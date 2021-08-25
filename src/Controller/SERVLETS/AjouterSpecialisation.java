package Controller.SERVLETS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEANS.C_Garagiste;
import Model.BEANS.C_Specialisation;
import Model.BEANS.Global;

/**
 * Servlet implementation class CreerSpecialisation
 */
@WebServlet("/AjouterSpecialisation")
public class AjouterSpecialisation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private C_Garagiste gara = null;
	List<C_Specialisation> liste = new ArrayList<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterSpecialisation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//0. Variables
		liste = C_Specialisation.lister();
				
		// 1. Récupérer id utilisateur
		String strId = request.getParameter("id");
		int id = Integer.parseInt(strId);
				
		// 2. Récupérer info utilisateur
		gara = new C_Garagiste();
		gara = gara.trouver(id);
		
		// 3. Envoi des données à la page jsp
		request.setAttribute("liste", liste);
		request.getRequestDispatcher("/Views/administrateur/specialisation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		// 1. Vérifier submit
		if (request.getParameter("submit") != null) {
			String strTypeSpe = request.getParameter("typeSpe");
			liste = C_Specialisation.lister();
			
			// 2. Envoi des données
			int typeSpe = Global.tryParseInt(strTypeSpe);
			C_Specialisation specialisation = new C_Specialisation();
			specialisation = specialisation.trouver(typeSpe);
			gara.setSpecialisation(specialisation);
			
			// 2.1 Update garagiste
			if(gara.modifier())
				response.sendRedirect("ListeUtilisateur");
						
			else {
				// 2.1 Refresh liste
				request.setAttribute("erreur", "Une erreur est survenue lors de l'ajout de la spécialité" );
				request.getRequestDispatcher("/Views/administrateur/specialisation.jsp").forward(request, response);
			}
		}			
	}
}
