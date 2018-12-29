package alazif.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import alazif.pojos.Critic;

@Path("critic")
public class CriticAPI {
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
		Critic c=new Critic();
		c.setUserId(userId);
		c.setNovelId(novelId);
		//On cherche ds la db selon id
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
}
