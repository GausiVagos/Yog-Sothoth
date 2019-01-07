<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="alazif.javabean.Novel" %>
<!DOCTYPE html>
<html>
  <head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <title>Necronomidex</title>
  </head>
  <body>
	  <h1>Nouvelles de l'univers Lovecraftien</h1>
	  <%
	  ArrayList<Novel> lNov = (ArrayList<Novel>)request.getAttribute("list");
	  %>	  
		  <table border="1" cellspacing="0" cellpadding="5">
			 <%
			 	for(Novel n: lNov){
			 %>
			  	<tr>
			  		<td><%out.print(n.getTitle());%></td>
			  		<td><a href="/AlAzifClient/novel?id=<%out.print(n.getNovelId()); %>">Voir plus</a></td>
			  	</tr>			  
			 <%
			 	}	
			 %>
		  </table>
  </body>
</html>