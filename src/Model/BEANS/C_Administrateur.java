package Model.BEANS;

import java.util.List;

public class C_Administrateur extends C_Utilisateur{
	private static final long serialVersionUID = 1L;
	
	// Attributs
	
	// Constructeurs
	public C_Administrateur() {
		super();
	}
	
	public C_Administrateur(String email, String mdp, String nom, String prenom, int genre, String telephone) {
		super(email, mdp, nom, prenom, genre, telephone);
	}
	
	// GET - SET
	
	// Methodes
	@Override
	public boolean creer() {
		if(super.creer())
			return Global.getFactory().getAdministrateurDAO().creer(this);
		
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
	public C_Administrateur trouver() {
		return Global.getFactory().getAdministrateurDAO().rechercher(this.id);	
	}
	
	@Override
	public C_Administrateur trouver(int id) {
		return Global.getFactory().getAdministrateurDAO().rechercher(id);	
	}

	public static List<C_Administrateur> lister() {
		return Global.getFactory().getAdministrateurDAO().lister();
	}

}
