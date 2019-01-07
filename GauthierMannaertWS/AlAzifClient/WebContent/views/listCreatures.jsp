<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
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
	  ArrayList<CreatureName> lCN = (ArrayList<CreatureName>)request.getAttribute("list");
	  %>	  
		  <table border="1" cellspacing="0" cellpadding="5">
			 <%
			 	for(CreatureName cn: lCN){
			 %>
			  	<tr>
			  		<td><%out.print(cn.getName());%></td>
			  		<td><a href="">Voir plus</a></td>
			  	</tr>			  
			 <%
			 	}	
			 %>
		  </table>
  </body>
</html>