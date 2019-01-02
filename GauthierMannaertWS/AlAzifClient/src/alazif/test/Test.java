package alazif.test;

import java.util.HashSet;
import java.util.Set;

import alazif.dao.CreatureDAO;
import alazif.dao.WriterDAO;
import alazif.javabean.Creature;
import alazif.javabean.Novel;
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
		w = wdao.find("1");
		/*
		System.out.println(w.getFirstName()+" "+w.getLastName());
		
		User u = new User("Antoine", "azerty", true, null);
		UserDAO udao = new UserDAO();
		udao.create(u);
		System.out.println(u.getUserId());
		*/
		Novel n = new Novel(1, "titretest", 2019, w, "Test", null);
		/*
		NovelDAO ndao = new NovelDAO();
		ndao.create(n);
		System.out.println(n.getNovelId());	
		
		Critic c = new Critic(1, 1, "J'aime/J'aime pas", 3);
		CriticDAO cdao = new CriticDAO();
		cdao.create(c);
		*/
		Creature cre = new Creature("Creature de Test", w, null, null);
		CreatureDAO credao = new CreatureDAO();
		credao.create(cre);
		System.out.println(cre.getCreatureId());
	}

}
