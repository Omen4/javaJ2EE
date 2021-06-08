<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
  <title>Connexion</title>
  </head>
  <body>
  
    <form method="get" action="login">
  		<h1>Identifiez-vous</h1>
  		<label for="inputEmail">Adresse e-mail</label>
  		<input type="email" id="inputEmail" name="email" placeholder="Addresse mail" required autofocus>
  		<label for="inputPassword">Mot de passe</label>
  		<input type="password" id="inputPassword" name="motdepasse" placeholder="Mot de passe" required>
  		<input type="hidden" name="action" value="login" />
  		<button type="submit" >Connexion</button>
	</form>  
	
</body>
</html>


