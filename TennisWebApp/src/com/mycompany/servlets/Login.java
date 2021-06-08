package com.mycompany.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycompany.metier.MetierTennis;
import com.mycompany.models.User;

@WebServlet({"/login"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	private MetierTennis metierTennis;
	String action = null;
	HttpSession session = null;
	User connectingUser = null;
	
    public Login() {
        super();
       
    }
    
    @Override
    public void init() throws ServletException{
    	this.metierTennis = new MetierTennis();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail=request.getParameter("email");
		String password=request.getParameter("motdepasse");
		connectingUser = metierTennis.getDaoUser().search(mail,password);
		
		if(connectingUser != null){
			session = request.getSession(true);
			session.setAttribute("mail", mail);
			response.sendRedirect(request.getContextPath()+"/listedesjoueurs");
			return;	
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session.invalidate();
		connectingUser = null;
		action = null;
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

	}
}
	
