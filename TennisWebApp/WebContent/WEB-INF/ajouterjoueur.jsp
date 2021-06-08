<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!doctype html>
<html lang="fr">
	<head>
    	<meta charset="utf-8">  
    	<title>Ajouter votre joueur</title>
  	</head>
  	
  	<body>
    	<h1>Ajouter un joueur</h1>
		<form method="post" action="ajouterjoueur" >
		
			<label for="txtNom">Nom du joueur</label>
			<input type="text" name="txtNom" value="${joueur.getNom()}" required>
			
			<label for="txtPrenom">Prénom du joueur</label>
			<input type="text" name="txtPrenom" value="${joueur.getPrenom()}" required>
			
		    <label for="opSexe">Sexe</label>
		    <select name="opSexe" required>
			    <option disabled  >Selectioner...</option>
			    <option <c:if test="${joueur.isMale()? \"H\" : \"F\"}">selected</c:if> value="F">Femme</option>
				<option <c:if test="${joueur.isMale()? \"H\" : \"F\"}">selected</c:if> value="H">Homme</option>
		   	</select>
		  	<button type="submit">Ajouter</button>
		</form>
	</body>
</html>
