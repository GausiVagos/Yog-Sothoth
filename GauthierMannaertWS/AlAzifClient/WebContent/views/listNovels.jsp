<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="alazif.javabean.Novel" %>
<%@ page import="alazif.javabean.User" %>
<!DOCTYPE html>
<html>
  <head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <title>Necronomidex</title>
  </head>
  <body>
	  <h1>Récits du mythe de Cthulhu</h1>
	  <%
	  Novel[] lNov = (Novel[])request.getAttribute("all");
	  %>	  
		  <table border="1" cellspacing="0" cellpadding="5">
			 <%
			 	if(lNov!=null)
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
		  <%
			  try
			  {
				User u=(User)request.getSession().getAttribute("user");
				if(u!=null&&u.getAdmin())
				{
		  	
		  		%>
		  			<a href="/AlAzifClient/addnovel">Ajouter un récit</a>
		  		<% 
	  			}
			  }catch(NullPointerException e){}
		  %>
  </body>
</html>