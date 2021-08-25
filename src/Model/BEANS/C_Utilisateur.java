package Model.BEANS;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class C_Utilisateur implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// Attributs
	protected int id = 0;
	protected String email = "";
	protected String mdp = "";
	protected String nom ="";
	protected String prenom;
	protected int genre = 0;
	protected String telephone = "";
	protected int actif = 0;
	
	// Constructeurs
	public C_Utilisateur() {}
	
	public C_Utilisateur(String email, String mdp, String nom, String prenom, int genre, String telephone) {
		this.email = email;
		this.mdp = mdp;
		this.nom = nom;
		this.prenom = prenom;
		this.genre = genre;
		this.telephone = telephone;
		this.actif = 1;
	}

	// GET - SET
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getGenre() {
		return genre;
	}

	public void setGenre(int genre) {
		this.genre = genre;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public int getActif() {
		return actif;
	}
	
	public void setActif(int actif) {
		this.actif = actif;
	}
	
	// Methodes
	public boolean creer() {
		this.email = this.email.toLowerCase();
		return Global.getFactory().getUtilisateurDAO().creer(this);
	}
	
	public boolean modifier() {
		return Global.getFactory().getUtilisateurDAO().modifier(this);
	}
	
	public boolean supprimer() {
		return Global.getFactory().getUtilisateurDAO().supprimer(this);
	}
	
	public C_Utilisateur trouver() {
		return Global.getFactory().getUtilisateurDAO().rechercher(this.id);
	}
	
	public C_Utilisateur trouver(int id) {
		return Global.getFactory().getUtilisateurDAO().rechercher(id);
	}
	
	public static List<? extends C_Utilisateur> lister(){
		return Global.getFactory().getUtilisateurDAO().lister();
	}
	
	// Crypter le mot de passe
	public static String crypterMdp(String mdp) {
		return BCrypt.hashpw(mdp, BCrypt.gensalt());
	}
	
	// Obtenir mot de passe crypté
	@SuppressWarnings("unchecked")
	public static String recupCryptMdp(String email){
		List<C_Utilisateur> liste = new LinkedList<>();
		String mdp = null;
			
		liste = (List<C_Utilisateur>) lister();
			
		for(int i = 0; i < liste.size(); i++) {
			if(liste.get(i).email.equals(email)) {
				mdp = liste.get(i).getMdp();
				break;
			}
		}
			
		return mdp;
	}
	
	// Vérifier si email déjà présent
	@SuppressWarnings("unchecked")
	public static boolean emailExiste(String email) {
		List<C_Utilisateur> list = new LinkedList<>();
		
		list = (List<C_Utilisateur>) lister();
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).email.equals(email)) {
				return true;
			}
		}
				
		return false;
	}
	
	// Connexion à un compte utilisateur
	@SuppressWarnings("unchecked")
	public C_Utilisateur connexion(String email, String mdp) {
		List<C_Utilisateur> list = new LinkedList<>();
		C_Utilisateur uti = null;
		email = email.toLowerCase();
		
		list = (List<C_Utilisateur>) lister();
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).email.equals(email) && list.get(i).mdp.equals(mdp)) {
				uti = list.get(i);
				break;
			}
		}
		
		return uti;
	}
	
	// Déterminer le type de l'utilisateur
    public Object recupTypeUti() {
    	
    	C_Administrateur admin = new C_Administrateur();
    	C_Client cli = new C_Client();
    	C_Garagiste gara = new C_Garagiste();
    	
    	cli = cli.trouver(this.id);
    	if(cli != null)
    		return cli;
        	    	
    	gara = gara.trouver(this.id);
    	if(gara != null)
    		return gara;
    	
    	admin = admin.trouver(this.id);      	
    	return admin;
    }
    
    // Description du genre
    public String getNomGenre() {
    	if(this.genre == 1)
    		return "Homme";
    	else if(this.genre == 2)
    		return "Femme";
    	else
    		return "Autre";
    }
    
    // Description actif
    public String getNomActif() {
    	if(this.actif == 1)
    		return "Oui";
    	else
    		return "Non";
    }
}
