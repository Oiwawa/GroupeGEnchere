<%@page import="fr.eni.javaee.encheres.bo.Enchere"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="fr.eni.javaee.encheres.bo.ArticleVendu"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Les objets sont nos amis</title>
</head>
<body>
	<div>
		<h1>Détail Vente</h1>
	</div>

	<%
	ArticleVendu article = (ArticleVendu) request.getAttribute("ArticleAffiche");
	%>
	<table>
		<tr>
			<td>Nom :</td>
			<td><%=article.getNomArticle()%>
		</tr>
		<tr>
			<td>Description :</td>
			<td><%=article.getDescription()%>
		</tr>
		<tr>
			<td>Meilleure offre :</td>
			<td><%=article.getPrixVente()%></td>
		</tr>
		<tr>
			<td>Mise à prix :</td>
			<td><%=article.getMiseAPrix()%></td>
		</tr>
		<tr>
			<td>Fin de l'enchère :</td>
			<td><%=article.getDateFinEncheres()%></td>
		</tr>
		<tr>
			<td>Retrait :</td>
			<td><%=article.getLieuRetrait().getRue()%>, <%=article.getLieuRetrait().getCodePostal()%>,
				<%=article.getLieuRetrait().getVille()%></td>
		</tr>
		<tr>
			<td>Vendeur :</td>
			<td><%=article.getVendeur().getPseudo()%>
		</tr>
	</table>

	<form action="<%=request.getContextPath()%>/encherir" method="post">
		<div>
			<label for="sprix">Ma proposition : </label> <input type="number"
				name="sprix" id="sprix" step="1" max="10000" required>
		</div>
		<div>
			<button type="submit">Enchérir</button>
		</div>
		<input value="<%=article.getNoArticle()%>" type="text" id="idArticle"
			name="idArticle" style="visibility: hidden;">

	</form>
</body>
</html>