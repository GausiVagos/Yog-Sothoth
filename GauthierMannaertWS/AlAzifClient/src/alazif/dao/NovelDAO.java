package alazif.dao;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
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
		ClientResponse cr=Client.create(new DefaultClientConfig()).resource(branchUrl).path(search).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		if(cr.getStatus()==200)
		{
			try
			{
				ObjectMapper mapper = new ObjectMapper();
				Novel novel = mapper.readValue(cr.getEntity(String.class), Novel.class);
				return novel;
			}
			catch(Exception e){}
		}
		return null;
	}

}