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

import alazif.pojos.Creature;
import alazif.pojos.CreatureName;
import alazif.pojos.Novel;
import alazif.pojos.Writer;
import oracle.jdbc.OracleTypes;


@Path("creature")
public class CreatureAPI {
	
	Connection conn = ProjectConnection.getInstance();
	private static Creature defaultValue=new Creature(0, "Un truc indéfini", new Writer(0,"NoName","NoName", "Aucune donnée"), new HashSet<Novel>(), new HashSet<CreatureName>());
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response no()
	{
		return Response.status(Status.OK).entity(defaultValue).build();
	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id)
	{
		Creature c;
		Writer w = new Writer();
		Set<Novel> lNov = new HashSet<Novel>();
		Set<CreatureName> lCn = new HashSet<CreatureName>();
		Set<Integer> lInt = new HashSet<Integer>();
		CallableStatement addwri = null;
		ResultSet res = null;
		try {
			addwri = conn.prepareCall("{? = call findById.findCreature(?)}");
			
			addwri.registerOutParameter(1, OracleTypes.CURSOR);
			addwri.setInt(2, id);

			addwri.execute();
			res = (ResultSet) addwri.getObject(1);
			
			if(res.next()) {
				c=new Creature();
				c.setCreatureId(res.getInt("creatureId"));
				c.setDescription(res.getString("description"));
				w.setWriterId(res.getInt("writerId"));
				
				
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
					c.setFirstWriter(w);
				}
				
				addwri.close();
				res.close();
				
				addwri = conn.prepareCall("{? = call findById.findAppByCreature(?)}");
				
				addwri.registerOutParameter(1, OracleTypes.CURSOR);
				addwri.setInt(2, c.getCreatureId());
				
				addwri.execute();
				res = (ResultSet) addwri.getObject(1);
				
				if(res != null) {
					while(res.next()) {
						lInt.add(res.getInt("novelId"));
					}
					
					for(int i : lInt) {
						addwri = conn.prepareCall("{? = call findById.findNovel(?)}");
						
						addwri.registerOutParameter(1, OracleTypes.CURSOR);
						addwri.setInt(2, i);
						
						addwri.execute();
						res = (ResultSet)addwri.getObject(1);
						
						if(res.next()) {
							lNov.add(new Novel(i, res.getString("title"), res.getInt("publishingyear"), null, res.getString("synosis"), null));
						}
					}
					
					c.setSetOfNovels(lNov);
				}
				
				addwri.close();
				res.close();
				
				addwri = conn.prepareCall("{? = call findById.findNames(?)}");
				
				addwri.registerOutParameter(1, OracleTypes.CURSOR);
				addwri.setInt(2, id);
				
				addwri.execute();
				res = (ResultSet) addwri.getObject(1);
				
				if(res != null) {
					while(res.next()) {
						lCn.add(new CreatureName(res.getInt("creatureNameId"), res.getString("name")));
					}
				}
				
				c.setSetOfNames(lCn);
				
				addwri.close();
				res.close();
			}
			else
			{
				c=defaultValue;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		
		return Response.status(Status.OK).entity(c).build();
	}
	
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll()
	{
		Set<Creature> all = new HashSet<Creature>();
		CallableStatement allWri = null;
		ResultSet res = null;
		
		try {
			allWri = conn.prepareCall("{? = call findAll.findAllCreature}");
			allWri.registerOutParameter(1, OracleTypes.CURSOR);
			allWri.execute();
			res = (ResultSet) allWri.getObject(1);
			
			if(res != null) {
				CallableStatement getFirstName = conn.prepareCall("{? = call findById.findFirstCreatureName(?)}");
				getFirstName.registerOutParameter(1, OracleTypes.CURSOR);
				ResultSet name=null;
				while(res.next()) {
					getFirstName.setInt(2, res.getInt("creatureId"));
					getFirstName.execute();
					name=(ResultSet)getFirstName.getObject(1);
					Set<CreatureName> names=new HashSet<CreatureName>();
					if(name!=null && name.next())
					{
						names.add(new CreatureName(name.getInt("creatureNameId"),name.getString("name")));
					}
					all.add(new Creature(res.getInt("creatureId"), res.getString("description"), null, null, names));
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
	
	@Path("fromNovel/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFromNovel(@PathParam("id") int novel)
	{
		Set<Creature> cre = new HashSet<Creature>();
		CallableStatement fromNov = null;
		ResultSet res = null;
		
		try {
			fromNov = conn.prepareCall("{? = call findById.findCreaturesByNovel(?)}");
			
			fromNov.registerOutParameter(1, OracleTypes.CURSOR);
			fromNov.setInt(2, novel);
			fromNov.execute();
			res = (ResultSet) fromNov.getObject(1);
			
			if(res != null) {
				CallableStatement getFirstName = conn.prepareCall("{? = call findById.findFirstCreatureName(?)}");
				getFirstName.registerOutParameter(1, OracleTypes.CURSOR);
				ResultSet name=null;
				while(res.next()) {
					getFirstName.setInt(2, res.getInt("creatureId"));
					getFirstName.execute();
					name=(ResultSet)getFirstName.getObject(1);
					Set<CreatureName> names=new HashSet<CreatureName>();
					if(name!=null && name.next())
					{
						names.add(new CreatureName(name.getInt("creatureNameId"),name.getString("name")));
					}
					cre.add(new Creature(res.getInt("creatureId"), res.getString("description"), null, null, names));
				}
			}
			
			fromNov.close();
			res.close();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		
		return Response.status(Status.OK).entity(cre).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Creature c)
	{
		//On l'ajoute ds la db
		int id;
		CallableStatement addcre = null;
		try {
			addcre = conn.prepareCall("{? = call AddCreature(?, ?)}");
			
			addcre.registerOutParameter(1, Types.INTEGER);
			addcre.setString(2, c.getDescription());
			addcre.setInt(3, c.getFirstWriter().getWriterId());
			
			addcre.executeUpdate();
			id = addcre.getInt(1);
			
			//Ajout des apparitions éventuelles
			if(!c.getSetOfNovels().isEmpty())
			{
				for(Novel n : c.getSetOfNovels())
				{
					CallableStatement addapp=conn.prepareCall("{call ADDAPPEARANCE(?, ?)}");
					addapp.setInt(1, n.getNovelId());
					addapp.setInt(2, id);
					addapp.executeUpdate();
				}
			}
			//Ajout des noms éventuels
			if(!c.getSetOfNames().isEmpty())
			{
				for(CreatureName cn : c.getSetOfNames())
				{
					CallableStatement addname=conn.prepareCall("{? = call ADDCREATURENAME(?, ?)}");
					addname.registerOutParameter(1, Types.INTEGER);
					addname.setInt(2, id);
					addname.setString(3, cn.getName());
					addname.executeUpdate();
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		//On renvoie l'id nouvellement créé pour l'injecter dans l'objet
		
		return Response.status(Status.CREATED).entity("{\"id\":"+id+"}").build();
	}
	
	@Path("{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modify(@PathParam("id") int id, Creature c)
	{
		//On le modifie ds la db
		CallableStatement modcre = null;
		try {
			modcre = conn.prepareCall("{call UpdateCreature(?, ?, ?)}");
			
			modcre.setInt(1, c.getCreatureId());
			modcre.setString(2, c.getDescription());
			modcre.setInt(3, c.getFirstWriter().getWriterId());
			
			modcre.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		catch(Exception ex)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.OK).build();
	}
	
	@Path("{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id)
	{
		//On le supprime ds la db
		CallableStatement delcre = null;
		try {
			delcre = conn.prepareCall("{call DeleteNames(?)}");
			
			delcre.setInt(1, id);
			
			delcre.executeUpdate();
			
			delcre.close();
			
			delcre = conn.prepareCall("{call deleteAppearanceCreature(?)}");
			
			delcre.setInt(1, id);
			
			delcre.executeUpdate();
			
			delcre.close();
			
			delcre = conn.prepareCall("{call DeleteCreature(?)}");
			
			delcre.setInt(1, id);
			
			delcre.executeUpdate();
			
			delcre.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		return Response.status(Status.OK).build();
	}
}
