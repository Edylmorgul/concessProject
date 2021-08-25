package Model.BEANS;

import java.util.LinkedList;
import java.util.List;

public class C_Client extends C_Utilisateur{
	private static final long serialVersionUID = 1L;
	
	// Attributs
	private List<C_Voiture> listeVoiture;
	
	// Constructeurs
	public C_Client() {
		super();
	}
	
	public C_Client(String email, String mdp, String nom, String prenom, int genre, String telephone) {
		super(email, mdp, nom, prenom, genre, telephone);
	}
	
	// GET - SET
	public List<C_Voiture> getListeVoiture() {
		return listeVoiture;
	}

	public void setListeVoiture(List<C_Voiture> listeVoiture) {
		this.listeVoiture = listeVoiture;
	}
	
	// Methodes
	@Override
	public boolean creer() {
		if(super.creer())
			return Global.getFactory().getClientDAO().creer(this);
		
		return false;
	}

	@Override
	public boolean supprimer() {
		return super.supprimer();
	}

	@Override
	public boolean modifier() {
		return super.modifier();			
	}
	
	@Override
	public C_Client trouver() {
		return Global.getFactory().getClientDAO().rechercher(this.id);	
	}
	
	@Override
	public C_Client trouver(int id) {
		return Global.getFactory().getClientDAO().rechercher(id);	
	}

	public static List<C_Client> lister() {
		return Global.getFactory().getClientDAO().lister();
	}
	
	// Récupérer les voitures d'un client
	public void listeVoitureParClient() {
		List<C_Voiture> liste = C_Voiture.lister();
		this.listeVoiture = new LinkedList<>();
		
		for(C_Voiture v : liste) {
			if(v.getClient().getId() == this.id) {
				this.listeVoiture.add(v);
			}
		}
	}
}
