package alazif.api;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashSet;

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
import alazif.pojos.User;

@Path("user")
public class UserAPI {
	
	Connection conn = ProjectConnection.getInstance();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response no()
	{
		User defaultValue=new User(0, "DefaultUser", "DefaultPassword", false, new HashSet<Critic>());
		return Response.status(Status.OK).entity(defaultValue).build();
	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id)
	{
		User u=new User();
		u.setUserId(id);
		//On cherche ds la db selon id
		return Response.status(Status.OK).entity(u).build();
	}
	
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll()
	{
		User[] all=null;
		
		return Response.status(Status.OK).entity(all).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(User u)
	{
		//On l'ajoute ds la db
		int id;
		//On renvoie l'id nouvellement créé pour l'injecter dans l'objet
		CallableStatement adduser = null;
		try {
			adduser = conn.prepareCall("? = AddUser(?, ?, ?)");
			
			adduser.registerOutParameter(1, Types.INTEGER);
			adduser.setString(2, u.getUserName());
			adduser.setString(3, u.getPassword());
			adduser.setBoolean(4, u.getAdmin());
			
			adduser.executeUpdate();
			id = adduser.getInt(1);
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
	public Response modify(@PathParam("id") int id, User u)
	{
		//On le modifie ds la db
		return Response.status(Status.OK).build();
	}
	
	@Path("{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id)
	{
		//On le supprime ds la db
		return Response.status(Status.OK).build();
	}
}
