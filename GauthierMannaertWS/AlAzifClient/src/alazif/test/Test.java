package alazif.test;

import java.util.HashSet;
import java.util.Set;

import alazif.dao.CreatureDAO;
import alazif.dao.NovelDAO;
import alazif.dao.WriterDAO;
import alazif.javabean.Creature;
import alazif.javabean.Novel;

public class Test {

	public static void main(String[] args) {
		//Tests des DAO
		/*
		
		
		CriticDAO ctdao=new CriticDAO();
		UserDAO udao=new UserDAO();
		
		Novel n=ndao.find("1");
		System.out.println(n.getWriter().getFirstName());
		
		CreatureNameDAO cndao=new CreatureNameDAO();
	
		
		*/
		CreatureDAO credao = new CreatureDAO();
		NovelDAO ndao = new NovelDAO();
		WriterDAO wdao=new WriterDAO();
		Set<Novel> setOfNovels=new HashSet<Novel>();
		//setOfNovels.add(ndao.find("1"));
		Creature testAdd=credao.find("6");
		//System.out.println(testAdd.getDescription());
		testAdd.deleteNovel(ndao.find("1"));
		System.out.println(testAdd.getSetOfNovels().isEmpty());
		System.out.println(credao.update(testAdd));
	}

}
