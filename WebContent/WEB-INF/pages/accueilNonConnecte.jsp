<%@page import="fr.eni.javaee.encheres.bo.ArticleVendu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fr.eni.javaee.encheres.bo.ArticleVendu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fr.eni.javaee.encheres.bo.Categorie"%>
<%@page import="fr.eni.javaee.encheres.bll.CategorieManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/light.css">
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>ENI-Enchères</title>
<!-- Custom stylesheet -->
<link rel="stylesheet" href="css/accueilStyle.css">


</head>
<body>
	<header>
		<!-- Titre -->
		<h1 align="center">
			<a href="<%=request.getContextPath()%>/Accueil.html">ENI-Enchères</a>
		</h1>
		<!-- INSCRIPTION ET CONNEXION---------------------------- -->
		<div align="right">
			<a href="<%=request.getContextPath()%>/CreationUtilisateur">S'inscrire</a> <span>
				- </span> <a href="<%=request.getContextPath()%>/ServletConnection">Se connecter</a>

		</div>
	</header>
	<section>
		<div align="center">
			<h3>Liste des encheres</h3>
		</div>

		<!-- FILTRE---------------------------- -->
		<div align="center">
			<p>Filtres :</p>

			<form action="Accueil.html" method="post">
			<label for="nomArticle">Article :</label>
				<input type="text" placeholder="Le nom de l'article contient"
					name="nomArticle">
		
				<br>
				<br>
				<!--CATEGORIE  -->
				<div>
					<label for="categorie">Categorie :</label> <select name="categorie"
						id="categorie">
						<option value="0">Tous</option>
						<%
						for (Categorie categorie : CategorieManager.selectAllCat()) {
						%>
							<option value="<%=categorie.getNoCategorie()%>"><%=categorie.getLibelle()%></option>
						<%
						}
						%>
					</select>
				</div>
				<br>
				<br>
				<button type="submit" name="recherche" value="Rechercher">Rechercher</button>
			</form>
		</div>
		<br>
		<br>
		<br>
		<br>


		<!-- LISTE -->
		<div align="center">
			<table border="1">
				<tr>
					<th><b>Nom</b></th>
					<th><b>Description</b></th>
					<th><b>Prix</b></th>
					<th><b>Début de l'enchère</b></th>
					<th><b>Fin de l'enchère</b></th>
					<th><b>Vendeur</b></th>
				</tr>
		
				<%
				ArrayList<ArticleVendu> avs = (ArrayList<ArticleVendu>) request.getAttribute("avs");
				for (ArticleVendu av : avs) {
				%>
		
				<tr>
					<td><%=av.getNomArticle()%></td>
					<td><%=av.getDescription()%></td>
					<td><%=av.getMiseAPrix()%></td>
					<td><%=av.getDateDebutEncheres()%></td>
					<td><%=av.getDateFinEncheres()%></td>
					<td><%=av.getVendeur().getPseudo()%></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>







	</section>
	<footer> </footer>
</body>
</html>