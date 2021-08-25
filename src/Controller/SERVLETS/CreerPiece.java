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
 * Servlet implementation class CreerPiece
 */
@WebServlet("/CreerPiece")
public class CreerPiece extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreerPiece() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // Vérifier champs vide
    private boolean estVide(String nom, String prix) {
    	
    	boolean erreur = false;
    	if(nom == null || nom.equals(""))
    		erreur = true;
    	
    	if(prix == null || prix.equals(""))
			erreur = true;
    	    	
    	return erreur;
    }
    
    // Vérifier si piéce déjà présente
    private boolean estPresente(C_Piece piece) {
    	
    	for(C_Piece p : C_Piece.lister()) {
    		if(((piece.getNom()).toLowerCase()).equals((p.getNom()).toLowerCase())) {
    			return true;
    		}
    	}
		return false;   	
    }
    
    // Vérifier si piéce déjà présente
    private boolean estPresenteTypeIntervention(C_TypeIntervention typeInter, C_Piece piece) {
    	typeInter.listePieceParIntervention();
    	
    	for(C_Piece p : typeInter.getListPiece()) {
    		if(((piece.getNom()).toLowerCase()).equals((p.getNom()).toLowerCase())) {
    			return true;
    		}
    	}
		return false;   	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		int idTypeIntervention = Global.tryParseInt(request.getParameter("id"));
		C_TypeIntervention typeInter = new C_TypeIntervention();
		typeInter = typeInter.trouver(idTypeIntervention);
		List<C_Piece> piecesAvec = new LinkedList<>();
		List<C_Piece> piecesSans = new LinkedList<>();
		
		for(C_TypeInterventionPiece piece : C_TypeInterventionPiece.lister())
		{
			if(piece.getTypeIntervention().getId() == idTypeIntervention) piecesAvec.add(piece.getPiece());
		}
		
		for(C_Piece piece : C_Piece.lister())
		{
			if(!piecesAvec.contains(piece)) piecesSans.add(piece);
		}
		
		request.setAttribute("typeInter",typeInter);
		request.setAttribute("piecesAvec", piecesAvec);
		request.setAttribute("piecesSans", piecesSans);
		request.getRequestDispatcher("/Views/administrateur/piece.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		// 0. Variable
		C_Piece piece = null;
		C_TypeInterventionPiece typeInterPiece = null;
		C_TypeIntervention typeInter = new C_TypeIntervention();
		int idTypeIntervention = Global.tryParseInt((String)request.getParameter("id"));
		typeInter = typeInter.trouver(idTypeIntervention);
		List<C_Piece> piecesAvec = new LinkedList<>();
		List<C_Piece> piecesSans = new LinkedList<>();
		
		
		// 2. Vérifier submit
		if (request.getParameter("submit") != null) {
			String nom = request.getParameter("nom");
			String strPrix = request.getParameter("prix");
			String listePiece = request.getParameter("listePiece");
			Double prix = Global.tryParseDouble(strPrix);
			piece = new C_Piece(nom, prix);
			
			// Si l'utilisateur n'a pas cliqué sur une option ==> Forcément il fait un ajout...
			
			// 3. Vérifier champs vide
			if(estVide(nom, strPrix)) {
				
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
				request.setAttribute("erreur", "Veuillez remplir les champs !");
				request.getRequestDispatcher("/Views/administrateur/piece.jsp").forward(request, response);
			}
			
			// 4. Vérifier format
			else if(Global.tryParseDouble(strPrix) == null) {
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
				request.setAttribute("erreur", "Veuillez entrer un format de nombre valide !");
				request.getRequestDispatcher("/Views/administrateur/piece.jsp").forward(request, response);
			}
			
			// 5. Vérifier si nombre négatif
			else if(Global.tryParseDouble(strPrix) < 0){
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
				request.setAttribute("erreur", "Veuillez entrer un nombre valide !");
				request.getRequestDispatcher("/Views/administrateur/piece.jsp").forward(request, response);
			}
			
			else if(estPresente(piece)) {
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
				request.setAttribute("erreur", "Cette pièce est déjà présente !");
				request.getRequestDispatcher("/Views/administrateur/piece.jsp").forward(request, response);
			}
			
			// Vérifier que la piece n'est pas déjà présente pour le type d'intervention
			else if(estPresenteTypeIntervention(typeInter, piece)) {
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
				request.setAttribute("erreur", "Cette pièce est déjà présente pour l'intervention !");
				request.getRequestDispatcher("/Views/administrateur/piece.jsp").forward(request, response);
			}
			
			// 6. Création de l'objet
			else {				
				if(piece.creer()) {
					typeInterPiece = new C_TypeInterventionPiece(typeInter, piece);
					if(typeInterPiece.creer()) {
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
						request.setAttribute("erreur", "Voulez-vous ajouter une autre pièce ?");
						request.getRequestDispatcher("/Views/administrateur/piece.jsp").forward(request, response);
					}	
					
					else {
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
						request.setAttribute("erreur", "Une erreur est survenue !");
						request.getRequestDispatcher("/Views/administrateur/piece.jsp").forward(request, response);
					}
				}
				
				else {
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
					request.setAttribute("erreur", "Une erreur est survenue lors de l'ajout de la pièce !");
					request.getRequestDispatcher("/Views/administrateur/piece.jsp").forward(request, response);
				}					
			}
		}
	}		
}
