<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href= "<c:url value='/inc/form.css'/>" />
<meta charset="UTF-8">
<title>Page de connection</title>
</head>
<body>
	<!-- Titre -->
	<h1>
		<a href="<%=request.getContextPath()%>/Accueil.html">Les objets
			sont nos amis</a>
	</h1>
<h1>Entrez votre identifiant et votre mot de passe</h1>
   
   	<fieldset>
		<br><br>
			<legend>Connexion</legend>
	<form action="ServletConnection" method="post" >
		<p>
			<label for="identifiant"> Identifiant </label>
			<input type="text" name="identifiant" id="identifiant"
			size="40" maxlength= "10" required/>
		</p>
		<p>
			<label for="motdepasse"> Mot de passe </label>
			<input type="password" name="mdp" id="mdp"
			size="40" required/>
		</p>
			<button type="submit">Connexion</button>
		<p>
			<% if(request.getAttribute("message") != null){
    			out.println((String)request.getAttribute("message"));
			}%>
		</p>
	</form>
	</fieldset>

</body>
</html>