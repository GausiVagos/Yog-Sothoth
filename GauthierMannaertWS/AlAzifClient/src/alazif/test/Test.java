package alazif.test;

import java.util.HashSet;
import java.util.Set;

import alazif.dao.CreatureDAO;
import alazif.dao.NovelDAO;
import alazif.dao.WriterDAO;
import alazif.javabean.Creature;
import alazif.javabean.CreatureName;
import alazif.javabean.Novel;

public class Test {

	public static void main(String[] args) {
		Set<CreatureName> names=new HashSet<CreatureName>();
		names.add(new CreatureName("On s'AMUSE"));
		Set<Novel> novels=new HashSet<Novel>();
		novels.add(new NovelDAO().find("2"));
		Creature c=new Creature("Description", new WriterDAO().find("21"), novels, names);
		System.out.println(new CreatureDAO().create(c));
	}

}
