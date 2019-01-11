<%@ 
page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="alazif.javabean.Writer" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Necronomidex</title>
</head>
<body>
	<h2>Entrez les données du nouveau récit</h2>
	<form action="addnovel" method="POST">
		<table border=1>
			<tr>
				<td>Titre : </td>
				<td><input name="title" maxlength=100 size=100></td>
			</tr>
			<tr>
				<td>Année : </td>
				<td><input type="number" name="year" min=1900 max=2019></td>
			</tr>
			<tr>
				<td>Ecrivain : </td>
				<td>
					<select name="writer">
						<%
						Writer[] all=(Writer[])request.getAttribute("all");
							if(all!=null)
								for(Writer w : all)
								{
								%>
									<option value="<%out.print(w.getWriterId()); %>">
										<%out.print(w.getFirstName()+" "+w.getLastName()); %>
									</option>
								<%
								}
								%>
					</select>
				</td>
			</tr>
			<tr>
				<td>Synopsis : </td>
				<td><input name="syn" maxlength=500 size=100></td>
			</tr>
			<tr>
           		<td colspan="2" align= "center"> <input type="submit" name= "valider"value="Ajouter le récit"/></td>
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