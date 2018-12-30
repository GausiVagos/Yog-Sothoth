package alazif.test;

import alazif.dao.CreatureDAO;
import alazif.javabean.Creature;

public class Test {

	public static void main(String[] args) {
		CreatureDAO cdao=new CreatureDAO();
		Creature c=cdao.find("");
		System.out.println(c.getDescription());
	}

}
