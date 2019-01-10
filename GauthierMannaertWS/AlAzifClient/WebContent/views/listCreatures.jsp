<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="alazif.javabean.Creature" %>
<%@ page import="alazif.javabean.CreatureName" %>
<%@ page import="alazif.javabean.User" %>
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
			 	if(tabCre!=null)
			 	for(Creature c : tabCre){
			 %>
			  	<tr>
			  		<td>
			  		<%
			  			if(!c.getSetOfNames().isEmpty())
			  				out.print(c.findFirstName().getName());
			  			else
			  				out.print("-Créature non nommée-");
			  		%></td>
			  		<td><a href="/AlAzifClient/creature?id=<%out.print(c.getCreatureId()); %>">Voir plus</td>
			  	</tr>			  
			 <%
			 	}	
			 %>
		  </table>
		  <%
			  try
			  {
				User u=(User)request.getSession().getAttribute("user");
				if(u!=null&&u.getAdmin())
				{
		  	
		  		%>
		  			<a href="/AlAzifClient/addcreature">Ajouter une créature</a>
		  		<% 
	  			}
			  }catch(NullPointerException e){}
		  %>
  </body>
</html>