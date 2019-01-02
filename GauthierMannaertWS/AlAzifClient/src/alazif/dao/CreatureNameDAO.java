package alazif.dao;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import alazif.javabean.Creature;
import alazif.javabean.CreatureName;


public class CreatureNameDAO extends DAO<CreatureName> {
	private String branchUrl=baseUrl+"creatureName/";

	@Override
	public boolean create(CreatureName obj) {
		return false;
	}
	
	public boolean create(CreatureName obj, Creature c) {
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			String json=mapper.writeValueAsString(obj);
			ClientResponse response = Client.create(new DefaultClientConfig()).resource(branchUrl)
					.path(Integer.toString(c.getCreatureId()))
				    .type(MediaType.APPLICATION_JSON)
				    .post(ClientResponse.class, json);
			if(response.getStatus()==201)
			{
				JSONObject jsonObject = new JSONObject(response.getEntity(String.class));
				obj.setCreatureNameId(jsonObject.getInt("id"));

				return true;
			}
		}
		catch(Exception e) {}
		return false;
	}

	@Override
	public boolean delete(CreatureName obj) {
		ClientResponse cr=Client.create(new DefaultClientConfig()).resource(branchUrl).path(Integer.toString(obj.getCreatureNameId())).accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);

		if(cr.getStatus()==200)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean update(CreatureName obj) {
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			String json=mapper.writeValueAsString(obj);
			ClientResponse cr=Client.create(new DefaultClientConfig()).resource(branchUrl)
					.path(Integer.toString(obj.getCreatureNameId()))
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