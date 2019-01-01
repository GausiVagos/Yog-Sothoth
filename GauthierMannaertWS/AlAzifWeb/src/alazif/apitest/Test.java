package alazif.apitest;

import alazif.api.WriterAPI;
import alazif.pojos.Writer;

public class Test {

	public static void main(String[] args) 
	{
		WriterAPI wapi = new WriterAPI();
		Writer w = new Writer();
		wapi.getById(1);
		System.out.println(w.getFirstName());
	}

}
