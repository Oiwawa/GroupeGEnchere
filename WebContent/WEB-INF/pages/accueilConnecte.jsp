<%@page import="java.util.List"%>
<%@page import="fr.eni.javaee.encheres.bo.Categorie"%>
<%@page import="fr.eni.javaee.encheres.bll.CategorieManager"%>
<%@page import="fr.eni.javaee.encheres.bo.Utilisateur"%>
<%@page import="fr.eni.javaee.encheres.bo.ArticleVendu"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>




<html>

<head>

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Les objets sont nos amis</title>
<!-- Custom stylesheet -->
<link rel="stylesheet" href="css/accueilStyle.css">


</head>
<body>
	<%
	Utilisateur connectedUser = (Utilisateur) session.getAttribute("user");
	List<Categorie> listeCategories = (List<Categorie>) request.getAttribute("listeCategorie");
	%>
	<header>
		<!-- Titre -->
		<h1>
			<a href="#">Les objets sont nos amis</a>
		</h1>
		<!-- INSCRIPTION ET CONNEXION---------------------------- -->
		<div>
			<a href="<%=request.getContextPath()%>/VenteArticle">Vendre un
				article</a>

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

			<br> <br>
			<!--CATEGORIE  -->
			<div>
				<label for="categorie">Categorie : </label> <select
					name="scategorie" id="categorie" required>
					<option value="">Toutes</option>
					<%
					for (Categorie categorie : CategorieManager.selectAllCat()) {
					%>

					<option value=<%=categorie.getNoCategorie()%>><%=categorie.getLibelle()%></option>
					<%
					}
					%>

				</select>
			</div>
		</form>
		<br> <br>

		<!-- LISTE -->

		<div>${av.forename}</div>
		<div>${av.forename}</div>



		<table border="1" style="border-spacing: 3px; border-collapse: separate;">
			<tr >
				<th><b>Nom</b></th>
				<th><b>Description</b></th>
				<th><b>Prix</b></th>
				<th><b>Début de l'enchère</b></th>
				<th><b>Fin de l'enchère</b></th>
				<th><b>Vendeur</b></th>
			</tr>
			<%-- Fetching the attributes of the request object 
             which was previously set by the servlet  
              "StudentServlet.java" 
        --%>
			<%
			ArrayList<ArticleVendu> avs = (ArrayList<ArticleVendu>) request.getAttribute("avs");
			for (ArticleVendu av : avs) {
			%>
			<%-- Arranging data in tabular form 
        --%>
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