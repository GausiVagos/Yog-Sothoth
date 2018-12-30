package alazif.dao;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import alazif.javabean.User;

public class UserDAO extends DAO<User> {
	private String branchUrl=baseUrl+"user/";

	@Override
	public boolean create(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User obj) {
		// TODO Auto-generated method stub
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