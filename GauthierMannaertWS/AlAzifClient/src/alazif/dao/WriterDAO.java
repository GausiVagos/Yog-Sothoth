package alazif.dao;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import alazif.javabean.Writer;

public class WriterDAO extends DAO<Writer> {
	private String branchUrl=baseUrl+"writer/";

	@Override
	public boolean create(Writer obj) {
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
				obj.setWriterId(jsonObject.getInt("id"));

				return true;
			}
		}
		catch(Exception e) {}
		return false;
	}

	@Override
	public boolean delete(Writer obj) {
		ClientResponse cr=Client.create(new DefaultClientConfig()).resource(branchUrl).path(Integer.toString(obj.getWriterId())).accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class);

		if(cr.getStatus()==200)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Writer obj) {
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			String json=mapper.writeValueAsString(obj);
			ClientResponse cr=Client.create(new DefaultClientConfig()).resource(branchUrl)
					.path(Integer.toString(obj.getWriterId()))
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
	public Writer find(String search) {
		ClientResponse cr=Client.create(new DefaultClientConfig()).resource(branchUrl).path(search).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		if(cr.getStatus()==200)
		{
			try
			{
				ObjectMapper mapper = new ObjectMapper();
				Writer writer = mapper.readValue(cr.getEntity(String.class), Writer.class);
				return writer;
			}
			catch(Exception e){}
		}
		return null;
	}

	@Override
	public Writer[] getAll() {
		Writer[] all=null;
		ClientResponse cr=Client.create(new DefaultClientConfig()).resource(branchUrl).path("all").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if(cr.getStatus()==200)
		{
			try
			{
				ObjectMapper mapper = new ObjectMapper();
				all = mapper.readValue(cr.getEntity(String.class), Writer[].class);
				return all;
			}
			catch(Exception e){}
		}
		return null;
	}

}