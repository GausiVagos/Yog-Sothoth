package alazif.dao;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import alazif.javabean.CreatureName;


public class CreatureNameDAO extends DAO<CreatureName> {
	private String branchUrl=baseUrl+"creatureName/";

	@Override
	public boolean create(CreatureName obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(CreatureName obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CreatureName obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CreatureName find(String search) {
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
			CreatureName creatureName = mapper.readValue(jsonAnswer, CreatureName.class);
			return creatureName;
		}
		catch(Exception e)
		{
			return null;
		}
	}
}