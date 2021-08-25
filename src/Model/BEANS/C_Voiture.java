package Model.BEANS;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class C_Voiture implements Serializable{
	private static final long serialVersionUID = 1L;

    //Attributs
    private int id = 0;
    private C_Client client = null;
    private String numimm = "";
    private String numchas = "";
    private String couleur ="";
    private int km = 0;
    private Date primm = null;
    private String marque = "";
    private String modele = "";
    private List<C_Rdv> listeRdv = null;

    //Constructeurs
    public C_Voiture(){}
    
    public C_Voiture(C_Client client, String numimm, String numchas, String couleur, int km, Date primm,
			String marque, String modele) {
		this.client = client;
		this.numimm = numimm;
		this.numchas = numchas;
		this.couleur = couleur;
		this.km = km;
		this.primm = primm;
		this.marque = marque;
		this.modele = modele;
	}

	// GET - SET
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public C_Client getClient() {
		return client;
	}

	public void setClient(C_Client client) {
		this.client = client;
	}

	public String getNumimm() {
		return numimm;
	}

	public void setNumimm(String numimm) {
		this.numimm = numimm;
	}

	public String getNumchas() {
		return numchas;
	}

	public void setNumchas(String numchas) {
		this.numchas = numchas;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public Date getPrimm() {
		return primm;
	}

	public void setPrimm(Date primm) {
		this.primm = primm;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}
	
	public List<C_Rdv> getListeRdv() {
		return listeRdv;
	}

	public void setListeRdv(List<C_Rdv> listeRdv) {
		this.listeRdv = listeRdv;
	}
	
	// Methodes
	public boolean creer() {
		return Global.getFactory().getVoitureDAO().creer(this);
	}
	
	public boolean modifier() {
		return Global.getFactory().getVoitureDAO().modifier(this);
	}
	
	public boolean supprimer() {
		return Global.getFactory().getVoitureDAO().supprimer(this);
	}
	
	public C_Voiture trouver() {
		return Global.getFactory().getVoitureDAO().rechercher(this.id);
	}
	
	public C_Voiture trouver(int id) {
		return Global.getFactory().getVoitureDAO().rechercher(id);
	}
	
	public static List<C_Voiture> lister(){
		return Global.getFactory().getVoitureDAO().lister();
	}
	
	// Récupérer l'historique rdv d'un client
	public void listeRdvParClient() {
		List<C_Rdv> liste = C_Rdv.lister();
		this.listeRdv = new LinkedList<>();
			
		for(C_Rdv rdv : liste) {
			if(rdv.getVoiture().getId() == this.id) {
				this.listeRdv.add(rdv);
			}
		}
	}
}
