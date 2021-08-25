package Controller.SERVLETS;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEANS.C_Voiture;
import Model.BEANS.Global;

/**
 * Servlet implementation class ModifierVoiture
 */
@WebServlet("/ModifierVoiture")
public class ModifierVoiture extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private C_Voiture voiture = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierVoiture() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // Vérifier champs vide
    private boolean estVide(String marque, String modele, String numImm
    		, String numChass, String couleur, String km, String primm) {
    	
    	boolean erreur = false;
    	if(marque == null || marque.equals(""))
			erreur = true;
		   	
    	if(modele == null || modele.equals(""))
    		erreur = true;
    	
    	if(numImm == null || numImm.equals(""))
    		erreur = true;
    	
    	if(numChass == null || numChass.equals(""))
    		erreur = true;
    	
    	if(couleur == null || couleur.equals(""))
    		erreur = true;
    	
    	if(km == null || km.equals(""))
    		erreur = true;
    	
    	if(primm == null || primm.equals(""))
    		erreur = true;
    	    	
    	return erreur;
    }
    
    // Vérifier regexp
    private boolean estValide(String numChass, String couleur, String km) {
    	
    	boolean erreur = false;
    	if(!couleur.matches("\\p{L}*"))
    		erreur = true;
    	
    	if(!numChass.matches(Model.BEANS.Global.getVinPattern()))
    		erreur = true;
    	
    	if(!km.matches(Model.BEANS.Global.getNumberPattern()))
    		erreur = true;
    	
    	return erreur;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// 1. Récupérer id voiture
		String strId = request.getParameter("id");
		int id = Integer.parseInt(strId);
		//C_Voiture voiture = new C_Voiture();
		voiture = new C_Voiture();
		voiture = voiture.trouver(id);
		//request.setAttribute("voiture", voiture);
		HttpSession session = request.getSession();
        session.setAttribute("voiture", voiture);
		request.getRequestDispatcher("/Views/client/modifierVehicule.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		// 0. Variable
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int km = 0;
		Date primm = null;
		boolean erreur = false;
		//C_Voiture voiture = new C_Voiture();
		
		// 1. Vérifier submit
		if (request.getParameter("submit") != null) {			
			String marque = request.getParameter("marque");
			String modele = request.getParameter("modele");
			String numImm = request.getParameter("numImm");
			String numChass = request.getParameter("numChass");
			String couleur = request.getParameter("couleur");
			String strKm = request.getParameter("km");
			String strPrimm = request.getParameter("primm");
			
			// 2. Vérifier champs vide
			if(estVide(marque, modele, numImm, numChass, couleur, strKm, strPrimm)) {
				request.setAttribute("erreur", "Veuillez remplir les champs !");
				request.getRequestDispatcher("/Views/client/modifierVehicule.jsp").forward(request, response);
			}			
												
			// 3. Vérifier valider des champs(regexp)
			else if(estValide(numChass, couleur, strKm)) {
				request.setAttribute("erreur", "Saisie invalide, recommencez !");
				request.getRequestDispatcher("/Views/client/modifierVehicule.jsp").forward(request, response);
			}
			
			// 4. Envoi des données
			else {					
				// 5.1 Vérifier format
				if(Global.tryParseInt(strKm) == null) {
					erreur = true;
					request.setAttribute("erreur", "Format kilomètre invalide, recommencez !");
					request.getRequestDispatcher("/Views/client/modifierVehicule.jsp").forward(request, response);
				}	
					
				km = Global.tryParseInt(strKm);
					
				if(Global.tryParseDate(strPrimm, formatter) == null) {
					erreur = true;
					request.setAttribute("erreur", "Format date invalide, recommencez !");
					request.getRequestDispatcher("/Views/client/modifierVehicule.jsp").forward(request, response);
				}
					
				primm = Global.tryParseDate(strPrimm, formatter);
					
				if(!erreur) {
					// 4.3 Modifier objet
					voiture.setMarque(marque);
					voiture.setModele(modele);
					voiture.setNumimm(numImm);
					voiture.setNumchas(numChass);
					voiture.setCouleur(couleur);
					voiture.setKm(km);
					voiture.setPrimm(primm);
					if(voiture.modifier())
						response.sendRedirect("ListeVoiture");
														
					else {
						request.setAttribute("erreur", "Une erreur est survenue lors de la modification !");
						request.getRequestDispatcher("/Views/client/modifierVehicule.jsp").forward(request, response);
					}
				}																			
			}
		}
	}
}
