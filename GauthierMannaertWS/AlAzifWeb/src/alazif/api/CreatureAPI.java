package alazif.api;
import java.util.HashSet;

import javax.ws.rs.GET;
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

@Path("creature")
public class CreatureAPI {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response no()
	{
		Creature defaultValue=new Creature(0, "Un truc indéfini", new Writer(0,"NoName","NoName", "Aucune donnée"), new HashSet<Novel>(), new HashSet<CreatureName>());
		return Response.status(Status.OK).entity(defaultValue).build();
	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id)
	{
		Creature c=new Creature();
		c.setCreatureId(id);
		//On cherche ds la db selon id
		return Response.status(Status.OK).entity(c).build();
	}
	
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll()
	{
		Creature[] all=null;
		//On cherche ttes les créatures de la db
		return Response.status(Status.OK).entity(all).build();
	}
}
