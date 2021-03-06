package alazif.api;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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

import alazif.pojos.CreatureName;
import oracle.jdbc.internal.OracleTypes;

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
		CreatureName cn = new CreatureName();
		CallableStatement addwri = null;
		ResultSet res = null;
		try {
			addwri = conn.prepareCall("{? = call findById.findCreatureName(?)}");
			
			addwri.registerOutParameter(1, OracleTypes.CURSOR);
			addwri.setInt(2, id);

			addwri.executeUpdate();
			res = (ResultSet) addwri.getObject(1);
			
			if(res.next()) {
				cn.setCreatureNameId(res.getInt("creatureNameId"));
				cn.setName(res.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		
		return Response.status(Status.OK).entity(cn).build();
	}
	
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll()
	{
		Set<CreatureName> all = new HashSet<CreatureName>();
		CallableStatement allWri = null;
		ResultSet res = null;
		
		try {
			allWri = conn.prepareCall("{? = call findAll.findAllName}");
			
			allWri.registerOutParameter(1, OracleTypes.CURSOR);
			
			allWri.execute();
			res = (ResultSet) allWri.getObject(1);
			
			if(res != null) {
				while(res.next()) {
					all.add(new CreatureName(res.getInt("creatureNameId"), res.getString("name")));
				}
			}
			
			allWri.close();
			res.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
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
			addcrename = conn.prepareCall("{? = call AddCreatureName(?, ?)}");
			
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
		CallableStatement modcn = null;
		try {
			modcn = conn.prepareCall("{call UpdateCreatureName(?, ?, ?)}");
			
			modcn.setInt(1, c.getCreatureNameId());
			modcn.setInt(2, id);//id de creature
			modcn.setString(3, c.getName());
			
			modcn.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		return Response.status(Status.OK).build();
	}
	
	@Path("{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id)
	{
		//On le supprime ds la db
		CallableStatement delcn = null;
		try {
			delcn = conn.prepareCall("{call DeleteCreatureName(?)}");
			
			delcn.setInt(1, id);
			
			delcn.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		return Response.status(Status.OK).build();
	}
}
