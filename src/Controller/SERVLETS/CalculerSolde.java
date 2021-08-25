package Controller.SERVLETS;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEANS.C_Intervention;
import Model.BEANS.C_InterventionTypeInterventionPiece;
import Model.BEANS.C_Piece;
import Model.BEANS.C_Rdv;
import Model.BEANS.Global;

/**
 * Servlet implementation class calculerSolde
 */
@WebServlet("/CalculerSolde")
public class CalculerSolde extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private C_Rdv rdv = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculerSolde() {
        super();
        // TODO Auto-generated constructor stub
    }
    
 // Vérifier champs vide
    private boolean estVide(String duree) {
    	
    	boolean erreur = false;
    	if(duree == null || duree.equals(""))
    		erreur = true;
    	    	
    	return erreur;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// 0. Variables
		rdv = new C_Rdv();
						
		// 1. Récupérer id rendez-vous
		String strId = request.getParameter("id");		
		int id = Integer.parseInt(strId);
		rdv = rdv.trouver(id);
		
		// 2. Créer session rendez-vous ==> Afin d'éviter rdv is null au rechargement de la page
		HttpSession session = request.getSession();
		session = request.getSession();
        session.setAttribute("rdv", rdv);		
		
        response.sendRedirect("Views/garagiste/solde.jsp");
		//request.getRequestDispatcher("/Views/garagiste/solde.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		// 0. Variables
		C_InterventionTypeInterventionPiece typeInterPiece = null;
		C_Piece piece = new C_Piece();
		C_Intervention intervention = null;
		Double total = 0d;
		
		// 1. Vérifier submit
		if (request.getParameter("submit") != null) {
			
			String strDuree = request.getParameter("duree");
			String[] tabIdPiece = request.getParameterValues("idPiece");
			String[] tabQt = request.getParameterValues("qt");
			String[][] tabPieceQt = {
					tabIdPiece,
					tabQt
			};
			
			// 2. Vérifier champs vide
			if(estVide(strDuree)) {
				request.setAttribute("erreur", "Veuillez remplir les champs !");
				request.getRequestDispatcher("/Views/garagiste/solde.jsp").forward(request, response);
			}
			
			else {
				// Résultat
				//System.out.println(tabPieceQt[0].length);
				for(int i = 0; i< tabPieceQt[0].length; i++) {					
					int idPiece = Global.tryParseInt(tabPieceQt[0][i]);
					int qt = Global.tryParseInt(tabPieceQt[1][i]);
					piece = piece.trouver(idPiece);
					
					
					if (qt > 0) {
						typeInterPiece = new C_InterventionTypeInterventionPiece(rdv.getIntervention(), rdv.getIntervention().getTypeIntervention(), piece, qt);
						if(!typeInterPiece.creer()) {
							request.setAttribute("erreur", "Une erreur est survenue pour la piece " + piece.getNom());
							request.getRequestDispatcher("/Views/garagiste/solde.jsp").forward(request, response);
						}
					}
				}
				
				// Update intervention
				int duree = Global.tryParseInt(strDuree);
				intervention = rdv.getIntervention();
				intervention.setDuree(duree);
		        
				// Calcul
		        total = intervention.getTypeIntervention().renvoyerCoutReel();
		        
				for(C_InterventionTypeInterventionPiece itp : C_InterventionTypeInterventionPiece.lister()) {
					if(itp.getIntervention().getId() == intervention.getId()) {
						total += itp.getPiece().calculerPrix(itp.getNombre());
					}
				}
				
				intervention.setCt(Global.renvoyerFormatMonetaire(total));
					
				if(intervention.modifier()) {
					response.sendRedirect("ListeRdvGaragiste");
				}
				
				else {
					request.setAttribute("erreur", "Une erreur est survenue lors de la modification de l'intervention!");
					request.getRequestDispatcher("/Views/garagiste/solde.jsp").forward(request, response);
				}
			}
		}
	}
}
