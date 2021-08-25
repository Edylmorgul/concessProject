package Controller.SERVLETS;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEANS.C_Rdv;
import Model.BEANS.C_TypeIntervention;

/**
 * Servlet implementation class ChercherDispos
 */
@WebServlet("/ChercherDispos")
public class ChercherDispos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChercherDispos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    
	    LocalDate jour = LocalDate.parse(request.getParameter("jour"));
	    C_TypeIntervention typeIntervention = new C_TypeIntervention();
	    typeIntervention = typeIntervention.trouver(Integer.parseInt(request.getParameter("typeIntervention")));
	    boolean checkflag = Boolean.valueOf(request.getParameter("checkflag"));
	    
		
	    response.getWriter().write(C_Rdv.GenererMomentsDisponibles(jour, typeIntervention, checkflag));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
