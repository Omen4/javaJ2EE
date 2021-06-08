package com.mycompany.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.metier.MetierTennis;
import com.mycompany.models.Player;
import com.mycompany.models.Tournoi;


@WebServlet("/ajoutertournoi")
public class AjouterTournoi extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private MetierTennis metierTennis;

    public void init()
    {
    	this.metierTennis = new MetierTennis();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutertournoi.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	
    	String nomTournoi = request.getParameter("txtNomTournoi");
    	String codeTournoi = request.getParameter("txtCodeTournoi");
    	Tournoi tournoi = new Tournoi();
    	tournoi.setNomTournoi(nomTournoi);
    	tournoi.setCodeTournoi(codeTournoi);
    	metierTennis.ajouterTournoi(tournoi);
    	
        response.sendRedirect(request.getContextPath() + "/listedestournois");
    }
}