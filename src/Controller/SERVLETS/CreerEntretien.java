package Controller.SERVLETS;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEANS.C_Garagiste;
import Model.BEANS.C_Intervention;
import Model.BEANS.C_Rdv;
import Model.BEANS.C_TypeIntervention;
import Model.BEANS.C_Voiture;
import Model.BEANS.Global;

/**
 * Servlet implementation class CreerEntretien
 */
@WebServlet("/CreerEntretien")
public class CreerEntretien extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private C_Voiture voiture = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreerEntretien() {
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
		List<C_TypeIntervention> liste = new LinkedList<>();
		
		// 1. Récupérer id voiture
		String strId = request.getParameter("id");
		int id = Integer.parseInt(strId);
		voiture = new C_Voiture();
		voiture = voiture.trouver(id);
		
		// 2. Récupérer liste type intervention
		liste = C_TypeIntervention.lister();
		
		request.setAttribute("voiture", voiture);
		request.setAttribute("liste", liste);
		request.getRequestDispatcher("/Views/client/creerEntretien.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 0. Variables
		C_Intervention inter = null;
		C_Rdv rdv = null;
		C_Garagiste garagiste = new C_Garagiste();
		C_TypeIntervention typeInter = new C_TypeIntervention();
		String strDateDebut;
		String strDateFin;
		int idGaragiste;
		
		String strDate = request.getParameter("date");
		String strTypeInter = request.getParameter("typeSpe");
		
		int idTypeInter = Global.tryParseInt(strTypeInter);
		idGaragiste = Global.tryParseInt(strDate.split(";")[2]);
		
		strDateDebut = strDate.split(";")[0]; // control + alt + fleche (control d sup)
		strDateFin = strDate.split(";")[1];
		
		// Convertion date		
		Timestamp dateDebut = Timestamp.valueOf(strDateDebut+"00");
		Timestamp dateFin = Timestamp.valueOf(strDateFin+"00");
		
		// Rechercher garagiste
		garagiste = garagiste.trouver(idGaragiste);
		
		// Recherche du type intervention
		typeInter = typeInter.trouver(idTypeInter);
		
		// Création de l'intervention
		inter = new C_Intervention(typeInter, 0, 0d);
		request.setAttribute("voiture", voiture);
		request.setAttribute("liste", C_TypeIntervention.lister());
		if(inter.creer()) {
			// Création du rendez-vous
			rdv = new C_Rdv(inter, voiture, garagiste, dateDebut, dateFin);
			if(rdv.creer()) {
				request.setAttribute("erreur", "Le rendez-vous a bien été ajouté !");
				request.getRequestDispatcher("/Views/client/creerEntretien.jsp").forward(request, response);
			}
			
			else {
				request.setAttribute("erreur", "Une erreur est survenue lors de l'ajout du rendez-vous !");
				request.getRequestDispatcher("/Views/client/creerEntretien.jsp").forward(request, response);
			}
		}
		
		else {
			request.setAttribute("erreur", "Une erreur est survenue lors de l'ajout de l'intervention !");
			request.getRequestDispatcher("/Views/client/creerEntretien.jsp").forward(request, response);
		}				
	}
}
