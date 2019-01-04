package alazif.api;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import oracle.jdbc.internal.OracleTypes;

@Path("critic")
public class CriticAPI {
	
	Connection conn = ProjectConnection.getInstance();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response no()
	{
		Critic defaultValue=new Critic(0, 0, "", 42);
		return Response.status(Status.OK).entity(defaultValue).build();
	}
	
	@Path("{user}/{novel}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("user") int userId, @PathParam("novel") int novelId)
	{
		Critic c = new Critic();
		CallableStatement addwri = null;
		ResultSet res = null;
		try {
			addwri = conn.prepareCall("{? = call findById.findCritic(?,?)}");
			
			addwri.registerOutParameter(1, OracleTypes.CURSOR);
			addwri.setInt(2, userId);
			addwri.setInt(3, novelId);

			addwri.executeUpdate();
			res = (ResultSet) addwri.getObject(1);
			
			if(res.next()) {
				c.setUserId(res.getInt("userId"));
				c.setNovelId(res.getInt("novelId"));
				c.setCommentary(res.getString("commentary"));
				c.setRating(res.getFloat("rating"));
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
		Critic[] all=null;
		
		return Response.status(Status.OK).entity(all).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Critic c)
	{
		//On l'ajoute ds la db
		CallableStatement addcri = null;
		try {
			addcri = conn.prepareCall("{call AddCritic(?, ?, ?, ?)}");
			
			addcri.setInt(1, c.getUserId());
			addcri.setInt(2, c.getNovelId());
			addcri.setFloat(3, c.getRating());
			addcri.setString(4, c.getCommentary());
			
			addcri.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		return Response.status(Status.CREATED).build();
	}
	
	@Path("{user}/{novel}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modify(@PathParam("user") int userId, @PathParam("novel") int novelId, Critic c)
	{
		//On le modifie ds la db
		CallableStatement modcri = null;
		try {
			modcri = conn.prepareCall("{call UpdateCritic(?, ?, ?, ?)}");
			
			modcri.setInt(1, c.getUserId());
			modcri.setInt(2, c.getNovelId());
			modcri.setFloat(3, c.getRating());
			modcri.setString(4, c.getCommentary());
			
			modcri.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		return Response.status(Status.OK).build();
	}
	
	@Path("{user}/{novel}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("user") int userId, @PathParam("novel") int novelId)
	{
		//On le supprime ds la db
		CallableStatement delcri = null;
		try {
			delcri = conn.prepareCall("{call DeleteCritic(?, ?)}");
			
			delcri.setInt(1, userId);
			delcri.setInt(2, novelId);
			
			delcri.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		return Response.status(Status.OK).build();
	}
}
