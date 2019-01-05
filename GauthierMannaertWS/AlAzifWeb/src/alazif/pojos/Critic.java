package alazif.pojos;

import java.io.Serializable;

public class Critic implements Serializable{
	
	//attributs
	
	private static final long serialVersionUID = 1L;
	private int userId;
	private int novelId;
	private String commentary;
	private float rating;
	
	//constructeurs
	
	public Critic() {}
	
	public Critic(int userId, int novelId, String commentary, float rating) {
		this.userId = userId;
		this.novelId = novelId;
		this.commentary = commentary;
		this.rating = rating;
	}
	
	//getters
	
	public int getUserId() {
		return this.userId;
	}
	
	public int getNovelId() {
		return this.novelId;
	}
	
	public String getCommentary() {
		return this.commentary;
	}
	
	public float getRating() {
		return this.rating;
	}
	
	//setters
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setNovelId(int novelId) {
		this.novelId = novelId;
	}
	
	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}
	
	public void setRating(float rating) {
		this.rating = rating;
	}
}
