package alazif.api;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

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

import alazif.pojos.CreatureName;

@Path("creatureName")
public class CreatureNameAPI{
	
	Connection conn = ProjectConnection.getInstance();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response no()
	{
		CreatureName defaultValue=new CreatureName(0,"L'innomm�");
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
	
	@Path("{creatureId}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(CreatureName c, @PathParam("creatureId") int numeroCre)
	{
		//On l'ajoute ds la db
		int id;
		//On renvoie l'id nouvellement cr�� pour l'injecter dans l'objet
		
		CallableStatement addcrename = null;
		try {
			addcrename = conn.prepareCall("? = call AddCreatureName(?, ?)");
			
			addcrename.registerOutParameter(1, Types.INTEGER);
			addcrename.setInt(2, numeroCre);
			addcrename.setString(3, c.getName());
			
			addcrename.executeUpdate();
			id = addcrename.getInt(1);
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
	public Response modify(@PathParam("id") int id, CreatureName c)
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
