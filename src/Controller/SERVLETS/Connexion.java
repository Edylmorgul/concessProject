package Controller.SERVLETS;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import Model.BEANS.C_Administrateur;
import Model.BEANS.C_Client;
import Model.BEANS.C_Garagiste;
import Model.BEANS.C_Utilisateur;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // Vérifier champs vide
    private boolean estVide(String email, String mdp) {
    	
    	boolean erreur = false;    	
    	if(email == null || email.equals(""))
    		erreur = true;
    	
    	if(mdp == null || mdp.equals(""))
    		erreur = true;
    	    	
    	return erreur;
    }
    
    // Vérification des mots de passe
    private boolean verifierMdp(String mdp, String cryptMdp) {
		if (BCrypt.checkpw(mdp, cryptMdp))
			return true;
		else
			return false;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		// 0. Variable
		C_Utilisateur uti = null;
		boolean verifMdp;
		String cryptMdp = null;
				
		// 1. Vérifier submit
		if (request.getParameter("submit") != null) {
			String email = request.getParameter("email");
			String mdp = request.getParameter("mdp");
			
			// 2. Vérifier champs vide
			if(estVide(email, mdp)) {
				request.setAttribute("erreur", "Veuillez remplir les champs !");
				request.getRequestDispatcher("/Views/home/index.jsp").forward(request, response);
			}				
						
			else {
				// 3. Vérifier si utilisateur présent
				// 3.1 Récupérer mdp crypter
				email = email.toLowerCase();
				cryptMdp = C_Utilisateur.recupCryptMdp(email);
				if(cryptMdp == null) {
					request.setAttribute("erreur", "Aucun compte trouver !");
					request.getRequestDispatcher("/Views/home/index.jsp").forward(request, response);
				}
				
				else {
					// 4. Test mdp
					verifMdp = verifierMdp(mdp, cryptMdp);			
					if(!verifMdp) {
						request.setAttribute("erreur", "Mauvais mot de passe !");
						request.getRequestDispatcher("/Views/home/index.jsp").forward(request, response);
					}							
					
					else {
						uti = new C_Utilisateur();
						uti = uti.connexion(email, cryptMdp);
						
						// 5. Vérifier type utilisateur
						if(uti != null) {
							// 6. Vérifier si compte actif ou pas
							if(uti.getActif() == 1) {
								HttpSession session = request.getSession();
								// 6.1 Client
								if(uti.recupTypeUti() instanceof C_Client) {
									// 6.1.1 Créer session
									C_Client cli = (C_Client) uti.recupTypeUti();
									
							        session.setAttribute("client", cli);
									response.sendRedirect("Views/client/home.jsp");
								}
								
								// 6.2 Garagiste
								else if(uti.recupTypeUti() instanceof C_Garagiste) {
									// 6.2.1 Créer session
									C_Garagiste gara = (C_Garagiste) uti.recupTypeUti();
									session = request.getSession();
							        session.setAttribute("garagiste", gara);
									response.sendRedirect("Views/garagiste/home.jsp");
								}
								
								// 6.3 Administrateur
								else {
									// 6.3.1 Créer session
									C_Administrateur admin = (C_Administrateur) uti.recupTypeUti();
									session = request.getSession();
							        session.setAttribute("administrateur", admin);
									response.sendRedirect("Views/administrateur/home.jsp");
								}
							}	
							
							// 6.4 Redirection page erreur
							else {
								response.sendRedirect("Views/home/inactif.jsp");
							}
						}
						
						else {
							request.setAttribute("erreur", "Une erreur est survenue lors de la connexion !");
							request.getRequestDispatcher("/Views/home/index.jsp").forward(request, response);
						}
					}					
				}
			}
		}
	}
}
