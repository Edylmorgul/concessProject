package Model.BEANS;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class C_Indisponibilite implements Serializable{
	private static final long serialVersionUID = 1L;

    //Attributs
    private int id = 0;
    private C_Garagiste garagiste = null;
    private Date debut = null;
    private Date fin = null;
    
    // Constructeurs
    public C_Indisponibilite() {}
    
    public C_Indisponibilite(C_Garagiste garagiste, Date debut, Date fin) {
		this.garagiste = garagiste;
		this.debut = debut;
		this.fin = fin;
	}
    
	// GET - SET
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public C_Garagiste getGaragiste() {
		return garagiste;
	}
	public void setGaragiste(C_Garagiste garagiste) {
		this.garagiste = garagiste;
	}
	public Date getDebut() {
		return debut;
	}
	public void setDebut(Date debut) {
		this.debut = debut;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	
	// Methodes
	public boolean creer() {
		return Global.getFactory().getIndisponibiliteDAO().creer(this);
	}
	
	public boolean modifier() {
		return Global.getFactory().getIndisponibiliteDAO().modifier(this);
	}
	
	public boolean supprimer() {
		return Global.getFactory().getIndisponibiliteDAO().supprimer(this);
	}
	
	public C_Indisponibilite trouver() {
		return Global.getFactory().getIndisponibiliteDAO().rechercher(this.id);
	}
	
	public C_Indisponibilite trouver(int id) {
		return Global.getFactory().getIndisponibiliteDAO().rechercher(id);
	}
	
	public static List<C_Indisponibilite> lister(){
		return Global.getFactory().getIndisponibiliteDAO().lister();
	}
}
