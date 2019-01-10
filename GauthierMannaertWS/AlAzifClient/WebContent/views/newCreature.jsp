<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="alazif.javabean.Writer" %>
<%@ page import="alazif.javabean.Novel" %>
<!DOCTYPE html>
<html>
  <head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <title>Necronomidex</title>
  </head>
  <body>
	<h2>Entrez les données de la nouvelle créature</h2>
	<form action="addcreature" method="POST">
		<table border=1>
			<tr>
				<td>Nom principal : </td>
				<td><input name="name" maxlength=50 size=50></td>
			</tr>
			<tr>
				<td>Description : </td>
				<td><input name="desc" maxlength=500 size=100></td>
			</tr>
			<tr>
				<td>Ecrivain : </td>
				<td>
					<select name="writer">
						<%
						Writer[] allW=(Writer[])request.getAttribute("allW");
							if(allW!=null)
								for(Writer w : allW)
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
				<td>Apparitions : </td>
				<td>
					<select name="novels" multiple>
						<%
						Novel[] allN=(Novel[])request.getAttribute("allN");
							if(allN!=null)
								for(Novel n : allN)
								{
								%>
									<option value="<%out.print(n.getNovelId()); %>">
										<%out.print(n.getTitle()); %>
									</option>
								<%
								}
								%>
					</select>
				</td>
			</tr>
			<tr>
           		<td colspan="2" align= "center"> <input type="submit" name= "valider"value="Ajouter la créature"/></td>
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