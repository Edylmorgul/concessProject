package Model.BEANS;

import java.io.Serializable;
import java.util.List;

public class C_Intervention implements Serializable{
	private static final long serialVersionUID = 1L;

    //Attributs
    private int id = 0;
    private C_TypeIntervention typeIntervention = null;
    private int duree = 0;
    private Double ct = 0.0;
    
    //Constructeurs
    public C_Intervention(){}
    
    public C_Intervention(C_TypeIntervention typeIntervention, int duree,
			Double ct) {
		this.typeIntervention = typeIntervention;
		this.duree = duree;
		this.ct = ct;
	}

	// GET - SET
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public C_TypeIntervention getTypeIntervention() {
		return typeIntervention;
	}

	public void setTypeIntervention(C_TypeIntervention typeIntervention) {
		this.typeIntervention = typeIntervention;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Double getCt() {
		return ct;
	}

	public void setCt(Double ct) {
		this.ct = ct;
	}
	
	// Methodes
	public boolean creer() {
		return Global.getFactory().getInterventionDAO().creer(this);
	}
	
	public boolean modifier() {
		return Global.getFactory().getInterventionDAO().modifier(this);
	}
	
	public boolean supprimer() {
		return Global.getFactory().getInterventionDAO().supprimer(this);
	}
	
	public C_Intervention trouver() {
		return Global.getFactory().getInterventionDAO().rechercher(this.id);
	}
	
	public C_Intervention trouver(int id) {
		return Global.getFactory().getInterventionDAO().rechercher(id);
	}
	
	public static List<C_Intervention> lister(){
		return Global.getFactory().getInterventionDAO().lister();
	}
}
