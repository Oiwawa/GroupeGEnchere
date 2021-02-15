<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="fr.eni.javaee.encheres.bo.Utilisateur" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Resultat Login</title>
</head>
<body>

<%
	Utilisateur u = (Utilisateur) session.getAttribute("user");
%>

<h1>Information utilisateur</h1>

	<ul>
		<li> Identifiant : <%= u.getPseudo() %>
		<li> Mot de passe : <%= u.getMotDePasse() %>
	</ul>
</body>
</html>