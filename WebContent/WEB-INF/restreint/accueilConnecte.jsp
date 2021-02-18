<%@page import="fr.eni.javaee.encheres.bo.ArticleVendu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fr.eni.javaee.encheres.bo.Categorie"%>
<%@page import="fr.eni.javaee.encheres.bll.CategorieManager"%>
<%@page import="fr.eni.javaee.encheres.bo.ArticleVendu"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="fr.eni.javaee.encheres.bo.Utilisateur" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/light.css">
<meta charset="UTF-8">
<title>AccueilConnecte</title>
</head>
<body>
	<header>
		<!-- Titre -->
		<h1>
			<a href="<%=request.getContextPath()%>/Accueil.html">Les objets sont nos amis</a>
		</h1>
		<!-- INSCRIPTION ET CONNEXION---------------------------- -->
		<div>
			<a href="<%=request.getContextPath()%>/VenteArticle">Vendre un article</a>

		</div>
	</header>
	<section>
		<div>
			<h3>Liste des encheres</h3>
		</div>

		<!-- FILTRE---------------------------- -->
		<p>Filtres</p>
		<form action="Accueil.html" method="post">
			<input type="text" placeholder="Le nom de l'article contient"
				name="nomArticle">
			<button type="submit" name="recherche" value="Rechercher">Rechercher</button>
<br><br>
			<!--CATEGORIE  -->
			<div>
				<label for="categorie">Categorie</label> <select name="categorie"
					id="categorie">
					<option>Tous</option>
						<%
			for (Categorie categorie : CategorieManager.selectAllCat()) {
			%>
			<option > <%=categorie.getLibelle()%></option>
			<%
			}
			%>	
				</select>
			</div>
		</form>
		<br><br><br><br>
	
		<!-- LISTE -->
		<table border="1">
			<tr >
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
	</section>
	<footer> </footer>
</body>
</html>