<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>

<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">  
	<title>Liste des epreuves</title>
</head>

<body>
	<%@ include file="/WEB-INF/navbar.jsp"%>
	<h1 style="text-align : center; ">Liste des epreuves</h1>
	<form style="display:inline;" action="listedesepreuves" method="post">
    	<span>TRIER PAR :</span>
    	<span>Sexe </span><select type="text" name="sexetournoi">
    		<option>  </option>
    		<option> Homme </option>
    		<option> Femme </option>
    	</select>
    	<span>Tournoi </span><select type="text" name="nomtournoi">
    		<option value="" selected></option>
           	<c:forEach var="tournoi" items="${tournois}">
             <option value="${tournoi}" >
              	<c:out value="${tournoi}"/>
             </option>
            </c:forEach>
    	</select>
    	<span>Année </span><select type="text" name="anneetournoi">
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
     
	<table style="border:1px solid black;margin-left:auto;margin-right:auto;">
    	<tr>
      		<th>#</th>
			<th>Annee</th>
			<th>Type</th>
			<th>Tournoi</th>
 		</tr>

		<c:forEach var="epreuve" items="${epreuves}">
			<form action="modifierepreuve" method="get"> 
	            <tr>
		            <td><c:out value="${epreuve.getIdEpreuve() }" /></td>
					<td><c:out value="${epreuve.getAnneeEpreuve() }"/></td>
					<td><c:out value="${epreuve.isMale()? \"H\" : \"F\" }"/></td>
					<td><c:out value="${epreuve.getTournoi().getNomTournoi()}"/></td>
        		</tr>
			</form>
		</c:forEach>
	</table>
</body>
</html>
