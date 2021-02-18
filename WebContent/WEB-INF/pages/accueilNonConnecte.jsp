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
	<header>
		<!-- Titre -->
		<h1>
			<a href="#">Les objets sont nos amis</a>
		</h1>
		<!-- INSCRIPTION ET CONNEXION---------------------------- -->
		<div>
			<a href="">S'inscrire</a> <span> - </span> <a
				href="/GroupeGEnchere/ServletConnection">Se connecter</a>

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

			<br>
			<!--CATEGORIE  -->
			<div>
				<label for="categorie">Categorie</label> <select name="categorie"
					id="categorie">
					<option value="">Toutes</option>
					<option value="informatique">Informatique</option>
					<option value="ameublement">Ameublement</option>
					<option value="vetements">Vetements</option>
					<option value="sport_loisirs">Sport Loisirs</option>
				</select>
			</div>
		</form>

		<!-- LISTE -->

		<div>${av.forename}</div>
		<div>${av.forename}</div>






		<table border="1">
			<tr bgcolor="00FF7F">
				<th><b>Nom</b></th>
				<th><b>Description</b></th>
				<th><b>Etat de vente</b></th>
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
				<td><%=av.getEtatVente()%></td>
			</tr>
			<%
			}
			%>
		</table>







	</section>
	<footer> </footer>
</body>
</html>