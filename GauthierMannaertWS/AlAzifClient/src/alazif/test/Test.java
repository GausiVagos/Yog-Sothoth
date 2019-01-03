package alazif.test;

import alazif.dao.CreatureDAO;
import alazif.javabean.Creature;
import alazif.javabean.CreatureName;

public class Test {

	public static void main(String[] args) {
		CreatureDAO cdao=new CreatureDAO();
		//Set<Novel> novels = new HashSet<Novel>();
		//novels.add(new NovelDAO().find("1"));
		//novels.add(new NovelDAO().find("2"));
		//Set<CreatureName> names=new HashSet<CreatureName>();
		//names.add(new CreatureName("Bobby"));
		//names.add(new CreatureName("Bobby l'invincible"));
		Creature bobby = cdao.find("15");
		bobby.addName(new CreatureName("Bobby l'uniquement unique"));
		bobby.addName(new CreatureName("Bobby le casse-burne"));
		System.out.println(bobby.getSetOfNames().size());
		System.out.println(cdao.update(bobby));
	}

}
