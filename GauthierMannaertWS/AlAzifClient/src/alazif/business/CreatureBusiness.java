package alazif.business;

import java.util.ArrayList;
import java.util.List;

import alazif.dao.CreatureDAO;
import alazif.dao.CreatureNameDAO;
import alazif.javabean.Creature;
import alazif.javabean.CreatureName;

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
	
	public List<CreatureName> getAllCreatureNames(){
		
		CreatureName[] tabCN = cndao.getAll();
		List<CreatureName> lCN = new ArrayList<CreatureName>();
		
		for(CreatureName creName : tabCN) {
			lCN.add(creName);
		}
		
		return lCN;
	}
	
	public void instanciate(int creatureId)
	{
		c=cdao.find(Integer.toString(creatureId));
	}
	
	public Creature[] getCreaturesFromNovel(int novelId)
	{
		return cdao.getFromNovel(novelId);
	}
	
}
