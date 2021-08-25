package Model.BEANS;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class C_Code implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// Attributs
	private int id = 0;
	private String cle = "";
	private Date creation = null;
	private Date utilisation = null;
	
	//Constructeurs
	public C_Code(String cle, Date creation, Date utilisation) {
		this.cle = cle;
		this.creation = creation;
		this.utilisation = utilisation;
	}

	// GET - SET
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCle() {
		return cle;
	}

	public void setCle(String cle) {
		this.cle = cle;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public Date getUtilisation() {
		return utilisation;
	}

	public void setUtilisation(Date utilisation) {
		this.utilisation = utilisation;
	}
	
	// Methodes
	public boolean creer() {
		return Global.getFactory().getCodeDAO().creer(this);
	}
	
	public boolean modifier() {
		return Global.getFactory().getCodeDAO().modifier(this);
	}
	
	public boolean supprimer() {
		return Global.getFactory().getCodeDAO().supprimer(this);
	}
	
	public C_Code trouver() {
		return Global.getFactory().getCodeDAO().rechercher(this.id);
	}
	
	public C_Code trouver(int id) {
		return Global.getFactory().getCodeDAO().rechercher(id);
	}
	
	public static List<C_Code> lister(){
		return Global.getFactory().getCodeDAO().lister();
	}
}
