<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Necronomidex</title>
</head>
<body>
	<h2>Ajouter un commentaire et une note sur 5</h2>
	<form action="addcritic" method="POST">
		<table border=1>
			<tr>
				<td>Votre commentaire : </td>
				<td><input name="commentary" maxlength=500 size=150/></td>
			</tr>
			<tr>
				<td>Votre note : </td>
				<td>
					<select name="rating">
						<option value=1>1</option>
						<option value=2>2</option>
						<option value=3>3</option>
						<option value=4>4</option>
						<option value=5>5</option>
					</select>
				</td>
			</tr>
			<tr>
           		<td colspan="2" align= "center"> <input type="submit" name= "valider"value="Ajouter le commentaire"/></td>
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