<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="alazif.javabean.Creature" %>
<%@ page import="alazif.javabean.CreatureName" %>
<!DOCTYPE html>
<html>
  <head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <title>Necronomidex</title>
  </head>
  <body>
	  <h1>Creatures de l'univers Lovecraftien</h1>
	  <%
	  Creature[] tabCre = (Creature[])request.getAttribute("tabCre");
	  %>	  
		  <table border="1" cellspacing="0" cellpadding="5">
			 <%
			 	for(Creature c : tabCre){
			 %>
			  	<tr>
			  		<td>
			  		<%
			  			if(!c.getSetOfNames().isEmpty())
			  				out.print(c.getFirstName().getName());
			  			else
			  				out.print("-Créature non nommée-");
			  		%></td>
			  		<td><a href="/AlAzifClient/creature?id=<%out.print(c.getCreatureId()); %>">Voir plus</td>
			  	</tr>			  
			 <%
			 	}	
			 %>
		  </table>
  </body>
</html>