package com.mycompany.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.metier.MetierTennis;
import com.mycompany.models.Player;


@WebServlet("/ajouterjoueur")
public class AjouterJoueur extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private MetierTennis metierTennis;

    public void init()
    {
    	this.metierTennis = new MetierTennis();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterjoueur.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	
    	String nom = request.getParameter("txtNom");
    	String prenom = request.getParameter("txtPrenom");
    	boolean isMale = request.getParameter("opSexe").equals("H");
    	Player joueur = new Player();
    	joueur.setNom(nom);
    	joueur.setPrenom(prenom);
    	joueur.setMale(isMale);
    	metierTennis.ajouterJoueur(joueur);
    	
        response.sendRedirect(request.getContextPath() + "/listedesjoueurs");
    }
}