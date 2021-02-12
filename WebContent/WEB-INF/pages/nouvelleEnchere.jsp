<%@page import="fr.eni.javaee.encheres.bo.Categorie"%>
<%@page import="java.util.List"%>
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

<% Utilisateur connectedUser = (Utilisateur) session.getAttribute("ConnectedUser"); %>
	<h1>Nouvelle Vente</h1>
	<br>
	<!--NOM DE L'ARTICLE ---------  -->
	<form action="<%=request.getContextPath()%>/NouvelleEnchere.html"
		method="post">
		<div>
			<label for="article">Article : </label> <input type="text"
				id="sarticle" name="sarticle"
				value="<%=request.getParameter("sarticle")%>" placeholder="Nom">
		</div>
	</form>
	<br>
	<!--DESCRIPTION DE L'ARTICLE ---------  -->
	<form action="<%=request.getContextPath()%>/NouvelleEnchere.html"
		method="post">
		<div>
			<label for="description">Description : </label>
			 <textarea rows="5" cols="30"  size="50" for="description"
				id="sdescription" name="sdescription"
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
				id="scategorie">
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
			id="quantity" name="sprix" min="0" max="100000" maxlength="4" size="4"
			value="<%=request.getParameter("sprix")%>">
		<!-- Ne pas supprimer le € -->
		€

	</form>
	<br>
	<!--DATE DE DEBUT D'ENCHERE DE L'ARTICLE  -->
	<form action="<%=request.getContextPath()%>/NouvelleEnchere.html"
		method="post">
		<label for="date">Début de l'enchère : </label> <input type="date" id="sdateDeb"
			name="sdateDeb" value="<%=request.getParameter("sdatedeb")%>">

	</form>
	<br>
	<!--DATE DE FIN D'ENCHERE DE L'ARTICLE  -->
	<form action="<%=request.getContextPath()%>/NouvelleEnchere.html"
		method="post">
		<label for="date">Fin de l'enchère : </label> <input type="date" id="sdateFin"
			name="sdateFin" value="<%=request.getParameter("sdatefin")%>">
	</form>
	<br>
	<br>
	<!-- RETRAIT -->
	
	<p> Retrait
	<%--  
	 <div >
      <h1>Retrait de l'objet</h1>
      <div >
          <label for="rue">Rue :</label>
          <input type="text" name="srue" id="srue" maxlength="100" value="<%=connectedUser.getRue() %>" required>
      </div>

      <div >
          <label for="cp">Code Postale :</label>
          <input class="input" type="text" name="scodepostal" id="scodepostal" 
          step="1000" min="0" maxlength="5" value="<%=connectedUser.getCodePostal() %>" required>
      </div>

      <div >
          <label for="ville">Ville :</label>
          <input class="input" type="text" name="sville" id="sville" value="<%=connectedUser.getVille() %>" required>
      </div>
    </div>
	 --%>
	
	<!-- Erreurs -->
	<div>
	<%
			List<Integer> listeCodesErreur = (List<Integer>)request.getAttribute("listeCodesErreur");
			if(listeCodesErreur!=null)
			{
		%>
				<p style="color:red;">Erreur, l'article n'a pas pu être ajouté :</p>
		<%
				for(int codeErreur:listeCodesErreur)
				{
		%>
					<p><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
		<%	
				}
			}
		%>
	</div>
	<div>
			<form action="<%=request.getContextPath()%>/NouvelleEnchere.html"
			 method="post">
				<a href="<%=request.getContextPath()%>/modifierEnchre"></a><input type="submit" value="Valider"/>
				<a href="<%=request.getContextPath()%>/nouvelleEnchere"><input type="button" value="Annuler"/></a>
				
			</form>
			</div>
</body>
</html>