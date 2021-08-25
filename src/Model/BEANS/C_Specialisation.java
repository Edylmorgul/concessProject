package Model.BEANS;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class C_Specialisation implements Serializable{
	private static final long serialVersionUID = 1L;

    //Attributs
    private int id = 0;
    private String nom = "";
    private List<C_Garagiste> listeGaragiste = null;

    //Constructeurs
    public C_Specialisation(){}
    
    public C_Specialisation(String nom) {
		this.nom = nom;
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
	
	public List<C_Garagiste> getListeGaragiste() {
		return listeGaragiste;
	}

	public void setListeGaragiste(List<C_Garagiste> listeGaragiste) {
		this.listeGaragiste = listeGaragiste;
	}
	
	// Methodes
	public boolean creer() {
		return Global.getFactory().getSpecialisationDAO().creer(this);
	}
	
	public boolean modifier() {
		return Global.getFactory().getSpecialisationDAO().modifier(this);
	}
	
	public boolean supprimer() {
		return Global.getFactory().getSpecialisationDAO().supprimer(this);
	}
	
	public C_Specialisation trouver() {
		return Global.getFactory().getSpecialisationDAO().rechercher(this.id);
	}
	
	public C_Specialisation trouver(int id) {
		return Global.getFactory().getSpecialisationDAO().rechercher(id);
	}
	
	public static List<C_Specialisation> lister(){
		return Global.getFactory().getSpecialisationDAO().lister();
	}
	
	// Afficher les garagistes par spécialités
	public void listeGaragisteParSpecialisation() {
		
	}
	
	@Override
	public boolean equals(Object obj) {
		C_Specialisation spe;
		if (obj ==null || obj.getClass()!=this.getClass()){
        	return false;
        }
		
		else {
			spe =(C_Specialisation)obj;
			if(spe.getId() == getId()
					&spe.getNom().equals(getNom())) 
			{
				return true;
			}
			
			else
				return false;	
		}		
	}
	
	@Override
    public int hashCode() {
    	return Objects.hash(id, nom);
    }
}
