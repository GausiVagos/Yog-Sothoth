package alazif.dao;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import alazif.javabean.Creature;

public class CreatureDAO extends DAO<Creature> {
	private String branchUrl=baseUrl+"creature/";
	
	@Override
	public boolean create(Creature obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Creature obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Creature obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Creature find(String search) {

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
			Creature creature = mapper.readValue(jsonAnswer, Creature.class);
			return creature;
		}
		catch(Exception e)
		{
			return null;
		}
	}
}