package Model.BEANS;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import Model.DAO.ConnectDatabase;
import Model.DAO.D_TypeInterventionPiece;

public class C_TypeInterventionPiece implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// Attributs
	private C_TypeIntervention typeIntervention = null;
	private C_Piece piece = null;
	
	// Constructeur
	public C_TypeInterventionPiece() {}
	
	public C_TypeInterventionPiece(C_TypeIntervention typeIntervention, C_Piece piece) {
		this.typeIntervention = typeIntervention;
		this.piece = piece;
	}

	// GET - SET
	public C_TypeIntervention getTypeIntervention() {
		return typeIntervention;
	}

	public void setTypeIntervention(C_TypeIntervention typeIntervention) {
		this.typeIntervention = typeIntervention;
	}

	public C_Piece getPiece() {
		return piece;
	}

	public void setPiece(C_Piece piece) {
		this.piece = piece;
	}
	
	// Methodes
	public boolean creer() {
		return Global.getFactory().getTypeInterventionPieceDAO().creer(this);
	}
	
	public boolean modifier() {
		return Global.getFactory().getTypeInterventionPieceDAO().modifier(this);
	}
	
	public boolean supprimer() {
		return Global.getFactory().getTypeInterventionPieceDAO().supprimer(this);
	}
	
	public C_TypeInterventionPiece trouver(int idt, int idp) {
		Connection conn = ConnectDatabase.getInstance();
		D_TypeInterventionPiece d_typeInterventionPiece = new D_TypeInterventionPiece(conn);
		return d_typeInterventionPiece.rechercher(idt, idp);
	}
	
	public static List<C_TypeInterventionPiece> lister(){
		return Global.getFactory().getTypeInterventionPieceDAO().lister();
	}
}
