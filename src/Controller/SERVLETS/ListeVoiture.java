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
import Model.BEANS.C_Voiture;

/**
 * Servlet implementation class ListeVoiture
 */
@WebServlet("/ListeVoiture")
public class ListeVoiture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeVoiture() {
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
		HttpSession session = request.getSession();
		C_Client client = (C_Client) session.getAttribute("client");
		List<C_Voiture> liste = new LinkedList<>();
		
		// 1. Liste des voitures 
		client.listeVoitureParClient();
		liste = client.getListeVoiture();
		request.setAttribute("liste", liste);
		request.getRequestDispatcher("/Views/client/voiture.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
