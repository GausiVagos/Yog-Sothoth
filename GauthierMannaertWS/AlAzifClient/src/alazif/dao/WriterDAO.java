package alazif.dao;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import alazif.javabean.Writer;

public class WriterDAO extends DAO<Writer> {
	private String branchUrl=baseUrl+"writer/";

	@Override
	public boolean create(Writer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Writer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Writer obj) {
		// TODO Auto-generated method stub
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

}