<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>

<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">  
	<title>Liste des matchs</title>
</head>

<body>
	<%@ include file="/WEB-INF/navbar.jsp"%>
	<h1 style="text-align : center; ">Liste des matchs</h1>
	
	 <form style="display:inline;" action="listedesmatchs" method="post">
    	<span>TRIER PAR :</span>
    	<span>Nom/Prenom </span><input  type="text" placeholder="nom/prénom" name="nomjoueur"/>
    	<span>Sexe </span><select type="text"name="sexejoueur">
    		<option>  </option>
    		<option> Homme </option>
    		<option> Femme </option>
    	</select>
    	<span>Tournoi </span><select type="text"name="nomtournoi">
    		<option value=null selected></option>
           	<c:forEach var="tournoi" items="${tournois}">
             <option value="${tournoi}" >
              	<c:out value="${tournoi}"/>
             </option>
            </c:forEach>
    	</select>
    	<span>Année </span><select type="text"name="anneetournoi">
    		<option value="" selected></option>
           	<c:forEach var="annee" items="${annees}">
             <option value="${annee}" >
              	<c:out value="${annee}"/>
             </option>
  			</c:forEach>
    	</select>
	 	<button name="action" type="submit">GO!</button></br>
     </form></br>
     </br>
     
	<table style="border:1px solid black;margin-left:auto;margin-right:auto;" >
    	<tr>
			<th>Epreuve</th>
			<th>Vainqueur</th>
			<th></th>
			<th>Finaliste</th>
			<th></th>
			<th>Set 1</th>
			<th>Set 2</th>
			<th>Set 3</th>
			<th>Set 4</th>
			<th>Set 5</th>
 		</tr>

		<c:forEach var="match" items="${matchs}">
			<form action="modifiermatch" method="get"> 
	            <tr>
		            <td><c:out value="${match.getIdMatch() }" /></td>
					<td><c:out value="${match.getPlayer1().getNom()}"/></td>
					<td><c:out value="${match.getPlayer1().getPrenom()}"/></td>
					<td><c:out value="${match.getPlayer2().getNom()}"/></td>
					<td><c:out value="${match.getPlayer2().getPrenom()}"/></td>
					<td><c:out value="${match.getSet1()}"/></td>
					<td><c:out value="${match.getSet2()}"/></td>
					<td><c:out value="${match.getSet3()}"/></td>
					<td><c:out value="${match.getSet4()}"/></td>
					<td><c:out value="${match.getSet5()}"/></td>
        		</tr>
			</form>
		</c:forEach>
	</table>
</body>
</html>
