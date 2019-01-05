package alazif.apitest;

import alazif.api.CreatureAPI;
import alazif.pojos.Creature;

public class Test {

	public static void main(String[] args) 
	{/*
		Set<Novel> novels = new HashSet<Novel>();
		novels.add(new Novel(1, null, 0, null, null, null));
		novels.add(new Novel(2, null, 0, null, null, null));
		Set<CreatureName> names=new HashSet<CreatureName>();
		names.add(new CreatureName("Bobby"));
		names.add(new CreatureName("Bobby l'invincible"));
		Creature bobby = new Creature("Salut, moi c'est Bobby.", new Writer(1,"", null, null), novels, names);
		//System.out.println(cdao.create(bobby));
		 */
		Creature c = new Creature();
		CreatureAPI capi = new CreatureAPI();
		capi.getById(5);
	}

}
