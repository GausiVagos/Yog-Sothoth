<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="alazif.javabean.Writer" %>
<!DOCTYPE html>
<html>
  <head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <title>Necronomidex</title>
  </head>
  <body>
	  <h1>Auteurs de l'univers Lovecraftien</h1>
	  <%
	  ArrayList<Writer> lWri = (ArrayList<Writer>)request.getAttribute("list");
	  %>	  
		  <table border="1" cellspacing="0" cellpadding="5">
			 <%
			 	for(Writer w : lWri){
			 %>
			  	<tr>
			  		<td><%out.print(w.getFirstName() + " " + w.getLastName());%></td>
			  		<td><a href="/AlAzifClient/writer?id=<%out.print(w.getWriterId());%>">Voir plus</a></td>
			  	</tr>			  
			 <%
			 	}	
			 %>
		  </table>
  </body>
</html>