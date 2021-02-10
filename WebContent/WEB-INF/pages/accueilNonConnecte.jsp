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
			<a href="#">S'inscrire</a> <span> - </span> <a href="#">Se
				connecter</a>

		</div>
	</header>
	<section>
		<div>
			<h3>Liste des encheres</h3>
		</div>

		<!-- FILTRE---------------------------- -->
		<p>Filtres</p>
		<form action="Accueil.html">
			<input type="text" placeholder="Le nom de l'article contient"
				name="recherche">
			<button type="submit" value="Rechercher">Rechercher</button>
		</form>
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

		<!-- LISTE -->



	</section>
	<footer> </footer>
</body>
</html>