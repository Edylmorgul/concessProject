package Controller.SERVLETS;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEANS.C_Garagiste;
import Model.BEANS.C_Indisponibilite;
import Model.BEANS.Global;

/**
 * Servlet implementation class CreerIndisponibilite
 */
@WebServlet("/CreerIndisponibilite")
public class CreerIndisponibilite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private C_Garagiste gara;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreerIndisponibilite() {
        super();
        // TODO Auto-generated constructor stub
    }
    
 // Vérifier champs vide
    private boolean estVide(String debut, String fin) {
    	
    	boolean erreur = false;
    	if(debut == null || debut.equals(""))
			erreur = true;
		   	
    	if(fin == null || fin.equals(""))
    		erreur = true;
    	    	
    	return erreur;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// 1. Récupérer id garagiste
		String strId = request.getParameter("id");
		int id = Integer.parseInt(strId);
		gara = new C_Garagiste();
		gara = gara.trouver(id);
		
		// 2. Récupérer liste indisponibilite
		gara.listeIndisponibiliteParGaragiste();
				
		request.setAttribute("garagiste", gara);
		request.setAttribute("liste", gara.getListeIndisponibilite());
		request.getRequestDispatcher("/Views/administrateur/indisponibilite.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		// 0. Variable
		boolean erreur = false;
		C_Indisponibilite indi = null;
		Date dateDebut = null;
		Date dateFin = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				
		// 1. Vérifier submit
		if (request.getParameter("submit") != null) {
			String debut = request.getParameter("debut");
			String fin = request.getParameter("fin");
			
			// 2. Vérifier champs vide
			if(estVide(debut, fin)) {
				request.setAttribute("erreur", "Veuillez remplir les champs !");
				request.getRequestDispatcher("/Views/administrateur/indisponibilite.jsp").forward(request, response);
			}
			
			// 6. Envoi des données
			else {
				// 6.1 Convertir éléments
				if(Global.tryParseDate(debut, formatter) == null) {
					erreur = true;
					request.setAttribute("erreur", "Format date invalide, recommencez !");
					request.getRequestDispatcher("/Views/administrateur/indisponibilite.jsp").forward(request, response);
				}				
				dateDebut = Global.tryParseDate(debut, formatter);
				
				if(Global.tryParseDate(debut, formatter) == null) {
					erreur = true;
					request.setAttribute("erreur", "Format date invalide, recommencez !");
					request.getRequestDispatcher("/Views/administrateur/indisponibilite.jsp").forward(request, response);
				}				
				dateFin = Global.tryParseDate(fin, formatter);
				
				// 6.2 Créer objet
				if(!erreur) {
					indi = new C_Indisponibilite(gara, dateDebut, dateFin);
					if(indi.creer())
						response.sendRedirect("ListeUtilisateur");
					
					else {
						request.setAttribute("erreur", "Echec durant la création de l'indisponibilité !");
						request.getRequestDispatcher("/Views/administrateur/indisponibilite.jsp").forward(request, response);
					}
				}
			}
		}
	}
}
