package Controller.SERVLETS;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEANS.C_Client;
import Model.BEANS.C_Rdv;
import Model.BEANS.C_Voiture;

/**
 * Servlet implementation class ListeRdvClient
 */
@WebServlet("/ListeRdvClient")
public class ListeRdvClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeRdvClient() {
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
		List<C_Rdv> liste = new LinkedList<>();
		
		// 1. Récupérer session
		HttpSession session = request.getSession(false);
		C_Client client = (C_Client)session.getAttribute("client");
		
		// 2. Récupérer voiture client
		client.listeVoitureParClient();
		
		// 3. Récupérer rdv voiture
		for(C_Voiture v : client.getListeVoiture()) {
			v.listeRdvParClient();
			for(C_Rdv rdv : v.getListeRdv()) {
				liste.add(rdv);
			}
		}
		//liste.addAll(v.getListeRdv()); // Marche bien addAll... lel
		request.setAttribute("liste", liste);
		request.getRequestDispatcher("/Views/client/historique.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
