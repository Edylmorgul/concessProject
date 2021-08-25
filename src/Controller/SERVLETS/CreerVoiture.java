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

import Model.BEANS.C_Client;
import Model.BEANS.C_Voiture;
import Model.BEANS.Global;

/**
 * Servlet implementation class CreerVoiture
 */
@WebServlet("/CreerVoiture")
public class CreerVoiture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreerVoiture() {
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
		response.sendRedirect("Views/client/enregistrementVehicule.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		// 0. Variable
		C_Voiture voiture = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		// 1. Récupérer session
		HttpSession session = request.getSession(false);
		C_Client client = (C_Client)session.getAttribute("client");
		
		// 2. Vérifier submit
		if (request.getParameter("submit") != null) {			
			String marque = request.getParameter("marque");
			String modele = request.getParameter("modele");
			String numImm = request.getParameter("numImm");
			String numChass = request.getParameter("numChass");
			String couleur = request.getParameter("couleur");
			String strKm = request.getParameter("km");
			String strPrimm = request.getParameter("primm");
			int km = 0;
			Date primm = null;
			boolean erreur = false;
			
			// 3. Vérifier champs vide
			if(estVide(marque, modele, numImm, numChass, couleur, strKm, strPrimm)) {
				request.setAttribute("erreur", "Veuillez remplir les champs !");
				request.getRequestDispatcher("/Views/client/enregistrementVehicule.jsp").forward(request, response);
			}				
						
			// 4. Vérifier valider des champs(regexp)
			else if(estValide(numChass, couleur, strKm)) {
				request.setAttribute("erreur", "Saisie invalide, recommencez !");
				request.getRequestDispatcher("/Views/client/enregistrementVehicule.jsp").forward(request, response);
			}
			
			// 5. Envoi des données
			else {
				// 5.1 Vérifier format
				if(Global.tryParseInt(strKm) == null) {
					erreur = true;
					request.setAttribute("erreur", "Format kilomètre invalide, recommencez !");
					request.getRequestDispatcher("/Views/client/enregistrementVehicule.jsp").forward(request, response);
				}	
				
				km = Global.tryParseInt(strKm);
				
				if(Global.tryParseDate(strPrimm, formatter) == null) {
					erreur = true;
					request.setAttribute("erreur", "Format date invalide, recommencez !");
					request.getRequestDispatcher("/Views/client/enregistrementVehicule.jsp").forward(request, response);
				}
				
				primm = Global.tryParseDate(strPrimm, formatter);
													
				if(!erreur) {					
					// 5.2 Créer objet
					voiture = new C_Voiture(client, numImm, numChass, couleur, km, primm, marque, modele);
					if(voiture.creer()) {
						request.setAttribute("erreur", "Enregistrement effectué ! Une autre voiture ?");
						request.getRequestDispatcher("/Views/client/enregistrementVehicule.jsp").forward(request, response);
					}
						
					else {
						request.setAttribute("erreur", "Une erreur est survenue lors de l'enregistrement !");
						request.getRequestDispatcher("/Views/client/enregistrementVehicule.jsp").forward(request, response);
					}	
				}										
			}
		}				
	}
}
