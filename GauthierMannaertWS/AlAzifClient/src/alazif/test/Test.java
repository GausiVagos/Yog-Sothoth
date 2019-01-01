package alazif.test;

import java.util.HashSet;

import alazif.dao.CreatureDAO;
import alazif.dao.CreatureNameDAO;
import alazif.dao.CriticDAO;
import alazif.dao.NovelDAO;
import alazif.dao.UserDAO;
import alazif.dao.WriterDAO;
import alazif.javabean.Creature;
import alazif.javabean.CreatureName;
import alazif.javabean.Critic;
import alazif.javabean.Novel;
import alazif.javabean.User;
import alazif.javabean.Writer;

public class Test {

	public static void main(String[] args) {
		//Tests des DAO
		/*
		CreatureDAO cdao=new CreatureDAO();
		Creature c=cdao.find("");
		System.out.println(c.getDescription());
		
		CreatureNameDAO cndao=new CreatureNameDAO();
		CreatureName cn=cndao.find("");
		System.out.println(cn.getName());
		
		CriticDAO ctdao=new CriticDAO();
		Critic ct=ctdao.find("");
		System.out.println(ct.getRating());
		
		NovelDAO ndao=new NovelDAO();
		Novel n=ndao.find("");
		System.out.println(n.getTitle());
		
		UserDAO udao=new UserDAO();
		User u=udao.find("");
		System.out.println(u.getUserName());

		WriterDAO wdao=new WriterDAO();
		Writer w=wdao.find("");
		System.out.println(w.getFirstName());
		
		Creature test=new Creature("Test description",new Writer("Machin","Truc","Il a vécu."),new HashSet<Novel>(),new HashSet<CreatureName>());
		cdao.create(test);
		System.out.println(test.getCreatureId());
		cdao.update(new Creature(42,"",new Writer(),new HashSet<Novel>(),new HashSet<CreatureName>()));
		System.out.println(cdao.delete(test));
		
		System.out.println(ctdao.delete(new Critic(1,1,"",1)));
		*/
		
		WriterDAO wdao=new WriterDAO();
		Writer w;
		w = wdao.find("2");
		System.out.println(w.getFirstName());
	}

}
