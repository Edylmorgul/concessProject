package Controller.SERVLETS;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEANS.C_TypeIntervention;

/**
 * Servlet implementation class SupprimerTypeIntervention
 */
@WebServlet("/SupprimerTypeIntervention")
public class SupprimerTypeIntervention extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerTypeIntervention() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// 0. Variables
		C_TypeIntervention typeInter = new C_TypeIntervention();
		
		// 1. Reception typeIntervention
		String strId = request.getParameter("id");
		int id = Integer.parseInt(strId);
		typeInter.setId(id);
				
		// 2. Supprimer typeIntervention
		if(typeInter.supprimer())
			response.sendRedirect("Views/administrateur/home.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
