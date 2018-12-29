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
import alazif.pojos.Critic;
import alazif.pojos.Novel;
import alazif.pojos.Writer;

@Path("novel")
public class NovelAPI {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response no()
	{
		//int novelId, String title, int year, Writer writer, String synopsis, Set<Critic> setOfCritics, Set<Creature> setOfCreatures
		Novel defaultValue=new Novel(0,"Vide",0,new Writer(0,"NoName","NoName", "Aucune donnée"),"Pas grand chose",new HashSet<Critic>(),new HashSet<Creature>());
		return Response.status(Status.OK).entity(defaultValue).build();
	}
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id)
	{
		Novel n=new Novel();
		n.setNovelId(id);
		//On cherche ds la db selon id
		return Response.status(Status.OK).entity(n).build();
	}
	
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll()
	{
		Novel[] all=null;
		
		return Response.status(Status.OK).entity(all).build();
	}
}
