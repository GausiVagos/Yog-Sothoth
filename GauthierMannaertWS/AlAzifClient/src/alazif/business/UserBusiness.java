package alazif.business;

import alazif.dao.UserDAO;
import alazif.javabean.User;

public class UserBusiness {
	
	private User u;
	private UserDAO udao;
	private String erreur;
	
	public UserBusiness () {
		u = new User();
		udao = new UserDAO();
		erreur = "";
	}
	
	public User getU() {
		return u;
	}
	
	public String getErreur() {
		return erreur;
	}

	public void setU(User u) {
		this.u = u;
	}
	
	public void setErreur(String erreur) {
		this.erreur = erreur;
	}
	
	public void Connexion(String pseudo, String password) {
		
		User[] lUser = udao.getAll();
		
		if(pseudo == null) {
			erreur += "L'identifiant est null,";
		}
		
		if(pseudo.isEmpty()) {
			erreur += "L'identifiant est vide,";
		}
		
		if(password == null) {
			erreur += "Le mot de passe est null,";
		}
		
		if(password.isEmpty()) {
			erreur += "Le mot de passe est vide,";
		}
		
		if(erreur.equals("")) {
			for(User user : lUser) {
				if(user.getUserName() == pseudo && user.getPassword() == password) {
					this.u = user;
					break;
				}
			}
			if(u.getUserId() == 0) {
				erreur += "Pseudo/Mot de passe incorrect";
			}
		}
	}
}
