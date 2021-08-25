package Model.BEANS;

import java.io.Serializable;
import java.util.List;

public class C_InterventionTypeInterventionPiece implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Attributs
	private C_Intervention intervention = null;
	private C_TypeIntervention typeIntervention = null;
	private C_Piece piece = null;
	private int nombre = 0;
	
	// Constructeurs
	public C_InterventionTypeInterventionPiece() {}
	
	public C_InterventionTypeInterventionPiece(C_Intervention intervention, C_TypeIntervention typeIntervention,
			C_Piece piece, int nombre) {
		this.intervention = intervention;
		this.typeIntervention = typeIntervention;
		this.piece = piece;
		this.nombre = nombre;
	}

	// GET - SET
	public C_Intervention getIntervention() {
		return intervention;
	}

	public void setIntervention(C_Intervention intervention) {
		this.intervention = intervention;
	}

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

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	
	// Methodes
	public boolean creer() {
		return Global.getFactory().getInterventionTypeInterventionPieceDAO().creer(this);
	}
	
	public boolean modifier() {
		return Global.getFactory().getInterventionTypeInterventionPieceDAO().modifier(this);
	}
	
	public boolean supprimer() {
		return Global.getFactory().getInterventionTypeInterventionPieceDAO().supprimer(this);
	}
	
	public C_InterventionTypeInterventionPiece trouver(int id) {
		return Global.getFactory().getInterventionTypeInterventionPieceDAO().rechercher(id);
	}
	
	public static List<C_InterventionTypeInterventionPiece> lister(){
		return Global.getFactory().getInterventionTypeInterventionPieceDAO().lister();
	}
}
