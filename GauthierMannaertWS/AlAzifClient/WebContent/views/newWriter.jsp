<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Necronomidex</title>
</head>
<body>
	<h2>Entrez les données du nouvel écrivain</h2>
	<form action="addwriter" method="POST">
		<table border=1>
			<tr>
				<td>Prénom : </td>
				<td><input name="firstname" maxlength=50 size=50></td>
			</tr>
			<tr>
				<td>Nom : </td>
				<td><input name="name" maxlength=50 size=50></td>
			</tr>
			<tr>
				<td>Bio : </td>
				<td><input name="bio" maxlength=500 size=150></td>
			</tr>
			<tr>
           		<td colspan="2" align= "center"> <input type="submit" name= "valider"value="Ajouter l'écrivain"/></td>
			</tr>
		</table>
	</form>
	<p> 
	<%
		String erreur = (String)request.getAttribute("erreur"); 
		if(erreur != null){
			out.println(erreur);
		}
	%>
	</p>
</body>
</html>