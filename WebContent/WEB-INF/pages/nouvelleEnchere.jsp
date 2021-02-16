<%@page import="fr.eni.javaee.encheres.bo.Categorie"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.javaee.encheres.bll.CategorieManager"%>
<%@page import="fr.eni.javaee.encheres.bo.Utilisateur"%>
<%@page import="fr.eni.javaee.encheres.messages.LecteurMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Les objets sont nos amis</title>
</head>
<body>

	<h1>Nouvelle Vente</h1>
	<br>
	<!--NOM DE L'ARTICLE ---------  -->
	<form action="<%=request.getContextPath()%>/NouvelleEnchere.html"
		method="post">

		<label for="article">Article : </label> <input type="text" id="nomArt"
			name="sarticle" placeholder="Nom" required> <br> <br>
		<!--DESCRIPTION DE L'ARTICLE ---------  -->
		<label for="description">Description : </label>
		<textarea rows="5" cols="30" size="50" for="sdescription"
			id="description" name="sdescription" value="sdescription">
				</textarea>
		<br>
		<!--CATEGORIE DE L'ARTICLE ---------  -->
		<br> <label for="categorie">Categorie : </label> <select
			name="scategorie" id="categorie">
			<option value="">Toutes</option>
			<option value="1">Informatique</option>
			<option value="2">Ameublement</option>
			<option value="3">Vêtements</option>
			<option value="4">Sports loisirs</option>

		</select> <br> <br>
		<!--PHOTO DE L'ARTICLE ---------  -->
		<label>Photo de l'article : </label> <input type="file"
			accept=".jpeg, .jpg, .jpe, .jfif, .jif"> <br> <br>
		<!--PRIX DE DEPART DE L'ARTICLE ---------  -->
		<label for="quantity">Prix : </label> <input type="number"
			id="quantity" name="sprix" min="0" max="50" maxlength="4" size="4"
			value="sprix">
		<!-- Ne pas supprimer le € -->
		€ <br> <br>

		<!--DATE DE DEBUT D'ENCHERE DE L'ARTICLE  -->
		<label for="date">Début de l'enchère : </label> <input type="date"
			id="sdatedeb" name="sdatedeb" value="sdatedeb"> <br> <br>

		<!--DATE DE FIN D'ENCHERE DE L'ARTICLE  -->
		<label for="date">Fin de l'enchère : </label> <input type="date"
			id="sdatefin" name="sdatefin" value="sdatefin"> <br> <br>

		<br>
		<!-- RETRAIT -->
		<div>
			<h1>Retrait</h1>
			<div>
				<label for="article">Rue : </label> <input type="text" id="rue"
					name="srue" placeholder="Rue" required> <br>
			</div>
			<br>
			<div>
				<label for="article">Code Postal : </label> <input type="text"
					id="cp" name="scodepostal" placeholder="Ex: 44000" required>
				<br>
			</div>
			<br>
			<div>
				<label for="article">Ville : </label> <input type="text" id="ville"
					name="sville" placeholder="Ville" required> <br>
			</div>
			<br> <br>

			<!-- BOUTONS  -->
			<input type="submit" value="Valider" /> <a
				href="<%=request.getContextPath()%>/NouvelleEnchere.html"><input
				type="button" value="Annuler" /></a>

			<div>
				<%
				List<Integer> listeCodesErreur = (List<Integer>) request.getAttribute("listeCodesErreur");
				if (listeCodesErreur != null) {
				%>
				<p style="color: red;">Erreur, l'article n'a pas pu être ajouté
					:</p>
				<%
				for (int codeErreur : listeCodesErreur) {
				%>
				<p><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
				<%
				}
				}
				%>
			</div>
		</div>

		<!-- Erreurs -->




	</form>
</body>
</html>