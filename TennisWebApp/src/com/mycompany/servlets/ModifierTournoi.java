package com.mycompany.servlets;

import com.mycompany.metier.MetierTennis;

import com.mycompany.models.Tournoi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/modifiertournoi")
public class ModifierTournoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MetierTennis metier;
       
	
    public ModifierTournoi() {
        super();
     
    }
    
    public void init() throws ServletException{
    	this.metier = new MetierTennis();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long idTournoi = Long.parseLong(request.getParameter("idtournoi"));
		String action = request.getParameter("action");
		if (action.equals("modifiertournoi")) {
			Tournoi tournoi = this.metier.lectureTournoi(idTournoi);
			request.setAttribute("tournoi", tournoi);
			this.getServletContext().getRequestDispatcher("/WEB-INF/modifiertournoi.jsp").forward(request, response);
		
			
		}else if(action.equals("supprimertournoi")) {
			Tournoi tournoi = this.metier.lectureTournoi(idTournoi);
			this.metier.supprimerTournoi(tournoi);
			response.sendRedirect("TennisWebApp/listedestournois");
			
			
		}else{
			response.getWriter().println("ERROR");
			response.getWriter().println(action);
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long idTournoi = Long.parseLong(request.getParameter("idTournoi"));
		Tournoi tournoi = this.metier.lectureTournoi(idTournoi);
		this.metier.modifierTournoi(tournoi);
		response.sendRedirect("/ListerTournois");
	}
}
