package Controller.SERVLETS;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEANS.C_Specialisation;
import Model.BEANS.C_TypeIntervention;
import Model.BEANS.Global;

/**
 * Servlet implementation class CreerTypeIntervention
 */
@WebServlet("/CreerTypeIntervention")
public class CreerTypeIntervention extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreerTypeIntervention() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // Vérifier champs vide
    private boolean estVide(String typeIntervention, String honoraire, String dureePrev) {
    	
    	boolean erreur = false;
    	if(typeIntervention == null || typeIntervention.equals(""))
    		erreur = true;
    	
    	if(honoraire == null || honoraire.equals(""))
			erreur = true;
		   	
    	if(dureePrev == null || dureePrev.equals(""))
    		erreur = true;
    	    	
    	return erreur;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setAttribute("typesIntervention", C_TypeIntervention.lister());
		request.setAttribute("specialisations", C_Specialisation.lister());
		request.getRequestDispatcher("/Views/administrateur/intervention.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		// 0. Variables
		
		// 1. Vérifier submit
		if (request.getParameter("submit") != null) {
			String typeIntervention = request.getParameter("typeIntervention");
			String typeSpecialite = request.getParameter("typeSpecialite");
			String strHonoraire = request.getParameter("honoraire");
			String strDureePrev = request.getParameter("dureePrev");
			
			// 2. Vérifier champs vide
			if(estVide(typeIntervention, strHonoraire, strDureePrev)) {
				request.setAttribute("erreur", "Veuillez remplir les champs !");
				request.getRequestDispatcher("/Views/administration/intervention.jsp").forward(request, response);
			}
			
			// 3. Envoi des données
			else {
				// 3.1 Convertir éléments
				double honoraire = Global.tryParseDouble(strHonoraire);
				int dureePrev = Global.tryParseInt(strDureePrev);
				int specialite = Global.tryParseInt(typeSpecialite);
				
				// 3.2 Recuparation specialité
				C_Specialisation spe = new C_Specialisation();
				spe = spe.trouver(specialite);
				
				// 3.2 Créer type intervention
				C_TypeIntervention typeInter = new C_TypeIntervention(typeIntervention, honoraire, dureePrev, spe);
				if(typeInter.creer()) {
					/*request.setAttribute("erreur", "Enregistrement réussi, une autre intervention ?");
					request.getRequestDispatcher("/Views/administrateur/intervention.jsp").forward(request, response);*/
					doGet(request, response);
				}
				else {
					request.setAttribute("erreur", "Une erreur est survenue lors de l'ajout de l'intervention !");
					request.getRequestDispatcher("/Views/administrateur/intervention.jsp").forward(request, response);
				}	
			}
		}
	}
}
