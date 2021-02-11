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
		<div>
			<label for="article">Article : </label> <input type="text"
				id="nomArt" name="sarticle"
				value="<%=request.getParameter("sarticle")%>" placeholder="Nom">
		</div>
	</form>
	<br>
	<!--DESCRIPTION DE L'ARTICLE ---------  -->
	<form action="<%=request.getContextPath()%>/NouvelleEnchere.html"
		method="post">
		<div>
			<label for="description">Description : </label>
			<textarea rows="5" cols="30" size="50" for="description"
				id="description" name="sdescription"
				value="<%=request.getParameter("sdescription")%>">
				</textarea>
		</div>
	</form>
	<br>
	<!--CATEGORIE DE L'ARTICLE ---------  -->
	<form action="<%=request.getContextPath()%>/NouvelleEnchere.html"
		method="post">
		<div>
			<label for="categorie">Categorie : </label> <select name="scategorie"
				id="categorie">
				<option value="">Toutes</option>
				<option value="1">Informatique</option>
				<option value="2">Ameublement</option>
				<option value="3">Vetements</option>
				<option value="4">Sport Loisirs</option>
			</select>
		</div>
	</form>
	<br>
	<!--PHOTO DE L'ARTICLE ---------  -->
	<form action="<%=request.getContextPath()%>/NouvelleEnchere.html"
		method="post">
		<label>Photo de l'article : </label> <input type="file"
			accept=".jpeg, .jpg, .jpe, .jfif, .jif">
	</form>
	<br>
	<!--PRIX DE DEPART DE L'ARTICLE ---------  -->
	<form action="<%=request.getContextPath()%>/NouvelleEnchere.html"
		method="post">
		<label for="quantity">Prix : </label> <input type="number"
			id="quantity" name="sprix" min="0" max="50" maxlength="4" size="4"
			value="<%=request.getParameter("sprix")%>">
		<!-- Ne pas supprimer le € -->
		€

	</form>
	<br>
	<!--DATE DE DEBUT D'ENCHERE DE L'ARTICLE  -->
	<form action="<%=request.getContextPath()%>/NouvelleEnchere.html"
		method="post">
		<label for="date">Début de l'enchère : </label> <input type="date"
			id="dateDeb" name="sdateDeb"
			value="<%=request.getParameter("sdatedeb")%>">

	</form>
	<br>
	<!--DATE DE FIN D'ENCHERE DE L'ARTICLE  -->
	<form action="<%=request.getContextPath()%>/NouvelleEnchere.html"
		method="post">
		<label for="date">Fin de l'enchère : </label> <input type="date"
			id="dateFin" name="sdateFin"
			value="<%=request.getParameter("sdatefin")%>">
	</form>
	<br>
	<br>
	<!-- RETRAIT -->

	<p>Retrait
	<form action="<%=request.getContextPath()%>/NouvelleEnchere.html"
		method="post">
		<input type="submit" value="Valider" /> <a
			href="<%=request.getContextPath()%>/nouvelleEnchere"><input
			type="button" value="Annuler" /></a> <a
			href="<%=request.getContextPath()%>/modifierEnchere"><input
			type="button" value="modifier" /></a>
	</form>
</body>
</html>