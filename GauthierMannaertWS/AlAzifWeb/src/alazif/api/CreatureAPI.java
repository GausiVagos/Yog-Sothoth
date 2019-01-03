package alazif.api;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

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

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

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
		String json;
		
		CallableStatement addwri = null;
		try {
			addwri = conn.prepareCall("{? = call FINDCREATURE(?)}");
			
			addwri.registerOutParameter(1, Types.VARCHAR);
			addwri.setInt(2, id);

			addwri.executeUpdate();
			json = addwri.getString(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		
		return Response.status(Status.OK).entity(json).build();
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
			addcre = conn.prepareCall("{? = call AddCreature(?, ?)}");
			
			addcre.registerOutParameter(1,  Types.INTEGER);
			addcre.setString(2, c.getDescription());
			addcre.setInt(3, c.getFirstWriter().getWriterId());
			
			addcre.executeUpdate();
			id = addcre.getInt(1);
			
			//Ajout des apparitions éventuelles
			if(!c.getSetOfNovels().isEmpty())
			{
				for(Novel n : c.getSetOfNovels())
				{
					CallableStatement addapp=conn.prepareCall("{call ADDAPPEARANCE(?, ?)}");
					addapp.setInt(1, n.getNovelId());
					addapp.setInt(2, c.getCreatureId());
					addapp.executeUpdate();
				}
			}
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
		CallableStatement modcre = null;
		try {
			modcre = conn.prepareCall("{call UpdateCreature(?, ?, ?)}");
			
			modcre.setInt(1, c.getCreatureId());
			modcre.setString(2, c.getDescription());
			modcre.setInt(3, c.getFirstWriter().getWriterId());
			
			modcre.executeUpdate();
			
			//Comparaison des apparitions
			CallableStatement getapp=conn.prepareCall("{? = call getappearances(?)}");
			getapp.registerOutParameter(1, Types.VARCHAR);
			getapp.setInt(2, c.getCreatureId());
			getapp.executeUpdate();
			
			String list=getapp.getString(1);
			ObjectMapper mapper = new ObjectMapper();
			Set novelIds= mapper.readValue(list.getBytes(), Set.class);
			
			//Ajout des nouveaux
			for(Novel n : c.getSetOfNovels())
			{
				boolean found=false;
				for(Object i : novelIds)
				{
					if(n.getNovelId()==(Integer)i)
					{
						found=true;
						break;
					}
				}
				if(!found)
				{
					//Si une info n'est pas trouvée ds la DB, on l'ajoute
					CallableStatement addapp=conn.prepareCall("{call addappearance(?, ?)}");
					addapp.setInt(1, n.getNovelId());
					addapp.setInt(2, c.getCreatureId());
					addapp.executeUpdate();
				}
			}
			
			//Suppression des obsolètes
			for(Object i : novelIds)
			{
				boolean found=false;
				for(Novel n : c.getSetOfNovels())
				{
					if(n.getNovelId()==(Integer)i)
					{
						found=true;
						break;
					}
					if(!found)
					{
						//Si une info est dans la db mais pas dans l'objet, on la supprime
						CallableStatement delapp=conn.prepareCall("{call deleteappearance(?, ?)}");
						delapp.setInt(1, n.getNovelId());
						delapp.setInt(2, c.getCreatureId());
						delapp.executeUpdate();
					}
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		catch(Exception ex)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Status.OK).build();
	}
	
	@Path("{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id)
	{
		//On le supprime ds la db
		CallableStatement delcre = null;
		try {
			delcre = conn.prepareCall("{call DeleteCreature(?)}");
			
			delcre.setInt(1, id);
			
			delcre.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		return Response.status(Status.OK).build();
	}
}
