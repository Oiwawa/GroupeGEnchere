<%@page import="fr.eni.javaee.encheres.bo.Categorie"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.javaee.encheres.bll.CategorieManager"%>
<%@page import="fr.eni.javaee.encheres.bo.Utilisateur"%>
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
	<form action="<%=request.getContextPath()%>/EnchereNonCommencee.html"
		method="post">

		<label for="article">Article : </label> <input type="text" id="nomArt"
			name="sarticle" placeholder="Nom" required> <br>
		<!--DESCRIPTION DE L'ARTICLE ---------  -->

		<label for="description">Description : </label>
		<textarea rows="5" cols="30" id="description" name="sdescription" value="<%=request.getParameter("sdescription")%>">
				</textarea>

		<br>
		<!--CATEGORIE DE L'ARTICLE ---------  -->
		<label for="categorie">Categorie : </label> <select name="scategorie"
			id="categorie">
			<%
			for (Categorie categorie : CategorieManager.selectAllCat()) {
			%>
			<option value="<%=categorie.getNoCategorie()%>"><%=categorie.getLibelle()%></option>
			<%
			}
			%>
		</select> <br>
		<!--PHOTO DE L'ARTICLE ---------  -->

		<label>Photo de l'article : </label> <input type="file"
			accept=".jpeg, .jpg, .jpe, .jfif, .jif"> <br>
		<!--PRIX DE DEPART DE L'ARTICLE ---------  -->

		<label for="quantity">Prix : </label> <input type="number"
			id="quantity" name="sprix" min="0" max="50" maxlength="4" size="4"
			value="<%=request.getParameter("sprix")%>">
		<!-- Ne pas supprimer le € -->
		€ <br>
		<!--DATE DE DEBUT D'ENCHERE DE L'ARTICLE  -->

		<label for="date">Début de l'enchère : </label> <input type="date"
			id="dateDeb" name="sdateDeb"
			value="<%=request.getParameter("sdatedeb")%>"> <br>
		<!--DATE DE FIN D'ENCHERE DE L'ARTICLE  -->

		<label for="date">Fin de l'enchère : </label> <input type="date"
			id="dateFin" name="sdateFin"
			value="<%=request.getParameter("sdatefin")%>"> <br> <br>
	
		<!-- RETRAIT -->
		<p>
			Retrait 
		</p>
			<label for="article">Rue : </label> <input type="text"
				id="rue" name="srue" placeholder="Rue" required> <br> <label
				for="article">Code Postal : </label> <input type="text" id="cp"
				name="scodepostal" placeholder="Ex: 44000" required> <br>
			<label for="article">Ville : </label> <input type="text" id="ville"
				name="sville" placeholder="Ville" required> <br>
				
				
				<!-- BOUTONS  -->
				 <input
				type="submit" value="Valider" /> <a
				href="<%=request.getContextPath()%>/EnchereNonCommencee"><input
				type="button" value="Annuler" /></a> <a
				href="<%=request.getContextPath()%>/EnchereNonCommencee"><input
				type="button" value="modifier" /></a>
	</form>
</body>
</html>