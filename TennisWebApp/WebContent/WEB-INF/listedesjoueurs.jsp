<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>

<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">  
	<title>Liste des joueurs</title>
</head>

<body>
	<%@ include file="/WEB-INF/navbar.jsp"%>
	<h1 style="text-align : center; ">Liste des joueurs</h1>
	
 	
    <form style="display:inline;" action="listedesjoueurs" method="post">
    	<span>TRIER PAR :</span>
    	<span>Nom/Prenom: </span><input  type="text" placeholder="nom du joueur" name="nomjoueur"/>
    	<span>Sexe: </span><select type="text"name="sexejoueur">
    		<option>  </option>
    		<option> Homme </option>
    		<option> Femme </option>
    	</select>
	 	<button name="action" type="submit">GO!</button></br>
	 	<span>OU: </span><a href="ajouterjoueur" role="button">Ajouter un joueur</a>
     </form></br>
	
	<table style="border:1px solid black;margin-left:auto;margin-right:auto;">
    	<tr>
      		<th>#</th>
			<th>Nom</th>
			<th>Prenom</th>
			<th>Sexe</th>
			<th></th>
 		</tr>

		<c:forEach var="joueur" items="${joueurs}">
			<form action="modifierjoueur" method="get"> 
	            <tr>
		            <td><c:out value="${joueur.id }" /></td>
					<td><c:out value="${joueur.nom }"/></td>
					<td><c:out value="${joueur.prenom}"/></td>
					<td><c:out value="${joueur.isMale()? \"H\" : \"F\" }"/></td>
					<td>
						<input type="hidden" name="idjoueur" value="${ joueur.id }" />
						<button type="submit" name="action" value="Modifier" >Modifier</button>
	                    <button type="submit" name="action" value="Supprimer">Supprimer</button>
	              	</td>
        		</tr>
			</form>
		</c:forEach>
	</table>
</body>
</html>
