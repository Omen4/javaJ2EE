<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!doctype html>
<html lang="fr">
	<head>
    	<meta charset="utf-8">  
    	<title>Modifier votre tournoi</title>
  	</head>
  	
  	<body>
    	<h1>Modifier un tournoi</h1>
		<form method="post" action="modifiertournoi" >
		
			<label for="txtNomTournoi">Nom du tournoi</label>
			<input type="text" name="txtNomTournoi" value="${tournoi.getNomTournoi()}" required>
			
			<label for="txtCodeTournoi">Code du tournoi</label>
			<input type="text" name="txtCodeTournoi" value="${tournoi.getCodeTournoi()}" required>
			
		  	<button type="submit">Modifier</button>
		</form>
	</body>
</html>
