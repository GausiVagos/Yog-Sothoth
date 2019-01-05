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
				if(user.getUserName().equals(pseudo) && user.getPassword().equals(password)) {
					this.u = user;
					break;
				}
			}
			if(u.getUserId() == 0) {
				erreur += "Pseudo/Mot de passe incorrect";
			}
		}
	}
	
	public void Inscription(String pseudo, String password, String conf) {
		
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
		
		if(conf == null) {
			erreur += "La confirmation est null,";
		}
		
		if(conf.isEmpty()) {
			erreur += "La confirmation est vide,";
		}
		
		if(!password.equals(conf)) {
			erreur += "Le mot de passe et la confirmation ne sont pas les mêmes, ";
		}
		
		if(erreur.equals("")) {
			u.setUserName(pseudo);
			u.setPassword(password);
			u.setAdmin(false);
			udao.create(u);
		}
	}
}
