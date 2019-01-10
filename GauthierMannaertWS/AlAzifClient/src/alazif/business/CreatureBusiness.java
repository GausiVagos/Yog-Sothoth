package alazif.business;

import java.util.Set;

import alazif.dao.CreatureDAO;
import alazif.dao.CreatureNameDAO;
import alazif.javabean.Creature;
import alazif.javabean.CreatureName;
import alazif.javabean.Novel;
import alazif.javabean.Writer;

public class CreatureBusiness {
	
	private Creature c;
	private CreatureName cn;
	private CreatureDAO cdao;
	private CreatureNameDAO cndao;
	
	public CreatureBusiness() {
		c = new Creature();
		cn = new CreatureName();
		cdao = new CreatureDAO();
		cndao = new CreatureNameDAO();
	}

	public Creature getC() {
		return c;
	}

	public CreatureName getCn() {
		return cn;
	}

	public void setC(Creature c) {
		this.c = c;
	}

	public void setCn(CreatureName cn) {
		this.cn = cn;
	}
	
	//Pas vraiment utile
	/*
	public List<CreatureName> getAllCreatureNames(){
		
		CreatureName[] tabCN = cndao.getAll();
		List<CreatureName> lCN = new ArrayList<CreatureName>();
		
		for(CreatureName creName : tabCN) {
			lCN.add(creName);
		}
		
		return lCN;
	}
	*/
	
	public Creature[] getAllCreatures()
	{	
		return cdao.getAll();
	}
	
	public void instanciate(int creatureId)
	{
		c=cdao.find(Integer.toString(creatureId));
	}
	
	public Creature[] getCreaturesFromNovel(int novelId)
	{
		return cdao.getFromNovel(novelId);
	}
	
	public boolean addCreature(String desc, Writer w, Set<Novel> novels, Set<CreatureName> names)
	{
		c=new Creature(desc, w, novels, names);
		return cdao.create(c);
	}
	
}
