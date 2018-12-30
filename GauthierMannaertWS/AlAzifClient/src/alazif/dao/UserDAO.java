package alazif.dao;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
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
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(branchUrl);
		
		String jsonAnswer = service
				.path(search)
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.readValue(jsonAnswer, User.class);
			return user;
		}
		catch(Exception e)
		{
			return null;
		}
	}

}