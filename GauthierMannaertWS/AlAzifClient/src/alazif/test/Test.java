package alazif.test;

import alazif.dao.CreatureDAO;
import alazif.javabean.Creature;
import alazif.javabean.CreatureName;

public class Test {

	public static void main(String[] args) {
		//Tests des DAO
		/*
		
		
		CriticDAO ctdao=new CriticDAO();
		UserDAO udao=new UserDAO();
		NovelDAO ndao = new NovelDAO();
		Novel n=ndao.find("1");
		System.out.println(n.getWriter().getFirstName());
		
		CreatureNameDAO cndao=new CreatureNameDAO();
	
		
		*/
		CreatureDAO credao = new CreatureDAO();
		Creature cthulhu=credao.find("2");
		for(CreatureName cn : cthulhu.getSetOfNames())
			System.out.println(cn.getName());
	}

}
