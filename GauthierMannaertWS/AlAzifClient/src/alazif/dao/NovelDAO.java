package alazif.dao;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import alazif.javabean.Novel;

public class NovelDAO extends DAO<Novel> {
	private String branchUrl=baseUrl+"novel/";

	@Override
	public boolean create(Novel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Novel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Novel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Novel find(String search) {
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
			Novel novel = mapper.readValue(jsonAnswer, Novel.class);
			return novel;
		}
		catch(Exception e)
		{
			return null;
		}
	}

}