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

import alazif.pojos.Critic;
import alazif.pojos.User;
import oracle.jdbc.internal.OracleTypes;

@Path("user")
public class UserAPI {
	
	Connection conn = ProjectConnection.getInstance();
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id)
	{
		User u = new User();
		Set<Critic> lCri = new HashSet<Critic>();
		CallableStatement addwri = null;
		ResultSet res = null;
		try {
			addwri = conn.prepareCall("{? = call findById.findUser(?)}");
			
			addwri.registerOutParameter(1, OracleTypes.CURSOR);
			addwri.setInt(2, id);

			addwri.execute();
			res = (ResultSet) addwri.getObject(1);
			
			if(res.next()) {
				u.setUserId(res.getInt("userId"));
				u.setUserName(res.getString("userName"));
				u.setPassword(res.getString("password"));
				if(res.getInt("administator") == 1) {
					u.setAdmin(true);
				}
				else {
					u.setAdmin(false);
				}
			}
			
			addwri.close();
			res.close();
			
			addwri = conn.prepareCall("{? = call findById.findCriticByUser(?)}");
			
			addwri.registerOutParameter(1, OracleTypes.CURSOR);
			addwri.setInt(2, u.getUserId());

			addwri.execute();
			res = (ResultSet) addwri.getObject(1);
			
			if(res != null) {
				while(res.next()) {
					lCri.add(new Critic(res.getInt("userId"), res.getInt("novelId"), res.getString("commentary"), res.getFloat("rating")));
				}
			}
			
			u.setSetOfCritics(lCri);
			
			addwri.close();
			res.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		
		return Response.status(Status.OK).entity(u).build();
	}
	
	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll()
	{
		Set<User> all = new HashSet<User>();
		CallableStatement allWri = null;
		ResultSet res = null;
		
		try {
			allWri = conn.prepareCall("{? = call findAll.findAllUser}");
			
			allWri.registerOutParameter(1, OracleTypes.CURSOR);
			
			allWri.execute();
			res = (ResultSet) allWri.getObject(1);
			
			if(res != null) {
				while(res.next()) {
					Boolean b;
					if(res.getInt("administator") == 1) {
						b = true;
					}
					else {
						b = false;
					}
					all.add(new User(res.getInt("userId"), res.getString("userName"), res.getString("password"), b, null));
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
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(User u)
	{
		//On l'ajoute ds la db
		int id;
		int adm;
		//On renvoie l'id nouvellement créé pour l'injecter dans l'objet
		CallableStatement adduser = null;
		try {
			adduser = conn.prepareCall("{? = call AddUser(?, ?, ?)}");
			
			adduser.registerOutParameter(1, Types.INTEGER);
			adduser.setString(2, u.getUserName());
			adduser.setString(3, u.getPassword());
			if(u.getAdmin() == true) {
				adm = 1;
			}
			else {
				adm = 0;
			}
			adduser.setInt(4, adm);
			
			adduser.executeUpdate();
			id = adduser.getInt(1);
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
	public Response modify(@PathParam("id") int id, User u)
	{
		//On le modifie ds la db
		int adm;
		CallableStatement moduser = null;
		try {
			moduser = conn.prepareCall("{call UpdateUser(?, ?, ?, ?)}");
			
			moduser.setInt(1, u.getUserId());
			moduser.setString(2, u.getUserName());
			moduser.setString(3, u.getPassword());
			if(u.getAdmin() == true) {
				adm = 1;
			}
			else {
				adm = 0;
			}
			moduser.setInt(4, adm);
			
			moduser.executeUpdate();
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
		CallableStatement deluser = null;
		try {
			deluser = conn.prepareCall("{call DeleteUser(?)}");
			
			deluser.setInt(1, id);
			
			deluser.executeUpdate();
			
			deluser.close();
			
			deluser = conn.prepareCall("{call deleteCriticByUser(?)}");
			
			deluser.setInt(1, id);
			
			deluser.executeUpdate();
			
			deluser.close();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		return Response.status(Status.OK).build();
	}
}
