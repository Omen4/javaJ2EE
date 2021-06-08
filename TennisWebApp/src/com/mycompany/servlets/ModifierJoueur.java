package com.mycompany.servlets;

import com.mycompany.metier.MetierTennis;
import com.mycompany.models.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ModifyPlayer
 */
@WebServlet("/modifierjoueur")
public class ModifierJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MetierTennis metier;
       
	
    public ModifierJoueur() {
        super();
     
    }
    
    public void init() throws ServletException{
    	this.metier = new MetierTennis();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long idJoueur = Long.parseLong(request.getParameter("idjoueur"));
		String action = request.getParameter("action");
		if (action.equals("Modifier")) {
			Player joueur = this.metier.lectureJoueur(idJoueur);
			request.setAttribute("joueur", joueur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/modifierjoueur.jsp").forward(request, response);
		
			
		}else if(action.equals("Supprimer")) {
			Player joueur = this.metier.lectureJoueur(idJoueur);
			this.metier.supprimerJoueur(joueur);
			response.sendRedirect(request.getContextPath() + "/listedesjoueurs");
			
		}else{
			response.getWriter().println("ERROR");
			response.getWriter().println(action);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long idJoueur = Long.parseLong(request.getParameter("idjoueur"));
		Player joueur = this.metier.lectureJoueur(idJoueur);
		this.metier.modifierJoueur(joueur);
		response.sendRedirect(request.getContextPath() + "/listedesjoueurs");
	}
}
