package Controller.SERVLETS;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEANS.C_Utilisateur;

/**
 * Servlet implementation class ReactiverCompte
 */
@WebServlet("/ReactiverCompte")
public class ReactiverCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReactiverCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// 1. Récupérer id utilisateur
		String strId = request.getParameter("id");
		int id = Integer.parseInt(strId);
				
		// 2. Récupérer info utilisateur
		C_Utilisateur uti = new C_Utilisateur();
		uti = uti.trouver(id);
				
		// 3. Modifier info utilisateur
		uti.setActif(1);
		if(uti.modifier())
			request.getRequestDispatcher("/ListeUtilisateur").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
