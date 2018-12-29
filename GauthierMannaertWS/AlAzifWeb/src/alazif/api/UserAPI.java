package alazif.api;

import java.util.HashSet;

import javax.ws.rs.GET;
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
}
