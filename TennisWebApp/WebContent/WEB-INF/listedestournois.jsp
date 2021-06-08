<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>

<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">  
	<title>Liste des Tournois</title>
</head>

<body>
	<%@ include file="/WEB-INF/navbar.jsp"%>
	<h1 style="text-align : center; ">Liste des tournois</h1>
	
	 <form style="display:inline;" action="listedestournois" method="post">
    	<span>TRIER PAR :</span>
    	<span>Nom </span><input  type="text" placeholder="nom du tournoi" name="nomtournoi"/>
    	<span>Code </span><input  type="text" placeholder="code du tournoi" name="codetournoi"/>
	 	<button name="action" type="submit">GO!</button></br>
	 	<span>OU: </span><a href="ajoutertournoi" role="button">Ajouter un tournoi</a>
     </form></br>
     </br>
     
	<table style="border:1px solid black;margin-left:auto;margin-right:auto;">
    	<tr>
      		<th>#</th>
			<th>Tournoi</th>
			<th>Code</th>
			<th></th>
 		</tr>
 		

		<c:forEach var="tournoi" items="${tournois}">
			<form action="modifiertournoi" method="get"> 
	            <tr>
		            <td><c:out value="${tournoi.getIdTournoi() }" /></td>
					<td><c:out value="${tournoi.getNomTournoi() }"/></td>
					<td><c:out value="${tournoi.getCodeTournoi()}"/></td>
					<td>
						<input type="hidden" name="idtournoi" value="${tournoi.getIdTournoi() }" />
						<button type="submit" name="action" value="modifiertournoi" >Modifier</button>
	                    <button type="submit" name="action" value="supprimertournoi">Supprimer</button>
	              	</td>
        		</tr>
			</form>
		</c:forEach>
	</table>
</body>
</html>
