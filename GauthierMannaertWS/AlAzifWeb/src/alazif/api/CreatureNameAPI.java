package alazif.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import alazif.pojos.CreatureName;

@Path("creatureName")
public class CreatureNameAPI{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response no()
	{
		CreatureName defaultValue=new CreatureName(0,"L'innommé");
		return Response.status(Status.OK).entity(defaultValue).build();
	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id)
	{
		CreatureName c=new CreatureName();
		c.setCreatureNameId(id);
		//On cherche ds la db selon id
		return Response.status(Status.OK).entity(c).build();
	}
	
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll()
	{
		CreatureName[] all=null;
		
		return Response.status(Status.OK).entity(all).build();
	}
}
