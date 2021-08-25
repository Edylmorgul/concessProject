package Model.BEANS;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class C_TypeIntervention implements Serializable{
	private static final long serialVersionUID = 1L;

    //Attributs
    private int id = 0;
    private String nom = "";
    private Double honoraire = 0.0;
    private int dureepr = 0;
    private C_Specialisation specialisation;
    private List<C_Piece> listePiece = null;

    //Constructeurs
    public C_TypeIntervention(){}
    
    public C_TypeIntervention(String nom, Double honoraire, int dureepr, C_Specialisation specialisation) {
		this.nom = nom;
		this.honoraire = honoraire;
		this.dureepr = dureepr;
		this.specialisation = specialisation;
	}

	// GET - SET
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getHonoraire() {
		return honoraire;
	}

	public void setHonoraire(Double honoraire) {
		this.honoraire = honoraire;
	}

	public int getDureepr() {
		return dureepr;
	}

	public void setDureepr(int dureepr) {
		this.dureepr = dureepr;
	}
	
	public C_Specialisation getSpecialisation() {
		return specialisation;
	}
	
	public void setSpecialisation(C_Specialisation specialisation) {
		this.specialisation = specialisation;
	}
	
	public List<C_Piece> getListPiece(){
		return listePiece;
	}
	
	public void setListePiece(List<C_Piece> listePiece) {
		this.listePiece = listePiece;
	}
	
	// Methodes
	public boolean creer() {
		return Global.getFactory().getTypeInterventionDAO().creer(this);
	}
	
	public boolean modifier() {
		return Global.getFactory().getTypeInterventionDAO().modifier(this);
	}
	
	public boolean supprimer() {
		return Global.getFactory().getTypeInterventionDAO().supprimer(this);
	}
	
	public C_TypeIntervention trouver() {
		return Global.getFactory().getTypeInterventionDAO().rechercher(this.id);
	}
	
	public C_TypeIntervention trouver(int id) {
		return Global.getFactory().getTypeInterventionDAO().rechercher(id);
	}
	
	public static List<C_TypeIntervention> lister(){
		return Global.getFactory().getTypeInterventionDAO().lister();
	}
	
	// Obtenir la liste des pièces d'un type d'intervention
	public void listePieceParIntervention() {
		listePiece = new LinkedList<>();
		List<C_TypeInterventionPiece> listeInterPiece = C_TypeInterventionPiece.lister();
		
		for(C_TypeInterventionPiece interPiece : listeInterPiece) {
			if(interPiece.getTypeIntervention().getId() == this.id) {
				listePiece.add(interPiece.getPiece());
			}
		}
	}
	
	@Override
    public String toString() {
        
        switch(specialisation.getNom())
        {
        	case "Electricien" : 
        		return String.format(nom + " - " + "Coût de l'intervention (TVAC, sans pièce) : " + String.format("%.2f", honoraire*Global.tva*Global.primeElectricien) + Global.euro + " - " + "Durée estimée : " + dureepr + " minutes");
        	
        	case "Mécanicien" :
        		return String.format(nom + " - " + "Coût de l'intervention (TVAC, sans pièce) : " + String.format("%.2f", honoraire*Global.tva*Global.primeMecanicien) + Global.euro + " - " + "Durée estimée : " + dureepr + " minutes");
        		
        	case "Carrossier" :
        		return String.format(nom + " - " + "Coût de l'intervention (TVAC, sans pièce) : " + String.format("%.2f", honoraire*Global.tva*Global.primeCarrossier) + Global.euro + " - " + "Durée estimée : " + dureepr + " minutes");
        	
        	default :
        		return String.format(nom + " - " + "Coût de l'intervention (TVAC, sans pièce) : " + String.format("%.2f", honoraire*Global.tva) + Global.euro + " - " + "Durée estimée : " + dureepr + " minutes");
        } 
    }	
	
	public Double renvoyerCoutReel() {
		
		switch(specialisation.getNom())
        {
        	case "Electricien" : 
        		return honoraire*Global.tva*Global.primeElectricien;
        	
        	case "Mécanicien" :
        		return honoraire*Global.tva*Global.primeMecanicien;
        		
        	case "Carrossier" :
        		return honoraire*Global.tva*Global.primeCarrossier;
        	
        	default :
        		return honoraire*Global.tva;
        }
    }
}
