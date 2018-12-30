package alazif.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProjectConnection {
	
	private static Connection instance = null;
	
	private ProjectConnection() {
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:char-oracle11.condorcet.be:1521:xe";
			String username = "simon3";
			String password = "Simon";
			instance = DriverManager.getConnection(url, username, password);
		}
		catch(ClassNotFoundException e) {
			System.out.println("Classe de driver introuvable" + e.getMessage());
			System.exit(0);
		}
		catch(SQLException e) {
			System.out.println("Erreur JDBC : " + e.getMessage());
			System.exit(0);
		}
		
		if(instance == null) {
			System.out.println("La base de données est inaccessible, fermeture du programme.");
            System.exit(0);
		}
	}
	
	public static Connection getInstance() {
		
		if(instance == null){
			new ProjectConnection();
		}
		return instance;
	}
}
