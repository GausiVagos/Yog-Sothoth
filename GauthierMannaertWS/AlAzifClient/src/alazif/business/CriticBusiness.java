package alazif.business;

import java.util.HashSet;
import java.util.Set;

import alazif.dao.CriticDAO;
import alazif.dao.NovelDAO;
import alazif.dao.UserDAO;
import alazif.javabean.Critic;
import alazif.javabean.Novel;
import alazif.javabean.User;

public class CriticBusiness 
{
	private User u;
	private UserDAO udao;
	private Critic c;
	private CriticDAO cdao;
	private Novel n;
	private NovelDAO ndao;
	private String erreur;
	
	public CriticBusiness() {
		u=new User();
		udao=new UserDAO();
		c=new Critic();
		cdao=new CriticDAO();
		n=new Novel();
		ndao=new NovelDAO();
		erreur = "";
	}
	
	public User getU()
	{
		return u;
	}
	
	public Critic getC()
	{
		return c;
	}
	
	public Novel getN()
	{
		return n;
	}
	
	public String getErreur() {
		return erreur;
	}
	
	public void setU(User u) {
		this.u = u;
	}
	
	public void setC(Critic c) {
		this.c = c;
	}
	
	public void setN(Novel n) {
		this.n= n;
	}
	
	public void setErreur(String erreur) {
		this.erreur = erreur;
	}
	
	//Pas très utile
	/*
	public void instanciate(int uId, int nId)
	{
		u=udao.find(Integer.toString(uId));
		n=ndao.find(Integer.toString(nId));
		c=cdao.find(uId+"/"+nId);
	}
	*/
	
	public Set<CriticRow> getCriticsFromUser(int uId)
	{
		User user=udao.find(Integer.toString(uId));
		Set<CriticRow> cr=new HashSet<CriticRow>();
		for(Critic c : user.getSetOfCritics())
		{
			cr.add(new CriticRow(user,c,ndao.find(Integer.toString(c.getNovelId()))));
		}
		return cr;
	}
	
	public Set<CriticRow> getCriticsFromNovel(int nId)
	{
		Novel novel=ndao.find(Integer.toString(nId));
		Set<CriticRow> cr=new HashSet<CriticRow>();
		for(Critic c : novel.getSetOfCritics())
		{
			cr.add(new CriticRow(udao.find(Integer.toString(c.getUserId())),c,novel));
		}
		return cr;
	}
	
	public boolean addCritic(int uId, int nId, String commentary, float rating) {
		
		if(commentary == null) {
			erreur += "Le commentaire est null,";
		}
		
		if(commentary.isEmpty()) {
			erreur += "Le commentaire est vide,";
		}
		
		if(uId==0) {
			erreur += "uId=0,";
		}
		
		if(nId==0) {
			erreur += "nId=0,";
		}
		
		if(erreur.equals("")) {
			/*c.setUserId(uId);
			c.setNovelId(nId);
			c.setCommentary(commentary);
			c.setRating(rating);*/
			c=new Critic(uId,nId,commentary,rating);
			if(cdao.create(c)) {
				return true;
			}
			else {
				erreur += "Erreur de requête (User : "+uId+", Novel : "+nId+"),";
				return false;
			}
		}
		else {
			return false;
		}
	}
}
