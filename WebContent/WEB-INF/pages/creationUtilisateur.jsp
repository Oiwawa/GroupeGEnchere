<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="fr.eni.javaee.encheres.bo.Utilisateur"%>

<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<title>Mon profil</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value='/inc/form.css'/>" />
</head>
<body>

	<h1>Mon profil</h1>

	<form action="CreationUtilisateur" method="post">
		<fieldset>
		<br><br>
			<legend>Inscription</legend>
			<div>
				<label for="pseudo"> Pseudo : <span class="requis">*</span></label>
				<input type="text" name="pseudo" id="pseudo" required="required"
					size="20" maxlength="20" value="Test1"> <span
					class="erreur">${erreurs['pseudo']}</span> <br> <br> <label
					for="prenom"> Prenom :<span class="requis">*</span></label> <input
					type="text" name="prenom" id="prenom" required="required"
					value="prenomtest"> <span class="erreur">${erreurs['prenom']}</span>

				<br> <br> <label for="telephone"> Telephone :</label> <input
					type="text" name="telephone" id="telephone" value="0648346228">
				<span class="erreur">${erreurs['telephone']}</span> <br> <br>
				<label for="codepostal"> Code postal : <span class="requis">*</span></label>
				<input type="text" name="codepostal" id="codepostal"
					required="required" value="44600"> <br> <br> <label
					for="mdp"> Mot de passe :<span class="requis">*</span></label> <input
					type="password" name="mdp" id="mdp" required="required"
					value="mdptest" size="20" maxlength="20"> <span
					class="erreur">${erreurs['mdp']}</span> <br> <br>
			</div>

			<div>
				<label for="nom"> Nom :<span class="requis">*</span></label> <input
					type="text" name="nom" id="nom" required="required" value="nomtest">
				<span class="erreur">${erreurs['nom']}</span> <br> <br> <label
					for="email"> Email :<span class="requis">*</span>
				</label> <input type="text" name="email" id="email" required="required"
					value="test.test@laposte.net" size="20" maxlength="60"> <span
					class="erreur">${erreurs['email']}</span> </br> <br> <br> <label
					for="rue"> Rue :<span class="requis">*</span></label> <input
					type="text" name="rue" id="rue" required="required" value="ruetest">
				<br> <br> <label for="ville"> Ville :<span
					class="requis">*</span></label> <input type="text" name="ville" id="ville"
					required="required" value="villetest"> <br> <br>

				<label for="confirmation"> Confirmation mot de passe :<span
					class="requis">*</span>
				</label> <input type="password" name="confirmation" id="confirmation"
					required="required" value="mdptest" size="20" maxlength="20">
				<span class="erreur">${erreurs['confirmation']}</span> <br> <br>
			</div>

			<div>
				<button type="submit" class="sansLabel">Creer</button>
				<button type="button" onreset="reset">Annuler</button>
			</div>
			<br>
			<br>
			<p>
				<% if(request.getAttribute("message") != null){
					out.println((String) request.getAttribute("message"));
				} %>
			</p>
		</fieldset>



	</form>



</body>
</html>