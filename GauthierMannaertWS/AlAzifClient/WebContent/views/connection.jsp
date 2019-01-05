<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Necronomidex</title>
</head>
<body>
	  <h1>Connexion</h1>
		  <form action="connection" method="POST">
			  <table border="1">
                  <tr>
                      <td>Identifiant/Login : </td>
                      <td><input type="text" name="identifiant" id="identifiant" size="20"/></td>
                  </tr>
                  <tr>
                      <td>Mot de passe : </td>
                      <td><input type= "password" name="motdepasse" id="motdepasse" size="20"/></td>
                  </tr>
                  <tr>
                      <td colspan="2" align= "center"> <input type="submit" name= "valider" id="valider" value="Valider"/></td>
				  </tr>
			  </table>
		  </form>
		  <a href="/register">Inscription</a>
		  <p> 
		  	<%
		  		String erreur = (String)request.getAttribute("erreur"); 
		  		if(!erreur.equals("")){
		  			out.println(erreur);
		  		}
		  	%>
		  </p>
  </body>
</html>