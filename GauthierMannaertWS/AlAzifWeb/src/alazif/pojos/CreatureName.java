package alazif.pojos;

import java.io.Serializable;

public class CreatureName implements Serializable{
	
	//attributs
	
	private static final long serialVersionUID = 1L;
	private int creatureNameId;
	private String name;
	
	//constructeurs
	
	public CreatureName() {}
	
	public CreatureName(String name) {
		this.creatureNameId = 0;
		this.name = name;
	}
	
	public CreatureName(int creatureNameId, String name) {
		this(name);
		this.creatureNameId = creatureNameId;
	}

	//getters
	
	public int getCreatureNameId() {
		return this.creatureNameId;
	}

	public String getName() {
		return this.name;
	}
	
	//setters
	
	public void setCreatureNameId(int creatureNameId) {
		this.creatureNameId = creatureNameId;
	}

	public void setName(String name) {
		this.name = name;
	}
}
