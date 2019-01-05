package alazif.api;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import alazif.pojos.Critic;
import alazif.pojos.Novel;
import alazif.pojos.Writer;
import oracle.jdbc.driver.OracleTypes;

@Path("novel")
public class NovelAPI {
	
	Connection conn = ProjectConnection.getInstance();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response no()
	{
		//int novelId, String title, int year, Writer writer, String synopsis, Set<Critic> setOfCritics, Set<Creature> setOfCreatures
		Novel defaultValue=new Novel(0,"Vide",0,new Writer(0,"NoName","NoName", "Aucune donnée"),"Pas grand chose",new HashSet<Critic>());
		return Response.status(Status.OK).entity(defaultValue).build();
	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id)
	{
		Novel n = new Novel();	
		Writer w = new Writer();
		Set<Critic> lCri = new HashSet<Critic>();
		CallableStatement addwri = null;
		ResultSet res = null;
		try {
			addwri = conn.prepareCall("{? = call findById.findNovel(?)}");
			
			addwri.registerOutParameter(1, OracleTypes.CURSOR);
			addwri.setInt(2, id);

			addwri.execute();
			res = (ResultSet) addwri.getObject(1);
			
			if(res.next()) {
				n.setNovelId(res.getInt("novelId"));
				n.setTitle(res.getString("title"));
				n.setYear(res.getInt("publishingYear"));
				w.setWriterId(res.getInt("writerId"));
				n.setSynopsis(res.getString("synosis"));
			}
			
			addwri.close();
			res.close();
			
			addwri = conn.prepareCall("{? = call findById.findWriter(?)}");
			
			addwri.registerOutParameter(1, OracleTypes.CURSOR);
			addwri.setInt(2, w.getWriterId());

			addwri.execute();
			res = (ResultSet) addwri.getObject(1);
			
			if(res.next()) {
				w.setFirstName(res.getString("firstName"));
				w.setLastName(res.getString("lastName"));
				w.setBiography(res.getString("biography"));
				n.setWriter(w);
			}
			
			addwri.close();
			res.close();
			
			addwri = conn.prepareCall("{? = call findById.findCriticByNovel(?)}");
			
			addwri.registerOutParameter(1, OracleTypes.CURSOR);
			addwri.setInt(2, id);

			addwri.execute();
			res = (ResultSet) addwri.getObject(1);
			
			if(res != null) {
				while(res.next()) {
					lCri.add(new Critic(res.getInt("userId"), res.getInt("novelId"), res.getString("commentary"), res.getFloat("rating")));
				}
			}
			
			n.setSetOfCritics(lCri);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		
		return Response.status(Status.OK).entity(n).build();
	}
	
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll()
	{
		Set<Novel> all = new HashSet<Novel>();
		CallableStatement allWri = null;
		ResultSet res = null;
		
		try {
			allWri = conn.prepareCall("{? = call findAll.findAllNovel}");
			
			allWri.registerOutParameter(1, OracleTypes.CURSOR);
			
			allWri.execute();
			res = (ResultSet) allWri.getObject(1);
			
			if(res != null) {
				while(res.next()) {
					all.add(new Novel(res.getInt("novelId"), res.getString("title"), res.getInt("publishingyear"), null, res.getString("synosis"), null));
				}
			}
			
			allWri.close();
			res.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		
		return Response.status(Status.OK).entity(all).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Novel n)
	{
		//On l'ajoute ds la db
		int id;
		//On renvoie l'id nouvellement créé pour l'injecter dans l'objet
		CallableStatement addnov = null;
		try {
			addnov = conn.prepareCall("{? = call AddNovel(?, ?, ?, ?)}");
			
			addnov.registerOutParameter(1, Types.INTEGER);
			addnov.setString(2, n.getTitle());
			addnov.setInt(3, n.getYear());
			addnov.setInt(4, n.getWriter().getWriterId());
			addnov.setString(5, n.getSynopsis());
			
			addnov.executeUpdate();
			id = addnov.getInt(1);
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		return Response.status(Status.CREATED).entity("{\"id\":"+id+"}").build();
	}
	
	@Path("{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modify(@PathParam("id") int id, Novel n)
	{
		//On le modifie ds la db
		CallableStatement modnov = null;
		try {
			modnov = conn.prepareCall("{call UpdateNovel(?, ?, ?, ?, ?)}");
			
			modnov.setInt(1, n.getNovelId());
			modnov.setString(2, n.getTitle());
			modnov.setInt(3, n.getYear());
			modnov.setInt(4, n.getWriter().getWriterId());
			modnov.setString(5, n.getSynopsis());
			
			modnov.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		return Response.status(Status.OK).build();
	}
	
	@Path("{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id)
	{
		//On le supprime ds la db
		CallableStatement delnov = null;
		try {
			delnov = conn.prepareCall("{call DeleteNovel(?)}");
			
			delnov.setInt(1, id);
			
			delnov.executeUpdate();
			
			delnov.close();
			
			delnov = conn.prepareCall("{call DeleteCriticByNovel(?)}");
			
			delnov.setInt(1, id);
			
			delnov.executeUpdate();
			
			delnov.close();
			
			delnov = conn.prepareCall("{call deleteAppearanceNovel(?)}");
			
			delnov.setInt(1, id);
			
			delnov.executeUpdate();
			
			delnov.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		return Response.status(Status.OK).build();
	}
}
