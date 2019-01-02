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
import alazif.pojos.Novel;
import alazif.pojos.Writer;

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
