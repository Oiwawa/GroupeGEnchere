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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/light.css"></head>
<body>
<h1 align="center">
			<a href="<%=request.getContextPath()%>/Accueil.html">Les objets
				sont nos amis</a>
		</h1>
	<h2 align="center">Mon profil</h2>

	<form action="CreationUtilisateur" method="post">
		<fieldset>
		<br><br>
			<legend>Inscription</legend>
			<div>
				<label for="pseudo"> Pseudo : <span class="requis">*</span></label>
				<input type="text" name="pseudo" id="pseudo" required="required"
					size="20" maxlength="20"> 
				<br>
				
				<label for="nom"> Nom :<span class="requis">*</span></label> 
				<input type="text" name="nom" id="nom" required="required">
				 <br> 
				 
				<label for="prenom"> Prenom :<span class="requis">*</span></label> 
				<input type="text" name="prenom" id="prenom" required="required"> 
				<br>
				
				<label for="telephone"> Telephone :</label> 
				<input type="text" name="telephone" id="telephone" >
				<br>  
				
				<label for="email"> Email :<span class="requis">*</span> </label>
				<input type="text" name="email" id="email" required="required"
					size="20" maxlength="60"> 
				<br>
				 
				<label for="rue"> Rue :<span class="requis">*</span></label> 
				<input type="text" name="rue" id="rue" required="required">
				<br>
				
				<label for="ville"> Ville :<span class="requis">*</span></label>
				<input type="text" name="ville" id="ville"
					required="required"> 
				<br> 
				
				<label for="codepostal"> Code postal : <span class="requis">*</span></label>
				<input type="text" name="codepostal" id="codepostal" required="required"> 
				<br> 

 				<label for="mdp"> Mot de passe :<span class="requis">*</span></label>
 				<input type="password" name="mdp" id="mdp" required="required"
					 size="20" maxlength="20"> 
				<br>
				
				<label for="confirmation"> Confirmation mot de passe :<span
					class="requis">*</span> </label> 
				<input type="password" name="confirmation" id="confirmation"
					required="required" size="20" maxlength="20">
				<br> 
				
			</div>

			<div>
				<button type="submit" class="sansLabel">Creer</button>
				<button type="button" href="<%=request.getContextPath()%>/Accueil.html">Annuler</button>
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