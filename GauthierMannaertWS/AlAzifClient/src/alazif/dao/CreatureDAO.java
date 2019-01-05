package alazif.dao;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import alazif.javabean.Creature;

public class CreatureDAO extends DAO<Creature> {
	private String branchUrl=baseUrl+"creature/";
	
	@Override
	public boolean create(Creature obj) {
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			String json=mapper.writeValueAsString(obj);
			ClientResponse response = Client.create(new DefaultClientConfig()).resource(branchUrl)
				    .type(MediaType.APPLICATION_JSON)
				    .post(ClientResponse.class, json);
			if(response.getStatus()==201)
			{
				JSONObject jsonObject = new JSONObject(response.getEntity(String.class));
				obj.setCreatureId(jsonObject.getInt("id"));

				return true;
			}
		}
		catch(Exception e) {}
		return false;
	}

	@Override
	public boolean delete(Creature obj) {
		ClientResponse cr=Client.create(new DefaultClientConfig()).resource(branchUrl).path(Integer.toString(obj.getCreatureId())).accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);

		if(cr.getStatus()==200)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Creature obj) {
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			String json=mapper.writeValueAsString(obj);
			ClientResponse cr=Client.create(new DefaultClientConfig()).resource(branchUrl)
					.path(Integer.toString(obj.getCreatureId()))
					.type(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.put(ClientResponse.class,json);
			if(cr.getStatus()==200)
			{
				return true;
			}
		}
		catch(Exception e) {}
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

	@Override
	public Creature[] getAll() {
		Creature[] all=null;
		ClientResponse cr=Client.create(new DefaultClientConfig()).resource(branchUrl).path("all").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if(cr.getStatus()==200)
		{
			try
			{
				ObjectMapper mapper = new ObjectMapper();
				all = mapper.readValue(cr.getEntity(String.class), Creature[].class);
				return all;
			}
			catch(Exception e){}
		}
		return null;
	}
}