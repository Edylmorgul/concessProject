package Controller.SERVLETS;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEANS.C_Client;
import Model.BEANS.C_Garagiste;
import Model.BEANS.C_Utilisateur;

/**
 * Servlet implementation class CreerCompte
 */
@WebServlet("/CreerUtilisateur")
public class CreerUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreerUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // Vérifier champs vide
    private boolean estVide(String nom, String prenom, String telephone
    		, String email, String mdp, String mdpConfirm) {
    	
    	boolean erreur = false;
    	if(nom == null || nom.equals(""))
			erreur = true;
		   	
    	if(prenom == null || prenom.equals(""))
    		erreur = true;
    	
    	if(telephone == null || telephone.equals(""))
    		erreur = true;
    	
    	if(email == null || email.equals(""))
    		erreur = true;
    	
    	if(mdp == null || mdp.equals(""))
    		erreur = true;
    	
    	if(mdpConfirm == null || mdpConfirm.equals(""))
    		erreur = true;
    	    	
    	return erreur;
    }
    
    // Vérifier regexp
    private boolean estValide(String nom, String prenom, String telephone
    		, String email, String mdp) {
    	
    	boolean erreur = false;
    	if(!nom.matches("\\p{L}*")) // Raccourci reg
    		erreur = true;
    	
    	if(!prenom.matches("\\p{L}*"))
    		erreur = true;
    	
    	if(!telephone.matches(Model.BEANS.Global.getPhonePattern()))
    		erreur = true;
    	
    	if(!email.matches(Model.BEANS.Global.getEmailPattern()))
    		erreur = true;
    	
    	if(!mdp.matches(Model.BEANS.Global.getPasswordPattern()))
    		erreur = true;
    	
    	return erreur;
    }
    
    // Confirmation mot de passe
    private boolean confirmerMdp(String mdp, String mdpConfirm) {
    	
    	boolean erreur = false;   	
    	if(!mdp.equals(mdpConfirm)) {
			erreur = true;
		}
    	
    	return erreur;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("Views/home/enregistrement.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		// 0. Variable
		C_Client cli = null;
		C_Garagiste gara = null;
		
		// 1. Vérifier submit
		if (request.getParameter("submit") != null) {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String strTypegenre = request.getParameter("typeGenre");
			String telephone = request.getParameter("telephone");
			String email = request.getParameter("email");
			String mdp = request.getParameter("mdp");
			String mdpConfirm = request.getParameter("mdpConfirm");
			String strTypeUtilisateur = request.getParameter("typeUtilisateur");
					
			// 2. Vérifier champs vide
			if(estVide(nom, prenom, telephone, email, mdp, mdpConfirm)) {
				request.setAttribute("erreur", "Veuillez remplir les champs !");
				request.getRequestDispatcher("/Views/home/enregistrement.jsp").forward(request, response);
			}				
													
			// 3. Vérifier valider des champs(regexp)
			else if(estValide(nom, prenom, telephone, email, mdp)) {
				request.setAttribute("erreur", "Saisie invalide, recommencez !");
				request.getRequestDispatcher("/Views/home/enregistrement.jsp").forward(request, response);
			}
				
			// 4. Vérifier les mots de passes
			else if(confirmerMdp(mdp, mdpConfirm)) {
				request.setAttribute("erreur", "Mot de passe différent !");
				request.getRequestDispatcher("/Views/home/enregistrement.jsp").forward(request, response);
			}
			
			// 5. Vérifier si email déjà présent
			else if(C_Utilisateur.emailExiste(email)) {
				request.setAttribute("erreur", "Email déjà présent !");
				request.getRequestDispatcher("/Views/home/enregistrement.jsp").forward(request, response);
			}
			
			// 6. Envoi des données
			else {	
				// 6.1 Convertir éléments
				int typeGenre = Integer.parseInt(strTypegenre);
				int typeUtilisateur = Integer.parseInt(strTypeUtilisateur);
				
				// 6.2 Crypter mdp				
				mdp = C_Utilisateur.crypterMdp(mdp);
				
				// 6.3 Créer objet selon type utilisateur
				// 6.3.1 Client
				if(typeUtilisateur == 1) {
					cli = new C_Client(email, mdp, nom, prenom, typeGenre, telephone);
					
					if(cli.creer())
						request.getRequestDispatcher("/Views/home/index.jsp").forward(request, response);

					else {
						request.setAttribute("erreur", "Enregistrement échoué !");
						request.getRequestDispatcher("/Views/home/enregistrement.jsp").forward(request, response);
					}
				}
				
				// 6.3.2 Garagiste
				else {
					gara = new C_Garagiste(email, mdp, nom, prenom, typeGenre, telephone, null);
					
					if(gara.creer())
						request.getRequestDispatcher("/Views/home/index.jsp").forward(request, response);
					
					else {
						request.setAttribute("erreur", "Enregistrement échoué !");
						request.getRequestDispatcher("/Views/home/enregistrement.jsp").forward(request, response);
					}
				}			
			}									
		}				
	}
}

/*
 * 2 façon d'envoyer des erreurs correctement d'une servlet vers une page jsp
 *  - Utiliser setAttribute
 *  - Utiliser une session "erreur"
 *  L'une ou l'autre dépend de l'utilisation de l'application 
 *  The most common and recommended scenario (for the server side validation in Java serlvets/JSP world) 
 *  is setting some error message as a request attribute (in the request scope) 
 *  and then outputting this message in a JSP using Expression Language (see the example below). 
 *  When the error message is not set - nothing will be shown.
 */
