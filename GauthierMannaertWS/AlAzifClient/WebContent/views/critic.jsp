<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Necronomidex</title>
</head>
<body>
	<h2>Ajouter un commentaire et une note sur 10</h2>
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
					<%
					for(int i=0;i<=10;i++)
					{
						out.print("<option value="+i+">"+i+"</option>");
					}
					%>
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