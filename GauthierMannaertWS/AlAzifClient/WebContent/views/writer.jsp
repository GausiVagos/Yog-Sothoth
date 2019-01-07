<%@ page import="alazif.javabean.Writer" %>
<%@ page import="alazif.javabean.Novel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <title>Necronomidex</title>
  </head>
  <body>
		<%Writer w=(Writer)request.getAttribute("writer"); %>
		<h1><%out.print(w.getFirstName() + " " + w.getLastName());%></h1>
		<p>
			<%out.print(w.getBiography());%>
		</p>
		<%
			Novel[] novels=(Novel[])request.getAttribute("novels");
			if(novels.length>0){%>
			<h3>Cet auteur a écrit les romans suivants :</h3>
			<ul>
			<%for(Novel n : novels){ %>
				<li>
					<a href="/AlAzifWeb/novel?id="<%out.print(n.getNovelId()); %>><%out.print(n.getTitle()); %></a>
				</li>
			<%} %>
			</ul>
		<%}
		else
		{%>
			<p>Notre base de données ne mentionne malheureusement pas les romans de cet auteur.</p>
		<%}%>
  </body>
</html>