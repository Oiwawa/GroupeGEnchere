<%@page import="java.util.List"%>
<%@page import="fr.eni.javaee.encheres.bo.Categorie"%>
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

	<%
	Utilisateur connectedUser = (Utilisateur) session.getAttribute("user");
	List<Categorie> listeCategories = (List<Categorie>) request.getAttribute("listeCategorie");
	%>
	<h1>Nouvelle Vente</h1>
	<br>
	<!--NOM DE L'ARTICLE ---------  -->
	<form action="<%=request.getContextPath()%>/NouvelleEnchere.html"
		method="post">

		<label for="article">Article : </label> <input type="text" id="nomArt"
			name="sarticle" placeholder="Nom de l'article..." maxlength="30" required> <br>
		<br>
		<!--DESCRIPTION DE L'ARTICLE ---------  -->
		<label for="description">Description : </label>
		<textarea rows="5" cols="30" for="sdescription"
			id="description" name="sdescription" value="sdescription"
			maxlength="300" placeholder="Description de l'article..." required>
				</textarea>
		<br>
		<!--CATEGORIE DE L'ARTICLE ---------  -->
		<br> <label for="categorie">Categorie : </label> <select
			name="scategorie" id="categorie" required>
			<%
			for (Categorie categorie : CategorieManager.selectAllCat()) {
			%>
			<option value=<%=categorie.getNoCategorie()%>><%=categorie.getLibelle()%></option>
			<%
			}
			%>

		</select> <br> <br>
		<!--PHOTO DE L'ARTICLE ---------  -->
		<label>Photo de l'article : </label> <input type="file"
			accept=".jpeg, .jpg, .jpe, .jfif, .jif"> <input type="submit">
		<br> <br>
		<!--PRIX DE DEPART DE L'ARTICLE ---------  -->
		<label for="quantity">Prix : </label> <input type="number"
			id="quantity" name="sprix" step="1" max="10000" value="sprix"
			required> <br> <br>

		<!--DATE DE DEBUT D'ENCHERE DE L'ARTICLE  -->
		<label for="date">Début de l'enchère : </label> <input type="date"
			id="sdatedeb" name="sdatedeb" value="sdatedeb" required> <br> <br>

		<!--DATE DE FIN D'ENCHERE DE L'ARTICLE  -->
		<label for="date">Fin de l'enchère : </label> <input type="date"
			id="sdatefin" name="sdatefin" value="sdatefin" required> <br> <br>

		<br>
		<!-- RETRAIT -->
		<div>
			<h1>Retrait</h1>
			<div>
				<label for="article">Rue : </label> <input type="text" id="rue"
					name="srue" placeholder="Rue" <%-- value=<%=connectedUser.getRue() %>--%> > <br>
			</div>
			
			<br>
			<div>
				<label for="article">Code Postal : </label> <input type="text"
					id="cp" name="scodepostal" min="0" maxlength="5" placeholder="Ex: 44000"<%-- 	value=<%=connectedUser.getCodePostal() --%> >
				<br>
			</div>
			<br>
			<div>
				<label for="article">Ville : </label> <input type="text" id="ville"
					name="sville" placeholder="Ville" <%-- value=<%=connectedUser.getVille() %> --%>> <br>
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