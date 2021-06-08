package com.mycompany.servlets;

import com.mycompany.metier.MetierTennis;
import com.mycompany.models.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/listedesjoueurs")
public class ListerJoueurs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MetierTennis metier;
    
    public ListerJoueurs() {
        super();
       
    }
    
    @Override
    public void init() throws ServletException{
    	this.metier = new MetierTennis();
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("joueurs", this.metier.listerJoueurs());
		this.getServletContext().getRequestDispatcher("/WEB-INF/listedesjoueurs.jsp").forward(request, response);	
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Player>joueurs = metier.listerJoueurs();
		String recherche = request.getParameter("nomjoueur");
		String sexe = request.getParameter("sexejoueur");
		System.out.println(sexe);
		
		if(sexe == null) {
			joueurs= joueurs.stream()
					.filter(item -> (item.getNom().toLowerCase().contains(recherche.toLowerCase())
							|| item.getPrenom().toLowerCase().contains(recherche.toLowerCase()))
		    		 )
					.collect(Collectors.toList());
		}else{
			joueurs= joueurs.stream()
					.filter(item -> (item.getNom().toLowerCase().contains(recherche.toLowerCase())
									|| item.getPrenom().toLowerCase().contains(recherche.toLowerCase())))
					.filter(item ->item.isMale() == sexe.equals("Homme"))
					.collect(Collectors.toList());
			System.out.println(sexe.equals("Homme"));
		}
		request.setAttribute("joueurs", joueurs);
		this.getServletContext().getRequestDispatcher("/WEB-INF/listedesjoueurs.jsp").forward(request, response);
	}
}
