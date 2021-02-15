<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page de connection</title>
</head>
<body>


   
   
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
			<%
    			out.println((String)request.getAttribute("message"));
			%>
		</p>
	</form>

</body>
</html>