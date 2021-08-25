package Controller.SERVLETS;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEANS.C_Client;
import Model.BEANS.C_Garagiste;
import Model.BEANS.C_Utilisateur;

/**
 * Servlet implementation class ListeUtilisateur
 */
@WebServlet("/ListeUtilisateur")
public class ListeUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// 0. Variables
		List<C_Utilisateur> liste = new LinkedList<>();
		
		// 1. Liste des utilisateur 		
		liste = (List<C_Utilisateur>) C_Utilisateur.lister();
		request.setAttribute("liste", liste);
		request.getRequestDispatcher("/Views/administrateur/utilisateur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		// 0. Variables
		
		// 1. Vérifier submit
		if (request.getParameter("submit") != null) {
			String strTypeUtilisateur = request.getParameter("typeUtilisateur");
			
			// 2. Convertir éléments
			int typeUtilisateur = Integer.parseInt(strTypeUtilisateur);
			
			// 3. Afficher liste selon type utilisateur
			// 3.1 Client
			if(typeUtilisateur == 1) {
				List<C_Client> liste = new LinkedList<>();
				liste = (List<C_Client>) C_Client.lister();
				request.setAttribute("liste", liste);
				request.getRequestDispatcher("/Views/administrateur/utilisateur.jsp").forward(request, response);
			}
			
			// 3.2 Garagiste
			else if(typeUtilisateur == 2) {
				List<C_Garagiste> liste = new LinkedList<>();
				liste = (List<C_Garagiste>) C_Garagiste.lister();
				request.setAttribute("liste", liste);
				request.getRequestDispatcher("/Views/administrateur/utilisateur.jsp").forward(request, response);
			}
			
			// 3.3 Tout
			else {
				List<C_Utilisateur> liste = new LinkedList<>();
				liste = (List<C_Utilisateur>) C_Utilisateur.lister();
				request.setAttribute("liste", liste);
				request.getRequestDispatcher("/Views/administrateur/utilisateur.jsp").forward(request, response);
			}
		}
	}
}
