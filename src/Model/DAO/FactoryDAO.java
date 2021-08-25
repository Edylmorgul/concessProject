package Model.DAO;

import java.sql.Connection;

import Model.BEANS.*;

public class FactoryDAO {
	
	protected static final Connection conn = ConnectDatabase.getInstance();
	
	// Utilisateur
	public DAO<C_Utilisateur> getUtilisateurDAO(){
		return new D_Utilisateur(conn);
	}
	
	// Client
	public DAO<C_Client> getClientDAO(){
		return new D_Client(conn);
	}
	
	// Garagiste
	public DAO<C_Garagiste> getGaragisteDAO(){
		return new D_Garagiste(conn);
	}
	
	// Administrateur
	public DAO<C_Administrateur> getAdministrateurDAO(){
		return new D_Administrateur(conn);
	}
	
	// Voiture
	public DAO<C_Voiture> getVoitureDAO(){
		return new D_Voiture(conn);
	}
	
	// Intervention
	public DAO<C_Intervention> getInterventionDAO(){
		return new D_Intervention(conn);
	}
	
	// Type intervention
	public DAO<C_TypeIntervention> getTypeInterventionDAO(){
		return new D_TypeIntervention(conn);
	}
	
	// Specialisation
	public DAO<C_Specialisation> getSpecialisationDAO(){
		return new D_Specialisation(conn);
	}
	
	// Indisponibilite
	public DAO<C_Indisponibilite> getIndisponibiliteDAO(){
		return new D_Indisponibilite(conn);
	}
	
	// Piece
	public DAO<C_Piece> getPieceDAO(){
		return new D_Piece(conn);
	}
	
	// RDV
	public DAO<C_Rdv> getRdvDAO(){
		return new D_Rdv(conn);
	}
	
	// Code
	public DAO<C_Code> getCodeDAO(){
		return new D_Code(conn);
	}
	
	// TypeInterventionPiece
	public DAO<C_TypeInterventionPiece> getTypeInterventionPieceDAO(){
		return new D_TypeInterventionPiece(conn);
	}
	
	// TypeInterventionInterventionPiece
	public DAO<C_InterventionTypeInterventionPiece> getInterventionTypeInterventionPieceDAO(){
		return new D_InterventionTypeInterventionPiece(conn);
	}
}
