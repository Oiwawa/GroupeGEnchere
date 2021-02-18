<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href= "<c:url value='/inc/form.css'/>" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/light.css">
<meta charset="UTF-8">
<title>Page de connection</title>
</head>
<body>

<h1 align="center">
			<a href="<%=request.getContextPath()%>/Accueil.html">Les objets
				sont nos amis</a>
		</h1>
<h2 align="center">Entrez votre identifiant et votre mot de passe</h2>
   <br>
   <div align="center">
   	<fieldset>
		<br><br>
			<legend>Connexion</legend>
	<form action="ServletConnection" method="post" >
		<p>
			<label for="identifiant"> Identifiant </label>
			<input type="text" name="identifiant" id="identifiant"
			size="40" maxlength= "30" required/>
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
	</div>

</body>
</html>