package Controller.SERVLETS;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEANS.C_Piece;
import Model.BEANS.C_TypeIntervention;
import Model.BEANS.C_TypeInterventionPiece;
import Model.BEANS.Global;

/**
 * Servlet implementation class SupprimerPiece
 */
@WebServlet("/SupprimerPiece")
public class SupprimerPiece extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerPiece() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		C_Piece piece = new C_Piece();
		C_TypeIntervention typeInter = new C_TypeIntervention();
		int idTypeIntervention = Global.tryParseInt((String)request.getParameter("idt"));
		int idPiece = Global.tryParseInt((String)request.getParameter("id"));
		List<C_Piece> piecesAvec = new LinkedList<>();
		List<C_Piece> piecesSans = new LinkedList<>();
		
		piece = piece.trouver(idPiece);
		if (piece.supprimer())
		{
			typeInter = typeInter.trouver(idTypeIntervention);
			
			for(C_TypeInterventionPiece p : C_TypeInterventionPiece.lister())
			{
				if(p.getTypeIntervention().getId() == idTypeIntervention) piecesAvec.add(p.getPiece());
			}
			
			for(C_Piece p : C_Piece.lister())
			{
				if(!piecesAvec.contains(p)) piecesSans.add(p);
			}
			
			request.setAttribute("typeInter",typeInter);
			request.setAttribute("piecesAvec", piecesAvec);
			request.setAttribute("piecesSans", piecesSans);
			request.getRequestDispatcher("/Views/administrateur/piece.jsp").forward(request, response);
		}
		else
		{
			typeInter = typeInter.trouver(idTypeIntervention);
			
			for(C_TypeInterventionPiece p : C_TypeInterventionPiece.lister())
			{
				if(p.getTypeIntervention().getId() == idTypeIntervention) piecesAvec.add(p.getPiece());
			}
			
			for(C_Piece p : C_Piece.lister())
			{
				if(!piecesAvec.contains(p)) piecesSans.add(p);
			}
			
			request.setAttribute("typeInter",typeInter);
			request.setAttribute("piecesAvec", piecesAvec);
			request.setAttribute("piecesSans", piecesSans);
			request.setAttribute("erreur", "Une erreur est survenue lors de la suppression de la pi�ce !");
			request.getRequestDispatcher("/Views/administrateur/piece.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
