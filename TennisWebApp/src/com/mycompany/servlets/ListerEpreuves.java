package com.mycompany.servlets;

import com.mycompany.metier.MetierTennis;
import com.mycompany.models.Epreuve;
import com.mycompany.models.Match;

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

@WebServlet("/listedesepreuves")
public class ListerEpreuves extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MetierTennis metier;
	List<Epreuve>epreuves;
	String sexetournoi;
	String nomtournoi;
	String txtanneetournoi;
	Integer anneetournoi;
    
    public ListerEpreuves() {
        super();
       
    }
    
    @Override
    public void init() throws ServletException{
    	this.metier = new MetierTennis();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("tournois", metier.getTournois());
		request.setAttribute("annees", metier.getAnnees());
		request.setAttribute("epreuves", this.metier.listerEpreuves());
		this.getServletContext().getRequestDispatcher("/WEB-INF/listedesepreuves.jsp").forward(request, response);	
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		epreuves = metier.listerEpreuves();
		sexetournoi = request.getParameter("sexetournoi");
		nomtournoi = request.getParameter("nomtournoi");
		txtanneetournoi = request.getParameter("anneetournoi");
		
		if(txtanneetournoi.equals("")) {
			anneetournoi = null;
		}else {
			anneetournoi = Integer.parseInt(txtanneetournoi);
		}
		
		if(sexetournoi.equals("")) {
			sexetournoi=null;
		}
		
		if(nomtournoi.equals("")) {
			nomtournoi=null;
		}
		
		epreuves = epreuves.stream()
			.filter(item ->((sexetournoi == null) || (item.isMale() == sexetournoi.equals("Homme")))
			&&((nomtournoi == null) || (item.getTournoi().getNomTournoi().toLowerCase().equals(nomtournoi.toLowerCase())))
			&&((anneetournoi == null) || (item.getAnneeEpreuve()==((int)anneetournoi))))
			.collect(Collectors.toList());
		System.out.println(sexetournoi);
		System.out.println(nomtournoi);
		System.out.println(anneetournoi);
		request.setAttribute("tournois", metier.getTournois());
		request.setAttribute("annees", metier.getAnnees());
		request.setAttribute("epreuves", epreuves);
		this.getServletContext().getRequestDispatcher("/WEB-INF/listedesepreuves.jsp").forward(request, response);
	}
	
	
}
