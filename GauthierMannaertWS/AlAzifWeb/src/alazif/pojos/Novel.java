package alazif.pojos;

import java.io.Serializable;
import java.util.Set;

public class Novel implements Serializable{
	
	//attributs
	
	private static final long serialVersionUID = 1L;
	private int novelId;
	private String title;
	private int year;
	private Writer writer;
	private String synopsis;
	private Set<Critic> setOfCritics;
	private Set<Creature> setOfCreatures;
	
	//constructeurs
	
	public Novel() {}
	
	public Novel(String title, int year, Writer writer, String synopsis, Set<Critic> setOfCritics, Set<Creature> setOfCreatures) {
		this.novelId = 0;
		this.title = title;
		this.year = year;
		this.writer=writer;
		this.synopsis=synopsis;
		this.setOfCritics = setOfCritics;
		this.setOfCreatures = setOfCreatures;
	}
	
	public Novel(int novelId, String title, int year, Writer writer, String synopsis, Set<Critic> setOfCritics, Set<Creature> setOfCreatures) {
		this(title, year, writer, synopsis, setOfCritics, setOfCreatures);
		this.novelId = novelId;
	}
	
	//getters

	public int getNovelId() {
		return this.novelId;
	}

	public String getTitle() {
		return this.title;
	}

	public int getYear() {
		return this.year;
	}
	
	public Writer getWriter() {
		return this.writer;
	}
	
	public String getSynopsis() {
		return this.synopsis;
	}

	public Set<Critic> getSetOfCritics() {
		return this.setOfCritics;
	}

	public Set<Creature> getSetOfCreatures() {
		return this.setOfCreatures;
	}
	
	//setters

	public void setNovelId(int novelId) {
		this.novelId = novelId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public void setWriter(Writer writer) {
		this.writer=writer;
	}
	
	public void setSynopsis(String syn) {
		this.synopsis=syn;
	}

	public void setSetOfCritics(Set<Critic> setOfCritics) {
		this.setOfCritics = setOfCritics;
	}

	public void setSetOfCreatures(Set<Creature> setOfCreatures) {
		this.setOfCreatures = setOfCreatures;
	}
	
	//Méthodes de Sets
	
	public void AddCritic(Critic c) {
		//if(!setOfCritics.contains(c))
			setOfCritics.add(c);
	}
	
	public void AddCreature(Creature c) {
		//if(!setOfCreatures.contains(c))
			setOfCreatures.add(c);
	}
	
	public void DeleteCritic(Critic c) {
		//if(setOfCritics.contains(c))
			setOfCritics.remove(c);
	}
	
	public void DeleteCreature(Creature c) {
		//if(setOfCreatures.contains(c))
			setOfCreatures.remove(c);
	}
}
