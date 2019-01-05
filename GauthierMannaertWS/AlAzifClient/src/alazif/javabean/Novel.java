package alazif.javabean;

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
	
	//constructeurs
	
	public Novel() {}
	
	public Novel(String title, int year, Writer writer, String synopsis, Set<Critic> setOfCritics) {
		this.novelId = 0;
		this.title = title;
		this.year = year;
		this.writer=writer;
		this.synopsis=synopsis;
		this.setOfCritics = setOfCritics;
	}
	
	public Novel(int novelId, String title, int year, Writer writer, String synopsis, Set<Critic> setOfCritics) {
		this(title, year, writer, synopsis, setOfCritics);
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
	
	//Méthodes de Sets
	
	public void AddCritic(Critic c) {
		setOfCritics.add(c);
	}
	
	public void DeleteCritic(Critic c) {
		for(Critic crit : setOfCritics)
		{
			if(crit.getUserId()==c.getUserId() && crit.getNovelId()==c.getNovelId())
			{
				setOfCritics.remove(crit);
			}
		}
	}
	
	//Autres méthodes
	
	public float calculerMoyenne()
	{
		if(!setOfCritics.isEmpty())
		{
			float tot=0;
			for(Critic c : setOfCritics)
			{
				tot+=c.getRating();
			}
			return tot/setOfCritics.size();
		}
		else return 0;
	}
}