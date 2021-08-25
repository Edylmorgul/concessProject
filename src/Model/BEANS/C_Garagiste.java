package Model.BEANS;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import Model.DAO.ConnectDatabase;
import Model.DAO.D_Garagiste;

public class C_Garagiste extends C_Utilisateur{
	private static final long serialVersionUID = 1L;
	
	// Attributs
	C_Specialisation specialisation = null;
	List<C_Indisponibilite> listeIndisponibilite = null;
	List<C_Rdv> listeRdv = null;
	
	// Constructeurs
	public C_Garagiste() {
		super();
	}
	
	public C_Garagiste(String email, String mdp, String nom, String prenom, int genre, String telephone, C_Specialisation specialisation) {		
		super(email, mdp, nom, prenom, genre, telephone);
		this.specialisation = specialisation;
	}
	
	// GET - SET
	public C_Specialisation getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(C_Specialisation specialisation) {
		this.specialisation = specialisation;
	}
	
	public List<C_Indisponibilite> getListeIndisponibilite() {
		return listeIndisponibilite;
	}

	public void setListeIndisponibilite(List<C_Indisponibilite> listeIndisponibilite) {
		this.listeIndisponibilite = listeIndisponibilite;
	}
	
	public List<C_Rdv> getListeRdv() {
		return listeRdv;
	}

	public void setListeRdv(List<C_Rdv> listeRdv) {
		this.listeRdv = listeRdv;
	}
	
	// Methodes
	@Override
	public boolean creer() {
		if(super.creer())
			return Global.getFactory().getGaragisteDAO().creer(this);
		
		return false;
	}

	@Override
	public boolean supprimer() {
		return super.supprimer();
	}

	@Override
	public boolean modifier() {
		if(super.modifier())
			return Global.getFactory().getGaragisteDAO().modifier(this);
		
		return false;
	}
	
	@Override
	public C_Garagiste trouver() {
		return Global.getFactory().getGaragisteDAO().rechercher(this.id);	
	}
	
	@Override
	public C_Garagiste trouver(int id) {
		return Global.getFactory().getGaragisteDAO().rechercher(id);	
	}

	public static List<C_Garagiste> lister() {
		return Global.getFactory().getGaragisteDAO().lister();
	}
	
	// Récupérer les absences d'un garagiste
	public void listeIndisponibiliteParGaragiste() {
		List<C_Indisponibilite> liste = C_Indisponibilite.lister();
		this.listeIndisponibilite = new LinkedList<>();
			
		for(C_Indisponibilite i : liste) {
			if(i.getGaragiste().getId() == this.id) {
				this.listeIndisponibilite.add(i);
			}
		}
	}
	
	// Récupérer l'historique rdv d'un garagiste
	public void listeRdvParGaragiste() {
		List<C_Rdv> liste = C_Rdv.lister();
		this.listeRdv = new LinkedList<>();
				
		for(C_Rdv rdv : liste) {
			//System.out.println("id courant : " + this.id + " id RDV : " + rdv.getId() + " id GARAGISTE : " + rdv.getGaragiste().getId() + " " + rdv.toString());
			if(rdv.getGaragiste().getId() == this.id) {
				this.listeRdv.add(rdv);
			}
		}
	}
	
	public static int rechercherGaragistesDispos(C_TypeIntervention typeIntervention, String debut, String fin) {
        Connection conn = ConnectDatabase.getInstance();
        D_Garagiste d_garagiste = new D_Garagiste(conn);
        return d_garagiste.rechercherGaragistesDispos(typeIntervention, debut, fin);
    }
}
