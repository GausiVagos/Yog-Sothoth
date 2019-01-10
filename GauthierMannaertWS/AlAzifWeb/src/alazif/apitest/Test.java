package alazif.apitest;

import alazif.api.CreatureAPI;
import alazif.pojos.Creature;
import alazif.pojos.Writer;

public class Test {

	public static void main(String[] args) 
	{
		Writer w=new Writer();
		w.setWriterId(4);
		Creature c=new Creature("Vérification API",w,null,null);
		System.out.println(new CreatureAPI().add(c).getStatus());
	}

}
