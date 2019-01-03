package alazif.test;

import alazif.dao.CreatureDAO;
import alazif.dao.CreatureNameDAO;
import alazif.dao.NovelDAO;
import alazif.javabean.Creature;

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
		bobby.deleteName(new CreatureNameDAO().find("25"));
		bobby.deleteNovel(new NovelDAO().find("1"));
		System.out.println(cdao.update(bobby));
	}

}
