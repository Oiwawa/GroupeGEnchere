<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Les objets sont nos amis</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/nouvelleVente.html">
	<div>
		<label for="article">Article : </label>
		<input type="text" id="nomArt" name="sarticle" value="<%=request.getParameter("sarticle") %>">
	</div>
	</form>
	<form action="<%=request.getContextPath()%>/nouvelleVente.html">
	<div>
		<label for="description">Description : </label>
		<input for="description" id="description" name="sdescription" value="<%=request.getParameter("sdescription")%>"> 
	</div>	
	 </form>
	 <form action="<%=request.getContextPath()%>/nouvelleVente.html">
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
	 </form>
	 
</body>
</html>