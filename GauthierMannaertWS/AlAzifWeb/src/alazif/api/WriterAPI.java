package alazif.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import alazif.pojos.Writer;

@Path("writer")
public class WriterAPI {
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
		Writer w=new Writer();
		w.setWriterId(id);
		//On cherche ds la db selon id
		return Response.status(Status.OK).entity(w).build();
	}
	
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll()
	{
		Writer[] all=null;
		
		return Response.status(Status.OK).entity(all).build();
	}
}
