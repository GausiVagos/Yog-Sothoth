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
	private User u=new User();
	private UserDAO udao=new UserDAO();
	private Critic c=new Critic();
	private CriticDAO cdao=new CriticDAO();
	private Novel n=new Novel();
	private NovelDAO ndao=new NovelDAO();
	
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
}
