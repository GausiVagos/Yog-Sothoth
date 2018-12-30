package alazif.dao;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import alazif.javabean.Critic;

public class CriticDAO extends DAO<Critic> {
	private String branchUrl=baseUrl+"critic/";

	@Override
	public boolean create(Critic obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Critic obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Critic obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Critic find(String search) {
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
			Critic critic = mapper.readValue(jsonAnswer, Critic.class);
			return critic;
		}
		catch(Exception e)
		{
			return null;
		}
	}

}