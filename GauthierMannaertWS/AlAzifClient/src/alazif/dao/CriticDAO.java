package alazif.dao;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
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
		ClientResponse cr=Client.create(new DefaultClientConfig()).resource(branchUrl).path(search).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		if(cr.getStatus()==200)
		{
			try
			{
				ObjectMapper mapper = new ObjectMapper();
				Critic critic = mapper.readValue(cr.getEntity(String.class), Critic.class);
				return critic;
			}
			catch(Exception e){}
		}
		return null;
	}

}