package Controller.SERVLETS;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEANS.C_Garagiste;

/**
 * Servlet implementation class ListeRdvGaragiste
 */
@WebServlet("/ListeRdvGaragiste")
public class ListeRdvGaragiste extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeRdvGaragiste() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// 1. Récupérer session
		HttpSession session = request.getSession(false);
		C_Garagiste garagiste = (C_Garagiste)session.getAttribute("garagiste");
		
		// 2. Récupérer rdv garagiste
		garagiste.listeRdvParGaragiste();
		request.setAttribute("liste", garagiste.getListeRdv());
		request.getRequestDispatcher("/Views/garagiste/historique.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
