package com.mycompany.servlets;

import com.mycompany.metier.MetierTennis;
import com.mycompany.models.Player;
import com.mycompany.models.Tournoi;

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

@WebServlet("/listedestournois")
public class ListerTournois extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MetierTennis metier;
    
    public ListerTournois() {
        super();
       
    }
    
    @Override
    public void init() throws ServletException{
    	this.metier = new MetierTennis();
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("tournois", this.metier.listerTournois());
		this.getServletContext().getRequestDispatcher("/WEB-INF/listedestournois.jsp").forward(request, response);	
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Tournoi>tournois = metier.listerTournois();
		String nom = request.getParameter("nomtournoi");
		String code = request.getParameter("codetournoi");
		tournois= tournois.stream()
					.filter(item -> item.getNomTournoi().toLowerCase().contains(nom.toLowerCase()))	
					.filter(item -> item.getCodeTournoi().toLowerCase().contains(code.toLowerCase()))
					.collect(Collectors.toList());
	
		request.setAttribute("tournois", tournois);
		this.getServletContext().getRequestDispatcher("/WEB-INF/listedestournois.jsp").forward(request, response);
	}
	
}
