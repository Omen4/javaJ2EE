package com.mycompany.servlets;

import com.mycompany.metier.MetierTennis;
import com.mycompany.models.Match;
import com.mycompany.models.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet("/listedesmatchs")
public class ListerMatchs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MetierTennis metier;
	List<Match>matchs;
	String nomjoueur;
	String sexejoueur;
	String nomtournoi;
	String txtanneetournoi;
	Integer anneetournoi;
    
    public ListerMatchs() {
        super();
       
    }
    
    @Override
    public void init() throws ServletException{
    	this.metier = new MetierTennis();
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("tournois", metier.getTournois());
		request.setAttribute("annees", metier.getAnnees());
		request.setAttribute("matchs", this.metier.listerMatchs());
		this.getServletContext().getRequestDispatcher("/WEB-INF/listedesmatchs.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		matchs = metier.listerMatchs();
		nomjoueur = request.getParameter("nomjoueur");
		sexejoueur = request.getParameter("sexejoueur");
		nomtournoi = request.getParameter("nomtournoi");
		txtanneetournoi = request.getParameter("anneetournoi");
		
		if(txtanneetournoi.equals("")) {
			anneetournoi = null;
		}else {
			anneetournoi = Integer.parseInt(txtanneetournoi);
		}
		
		if(sexejoueur.equals("")) {
			sexejoueur=null;
		}
		
		if(nomtournoi.equals("")) {
			nomtournoi=null;
		}
		
		matchs = matchs.stream()
		     .filter(item -> (item.getPlayer1().getNom().toLowerCase().contains(nomjoueur.toLowerCase()))
		    		 || (item.getPlayer1().getPrenom().toLowerCase().contains(nomjoueur.toLowerCase()))
		    		 || (item.getPlayer2().getNom().toLowerCase().contains(nomjoueur.toLowerCase()))
		    		 || (item.getPlayer2().getPrenom().toLowerCase().contains(nomjoueur.toLowerCase()))
		    		 	&&((sexejoueur == null) || (item.getPlayer1().isMale() == sexejoueur.equals("Homme")))
		    		 	&&((nomtournoi == null) || (item.getEpreuve().getTournoi().getNomTournoi().toLowerCase().equals(nomtournoi.toLowerCase())))
		    		 	&&((anneetournoi == null) || (item.getEpreuve().getAnneeEpreuve()==((int)anneetournoi)))
		    		 )
		     .collect(Collectors.toList());
		System.out.println(nomjoueur);
		System.out.println(sexejoueur);
		System.out.println(nomtournoi);
		System.out.println(anneetournoi);
		request.setAttribute("tournois", metier.getTournois());
		request.setAttribute("annees", metier.getAnnees());
		request.setAttribute("matchs", matchs);
		this.getServletContext().getRequestDispatcher("/WEB-INF/listedesmatchs.jsp").forward(request, response);
	}
}
