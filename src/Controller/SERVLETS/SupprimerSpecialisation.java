package Controller.SERVLETS;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEANS.C_Garagiste;

/**
 * Servlet implementation class SupprimerSpecialisation
 */
@WebServlet("/SupprimerSpecialisation")
public class SupprimerSpecialisation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerSpecialisation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		C_Garagiste g = new C_Garagiste();
		
		// 1. Reception voiture
		String strId = request.getParameter("id");
		int id = Integer.parseInt(strId);
		g = g.trouver(id);
		g.setSpecialisation(null);
		
		// 2. Supprimer voiture
		if(g.modifier())
			response.sendRedirect("ListeUtilisateur");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
