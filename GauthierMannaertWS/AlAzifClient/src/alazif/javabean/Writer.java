package alazif.javabean;

import java.io.Serializable;

public class Writer implements Serializable{
	
	//attributs
	
	private static final long serialVersionUID = 1L;
	private int writerId;
	private String firstName;
	private String lastName;
	private String biography;
	
	//constructeurs
	
	public Writer() {}
	
	public Writer(String firstName, String lastName, String biography) {
		this.writerId = 0;
		this.firstName = firstName;
		this.lastName = lastName;
		this.biography=biography;
	}
	
	public Writer(int writerId, String firstName, String lastName, String biography) {
		this(firstName, lastName, biography);
		this.writerId = writerId;
	}
	
	//getters

	public int getWriterId() {
		return this.writerId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}
	
	public String getBiography(){
		return this.biography;
	}
	
	//setters

	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setBiography(String biog) {
		this.biography=biog;
	}
	
}