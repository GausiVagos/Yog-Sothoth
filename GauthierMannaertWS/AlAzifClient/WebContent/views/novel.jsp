<%@ page import="alazif.javabean.Novel" %>
<%@ page import="alazif.javabean.Creature" %>
<%@ page import="alazif.javabean.Critic" %>
<%@ page import="alazif.business.CriticRow" %>
<%@ page import="java.util.Set" %>
<%@ page import="alazif.javabean.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
	  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <title>Necronomidex</title>
  </head>
  <body>
		<%Novel n=(Novel)request.getAttribute("novel"); %>
		<h1><%out.print(n.getTitle()); %></h1>
		<h2>Ecrit par <%out.print(n.getWriter().getFirstName()+" "+n.getWriter().getLastName()); %></h2>
		<p>
			<%out.print(n.getSynopsis()); %>
		</p>
		<%
			Creature[] creatures=(Creature[])request.getAttribute("creatures");
			if(creatures.length>0){%>
			<h3>Les créatures suivantes apparaissent dans ce roman :</h3>
			<ul>
			<%for(Creature c : creatures){ %>
				<li>
					<a href="/AlAzifClient/creature?id=<%out.print(c.getCreatureId()); %>">
						<%
							if(c.findFirstName()!=null)	
								out.print(c.findFirstName().getName());
							else
								out.print("-Créature non nommée-");
						%>
					</a>
				</li>
			<%} %>
			</ul>
		<%}
		else
		{%>
			<p>Notre base de données ne mentionne malheureusement pas les créatures apparaissant dans ce roman</p>
		<%}%>
		
		<a href="/AlAzifClient/addcritic?id=<%out.print(n.getNovelId());%>">Ajouter un commentaire</a>
		
		<%
		Set<CriticRow> critics=(Set<CriticRow>)request.getAttribute("critics");
		if(critics!=null&&!critics.isEmpty())
		{
		%>
		<h3>Critiques d'utilisateurs à propos de ce roman : </h3>
		<ul>
			<%for(CriticRow cr : critics){ %>
			<li>
				De <b><%out.print(cr.getUser().getUserName()); %></b> : 
				<p><%out.print(cr.getCritic().getCommentary()); %></p>
				<b><i><%out.print(cr.getCritic().getRating()); %>/10</i></b>
			</li>
			<%} %>
		</ul>
		<i>Avis de la communauté : <%out.print(n.calculerMoyenne()); %></i>
		<%} %>
		<p>
		<%
			  try
			  {
				User u=(User)request.getSession().getAttribute("user");
				if(u!=null&&u.getAdmin())
				{
		  		%>
		  			<a href="/AlAzifClient/deleteelement?type=n&id=<%out.print(n.getNovelId());%>">SUPPRIMER CE RECIT</a>
		  		<% 
	  			}
			  }catch(NullPointerException e){}
		  %>
		  </p>
  </body>
</html>