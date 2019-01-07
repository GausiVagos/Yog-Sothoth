package alazif.business;

import alazif.javabean.Critic;
import alazif.javabean.Novel;
import alazif.javabean.User;

public class CriticRow 
{
	private User user;
	private Critic critic;
	private Novel novel;
	
	public CriticRow(User u, Critic c, Novel n)
	{
		user=u;
		critic=c;
		novel=n;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Critic getCritic() {
		return critic;
	}

	public void setCritic(Critic critic) {
		this.critic = critic;
	}

	public Novel getNovel() {
		return novel;
	}

	public void setNovel(Novel novel) {
		this.novel = novel;
	}
}
