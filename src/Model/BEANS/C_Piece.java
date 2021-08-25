package Model.BEANS;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class C_Piece implements Serializable{
	private static final long serialVersionUID = 1L;

    //Attributs
    private int id = 0;
    private String nom = "";
    private Double prix = 0.0;

    //Constructeurs
    public C_Piece(){}
    
    public C_Piece(String nom, Double prix) {
		this.nom = nom;
		this.prix = prix;
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

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}
	
	// Methodes
	public boolean creer() {
		return Global.getFactory().getPieceDAO().creer(this);
	}
	
	public boolean modifier() {
		return Global.getFactory().getPieceDAO().modifier(this);
	}
	
	public boolean supprimer() {
		return Global.getFactory().getPieceDAO().supprimer(this);
	}
	
	public C_Piece trouver() {
		return Global.getFactory().getPieceDAO().rechercher(this.id);
	}
	
	public C_Piece trouver(int id) {
		return Global.getFactory().getPieceDAO().rechercher(id);
	}
	
	public static List<C_Piece> lister(){
		return Global.getFactory().getPieceDAO().lister();
	}
	
	@Override
	public boolean equals(Object piece1) {
		C_Piece piece2;
		if (piece1 == null || piece1.getClass() != this.getClass()){
        	return false;
        }
		
		else {
			piece2 =(C_Piece)piece1;
			if(piece2.getId() == this.getId() && piece2.getNom().equals(this.getNom()))return true;
			else return false;	
		}		
	}
	
	@Override
    public int hashCode() {
    	return Objects.hash(id, nom, prix);
    }
	
    public Double calculerPrix(int qt) {
        return this.prix * qt * Global.tva;
    }
}
