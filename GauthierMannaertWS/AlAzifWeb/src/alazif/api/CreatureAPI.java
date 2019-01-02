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

import alazif.pojos.Creature;
import alazif.pojos.CreatureName;
import alazif.pojos.Novel;
import alazif.pojos.Writer;

@Path("creature")
public class CreatureAPI {
	
	Connection conn = ProjectConnection.getInstance();
	
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
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Creature c)
	{
		//On l'ajoute ds la db
		int id;
		CallableStatement addcre = null;
		try {
			addcre = conn.prepareCall("? = call AddCreature(?, ?)");
			
			addcre.registerOutParameter(1,  Types.INTEGER);
			addcre.setString(2, c.getDescription());
			addcre.setInt(3, c.getCreatureId());
			
			addcre.executeUpdate();
			id = addcre.getInt(1);
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		//On renvoie l'id nouvellement créé pour l'injecter dans l'objet
		
		return Response.status(Status.CREATED).entity("{\"id\":"+id+"}").build();
	}
	
	@Path("{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modify(@PathParam("id") int id, Creature c)
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
