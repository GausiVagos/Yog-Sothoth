package alazif.dao;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import alazif.javabean.User;

public class UserDAO extends DAO<User> {
	private String branchUrl=baseUrl+"user/";

	@Override
	public boolean create(User obj) {
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			String json=mapper.writeValueAsString(obj);
			ClientResponse response = Client.create(new DefaultClientConfig()).resource(branchUrl)
				    .type(MediaType.APPLICATION_JSON)
				    .post(ClientResponse.class, json);
			if(response.getStatus()==201)
			{
				JSONObject jsonObject = new JSONObject(response.getEntity(String.class));
				obj.setUserId(jsonObject.getInt("id"));

				return true;
			}
		}
		catch(Exception e) {}
		return false;
	}

	@Override
	public boolean delete(User obj) {
		ClientResponse cr=Client.create(new DefaultClientConfig()).resource(branchUrl).path(Integer.toString(obj.getUserId())).accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);

		if(cr.getStatus()==200)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean update(User obj) {
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			String json=mapper.writeValueAsString(obj);
			ClientResponse cr=Client.create(new DefaultClientConfig()).resource(branchUrl)
					.path(Integer.toString(obj.getUserId()))
					.type(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.put(ClientResponse.class,json);
			if(cr.getStatus()==200)
			{
				return true;
			}
		}
		catch(Exception e) {}
		return false;
	}

	@Override
	public User find(String search) {
		ClientResponse cr=Client.create(new DefaultClientConfig()).resource(branchUrl).path(search).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if(cr.getStatus()==200)
		{
			
			try
			{
				ObjectMapper mapper = new ObjectMapper();
				User user = mapper.readValue(cr.getEntity(String.class), User.class);
				return user;
			}
			catch(Exception e){}
		}
		return null;
	}

}