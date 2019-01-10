package alazif.javabean;

import java.io.Serializable;
import java.util.Set;

public class Creature implements Serializable{
	
	//attributs
	
	private static final long serialVersionUID = 1L;
	private int creatureId;
	private String description;
	private Writer firstWriter;
	private Set<Novel> setOfNovels;
	private Set<CreatureName> setOfNames;
	
	//constructeurs
	
	public Creature() {}
	
	public Creature(String desc, Writer writer, Set<Novel> setOfNovels, Set<CreatureName> setOfNames) {
		this.description=desc;
		this.firstWriter=writer;
		this.setOfNovels = setOfNovels;
		this.setOfNames = setOfNames;
	}
	
	public Creature(int creatureId, String description, Writer firstWriter, Set<Novel> setOfNovels, Set<CreatureName> setOfNames) {
		this(description, firstWriter, setOfNovels, setOfNames);
		this.creatureId = creatureId;
	}
	
	//getters

	public int getCreatureId() {
		return this.creatureId;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Writer getFirstWriter() {
		return this.firstWriter;
	}

	public Set<Novel> getSetOfNovels() {
		return this.setOfNovels;
	}

	public Set<CreatureName> getSetOfNames() {
		return this.setOfNames;
	}
	
	//setters

	public void setCreatureId(int creatureId) {
		this.creatureId = creatureId;
	}
	
	public void setDescription(String desc) {
		this.description=desc;
	}
	
	public void setFirstWriter(Writer fw) {
		this.firstWriter=fw;
	}

	public void setSetOfNovels(Set<Novel> setOfNovels) {
		this.setOfNovels = setOfNovels;
	}

	public void setSetOfNames(Set<CreatureName> setOfNames) {
		this.setOfNames = setOfNames;
	}
	
	//Méthodes des sets
	
	public void addNovel(Novel n) {
		setOfNovels.add(n);
	}
	
	public void addName(CreatureName c) {
		setOfNames.add(c);
	}
	
	public void deleteNovel(Novel n) {
		for(Novel nov : setOfNovels)
		{
			if(nov.getNovelId()==n.getNovelId())
			{
				setOfNovels.remove(nov);
				break;
			}			
		}
	}
	
	public void deleteName(CreatureName c) {
		for(CreatureName cn : setOfNames)
		{
			if(cn.getCreatureNameId()==c.getCreatureNameId())
			{
				setOfNames.remove(cn);
				break;
			}
		}
	}
	
	//Autres méthodes
	
	public CreatureName findFirstName()
	{
		CreatureName cn=null;
		if(!setOfNames.isEmpty())
		{
			int indexMin=9999;
			for(CreatureName n : setOfNames)
			{
				if(n.getCreatureNameId()<indexMin)
				{
					indexMin=n.getCreatureNameId();
					cn=n;
				}
			}
		}
		
		
		return cn;
	}
}