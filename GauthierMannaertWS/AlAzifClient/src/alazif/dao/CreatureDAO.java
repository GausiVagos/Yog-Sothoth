package alazif.dao;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
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
		ClientResponse cr=Client.create(new DefaultClientConfig()).resource(branchUrl).path(search).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		if(cr.getStatus()==200)
		{
			try
			{
				ObjectMapper mapper = new ObjectMapper();
				Creature creature = mapper.readValue(cr.getEntity(String.class), Creature.class);
				return creature;
			}
			catch(Exception e){}
		}
		return null;
	}
}