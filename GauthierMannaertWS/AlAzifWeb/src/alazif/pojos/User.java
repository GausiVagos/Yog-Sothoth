package alazif.pojos;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable{
	
	//attributs

	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	private String password;
	private boolean admin;
	private Set<Critic> setOfCritics;
	
	//constructeurs
	
	public User() {}
	
	public User(String userName, String password, boolean admin, Set<Critic> setOfCritics) {
		this.userId = 0;
		this.userName = userName;
		this.password = password;
		this.admin = admin;
		this.setOfCritics = setOfCritics;
	}
	
	public User(int userId, String userName, String password, boolean admin, Set<Critic> setOfCritics) {
		this(userName, password, admin, setOfCritics);
		this.userId = userId;
	}
	
	//getters
	
	public int getUserId() {
		return this.userId;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public boolean getAdmin() {
		return this.admin;
	}
	
	public Set<Critic> getSetOfCritics(){
		return this.setOfCritics;
	}
	
	//setters
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public void setSetOfCritics(Set<Critic> setOfCritics) {
		this.setOfCritics = setOfCritics;
	}
	
	//méthodes pour liste
	
	public void AddCritic(Critic c) {
		setOfCritics.add(c);
	}
	
	public void DeleteCritic(Critic c) {
		setOfCritics.remove(c);
	}
}
