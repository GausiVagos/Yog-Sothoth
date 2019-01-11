<%@ page import="alazif.javabean.Novel" %>
<%@ page import="alazif.javabean.User" %>
<%@ page import="alazif.javabean.Creature" %>
<%@ page import="alazif.javabean.CreatureName" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <title>Necronomidex</title>
  </head>
  <body>
		<%Creature c=(Creature)request.getAttribute("creature"); %>
		<h1>
			<%
				if(c.findFirstName()!=null)	
					out.print(c.findFirstName().getName());
				else
					out.print("-Créature non nommée-");
			%>
		</h1>
		<h3>
			<%
				for(CreatureName cn : c.getSetOfNames())
				{
					if(cn!=c.findFirstName())
					{
						out.print("/ "+cn.getName());
					}
				}
			%>
		</h3>
		<b>Créature inventée par <%out.print(c.getFirstWriter().getFirstName()+" "+c.getFirstWriter().getLastName()); %></b>
		<p>
			<%out.print(c.getDescription()); %>
		</p>
		<%if(!c.getSetOfNovels().isEmpty()){ %>
		<h3>Elle apparait dans les romans suivants :</h3>
		<ul>
			<%for(Novel n : c.getSetOfNovels()){ %>
			<li>
				<a href="/AlAzifClient/novel?id=<%out.print(n.getNovelId()); %>"><%out.print(n.getTitle()); %></a>
			</li>
			<%} %>
		</ul>
		<%} 
		else{%>
		<p>Notre base de donnée ne contient malheureusement pas de détaills concernant les romans où cette créature apparaît.</p>
		<%} %>
		<p>
		<%
			  try
			  {
				User u=(User)request.getSession().getAttribute("user");
				if(u!=null&&u.getAdmin())
				{
		  		%>
		  			<a href="/AlAzifClient/deleteelement?type=c&id=<%out.print(c.getCreatureId());%>">SUPPRIMER CETTE CREATURE</a>
		  		<% 
	  			}
			  }catch(NullPointerException e){}
		  %>
		  </p>
  </body>
</html>