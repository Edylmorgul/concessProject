package Controller.SERVLETS;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEANS.C_Client;
import Model.BEANS.C_Garagiste;
import Model.BEANS.C_Utilisateur;

/**
 * Servlet implementation class ModifierCompte
 */
@WebServlet("/ModifierUtilisateur")
public class ModifierUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // Vérifier champs vide
    private boolean estVide(String nom, String prenom, String telephone, String mdp) {
    	
    	boolean erreur = false;
    	if(nom == null || nom.equals(""))
			erreur = true;
		   	
    	if(prenom == null || prenom.equals(""))
    		erreur = true;
    	
    	if(telephone == null || telephone.equals(""))
    		erreur = true;
    	
    	if(mdp == null || mdp.equals(""))
    		erreur = true;
    	    	
    	return erreur;
    }
    
    // Vérifier regexp
    private boolean estValide(String nom, String prenom, String telephone, String mdp) {
    	
    	boolean erreur = false;
    	if(!nom.matches("\\p{L}*")) // Raccourci reg
    		erreur = true;
    	
    	if(!prenom.matches("\\p{L}*"))
    		erreur = true;
    	
    	if(!telephone.matches(Model.BEANS.Global.getPhonePattern()))
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
		response.sendRedirect("Views/home/modifierCompte.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		// 0. Variables
		HttpSession session = request.getSession();
		C_Client client = (C_Client) session.getAttribute("client");
		C_Garagiste gara = (C_Garagiste) session.getAttribute("garagiste");
		
		// 1. Vérifier submit
		if (request.getParameter("submit") != null) {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String telephone = request.getParameter("telephone");
			String mdp = request.getParameter("mdp");
			String mdpConfirm = request.getParameter("mdpConfirm");
					
			// 2. Vérifier champs vide
			if(estVide(nom, prenom, telephone, mdp)) {
				request.setAttribute("erreur", "Veuillez remplir les champs !");
				request.getRequestDispatcher("Views/home/modifierCompte.jsp").forward(request, response);
			}
			
			// 3. Vérifier valider des champs(regexp)
			else if(estValide(nom, prenom, telephone, mdp)) {
				request.setAttribute("erreur", "Saisie invalide, recommencez !");
				request.getRequestDispatcher("Views/home/modifierCompte.jsp").forward(request, response);
			}
			
			// 4. Vérifier mots de passe
			else if(confirmerMdp(mdp, mdpConfirm)) {
				request.setAttribute("erreur", "Mot de passe différent !");
				request.getRequestDispatcher("Views/home/modifierCompte.jsp").forward(request, response);
			}
			
			// 5. Envoi des données
			else {
				// 5.1 Crypter nouveau mdp				
				mdp = C_Utilisateur.crypterMdp(mdp);
				
				// 6. Modification des informations selon utilisateurs
				// 6.1 Client
				if(client != null) {
					client.setNom(nom);
					client.setPrenom(prenom);
					client.setTelephone(telephone);
					client.setMdp(mdp);
					
					if(client.modifier()) {
						session.setAttribute("client", client);
						response.sendRedirect("Views/client/home.jsp");
					}
					
					else {
						request.setAttribute("erreur", "Une erreur est survenue lors de la modification !");
						request.getRequestDispatcher("Views/home/modifierCompte.jsp").forward(request, response);
					}
				}
				
				// 6.2 Garagiste
				else {
					gara.setNom(nom);
					gara.setPrenom(prenom);
					gara.setTelephone(telephone);
					gara.setMdp(mdp);
					
					if(gara.modifier()) {
						session.setAttribute("garagiste", gara);
						response.sendRedirect("Views/garagiste/home.jsp");
					}
					
					else {
						request.setAttribute("erreur", "Une erreur est survenue lors de la modification !");
						request.getRequestDispatcher("Views/home/modifierCompte.jsp").forward(request, response);
					}
				}
			}
		}
	}
}
