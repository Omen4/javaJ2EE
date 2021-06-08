<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
	<nav>
    <div id="navbarsExampleDefault">
	    <ul>
	     	<li style=" display: inline;"><a>Menu :</a></li>
	     	<li style=" display: inline;"><a  href="/TennisWebApp/listedesjoueurs">Joueurs</a></li>
	     	<li style=" display: inline;"><a  href="/TennisWebApp/listedestournois">Tournois</a></li>
	     	<li style=" display: inline;"><a  href="/TennisWebApp/listedesmatchs">Matchs</a></li>
	     	<li style=" display: inline;"><a  href="/TennisWebApp/listedesepreuves">Épreuves</a></li>
	     </ul>
	     <form action="/TennisWebApp/login" method="post">
		     <ul>	
		     	<li style=" display: inline;float:right"> 
		      		<button type="submit" >Deconnexion</button>
		    	</li>
		    	<li style=" display: inline;float:right"><a  href="login">${sessionScope.mail}</a>&nbsp;&nbsp;</li>
		    </ul>
	    </form>
		</br>
	   
    </div>
 	</nav>
  
</body>
</html>