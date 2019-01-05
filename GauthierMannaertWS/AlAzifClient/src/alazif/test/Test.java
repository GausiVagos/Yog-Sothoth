package alazif.test;

import alazif.dao.UserDAO;

public class Test {

	public static void main(String[] args) {
		UserDAO udao=new UserDAO();
		System.out.println(udao.getAll().length);
	}

}
