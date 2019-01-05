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

import alazif.pojos.Writer;
import oracle.jdbc.driver.OracleTypes;

@Path("writer")
public class WriterAPI {
	
	Connection conn = ProjectConnection.getInstance();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response no()
	{
		Writer defaultValue=new Writer(0, "NoName", "NoName", "Aucune donnée");
		return Response.status(Status.OK).entity(defaultValue).build();
	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id)
	{
		Writer w = new Writer();;
		
		CallableStatement addwri = null;
		ResultSet res = null;
		try {
			addwri = conn.prepareCall("{? = call findById.findWriter(?)}");
			
			addwri.registerOutParameter(1, OracleTypes.CURSOR);
			addwri.setInt(2, id);

			addwri.execute();
			res = (ResultSet) addwri.getObject(1);
			
			if(res.next()) {
				w.setWriterId(res.getInt("writerId"));
				w.setFirstName(res.getString("firstName"));
				w.setLastName(res.getString("lastName"));
				w.setBiography(res.getString("biography"));
			}
			
			addwri.close();
			res.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		
		return Response.status(Status.OK).entity(w).build();
	}
	
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll()
	{
		Set<Writer> all = new HashSet<Writer>();
		CallableStatement allWri = null;
		ResultSet res = null;
		
		try {
			allWri = conn.prepareCall("{? = call findAll.findAllWriter}");
			
			allWri.registerOutParameter(1, OracleTypes.CURSOR);
			
			allWri.execute();
			res = (ResultSet) allWri.getObject(1);
			
			if(res != null) {
				while(res.next()) {
					all.add(new Writer(res.getInt("writerId"), res.getString("firstName"), res.getString("lastName"), res.getString("biography")));
				}
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		
		return Response.status(Status.OK).entity(all).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Writer w)
	{
		//On l'ajoute ds la db
		int id;
		//On renvoie l'id nouvellement créé pour l'injecter dans l'objet
		
		CallableStatement addwri = null;
		try {
			addwri = conn.prepareCall("{? = call AddWriter(?, ?, ?)}");
			
			addwri.registerOutParameter(1, Types.INTEGER);
			addwri.setString(2, w.getFirstName());
			addwri.setString(3, w.getLastName());
			addwri.setString(4, w.getBiography());

			addwri.executeUpdate();
			id = addwri.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		return Response.status(Status.CREATED).entity("{\"id\":"+id+"}").build();
	}
	
	@Path("{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modify(@PathParam("id") int id, Writer w)
	{
		//On le modifie ds la db
		CallableStatement modwri = null;
		
		try {
			modwri = conn.prepareCall("{call UpdateWriter(?, ?, ?, ?)}");
			
			modwri.setInt(1, w.getWriterId());
			modwri.setString(2, w.getFirstName());
			modwri.setString(3, w.getLastName());
			modwri.setString(4, w.getBiography());
			
			modwri.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		return Response.status(Status.OK).build();
	}
	
	@Path("{id}")
	@DELETE
	public Response delete(@PathParam("id") int id)
	{
		//On le supprime ds la db
		CallableStatement delwri = null;
		
		try {
			delwri = conn.prepareCall("{call DeleteWriter(?)}");
			
			delwri.setInt(1, id);
			
			delwri.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		return Response.status(Status.OK).build();
	}
}
