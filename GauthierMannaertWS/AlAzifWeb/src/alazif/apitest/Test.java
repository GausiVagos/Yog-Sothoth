package alazif.apitest;

import alazif.api.CreatureNameAPI;
import alazif.pojos.CreatureName;

public class Test {

	public static void main(String[] args) 
	{
		/*
		WriterAPI wapi = new WriterAPI();
		Writer w = new Writer();
		wapi.getById(1);
		System.out.println(w.getFirstName());
		
		UserAPI uapi = new UserAPI();
		User u = new User("Antoine", "azerty", true, null);
		uapi.add(u);
		System.out.println(u.getUserId());
		*/
		
		CreatureName cn = new CreatureName("Testosaure");
		CreatureNameAPI cnapi = new CreatureNameAPI();
		cnapi.add(cn, 5);
		System.out.println(cn.getCreatureNameId());
	}

}
