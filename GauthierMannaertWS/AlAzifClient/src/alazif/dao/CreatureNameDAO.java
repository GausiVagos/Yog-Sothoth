package alazif.dao;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
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
		ClientResponse cr=Client.create(new DefaultClientConfig()).resource(branchUrl).path(search).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		if(cr.getStatus()==200)
		{
			try
			{
				ObjectMapper mapper = new ObjectMapper();
				CreatureName creatureName = mapper.readValue(cr.getEntity(String.class), CreatureName.class);
				return creatureName;
			}
			catch(Exception e){}
		}
		return null;
	}
}