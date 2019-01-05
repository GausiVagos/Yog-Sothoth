<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inscription</title>
</head>
<body>
	<h1>Inscription</h1>
	<form action="registration" method="POST">
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
    			<td>Confirmation mot de passe : </td>
    			<td><input type= "password" name="confmdp" id="confmdp" size="20"/></td>
    		</tr>
    		<tr>
    			<td colspan="2" align= "center"> <input type="submit" name= "valider" id="valider" value="Valider"/></td>
			</tr>
		</table>
	</form>
	<a href="/connection">retour</a>
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