package Model.BEANS;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

public class C_Rdv implements Serializable{
	private static final long serialVersionUID = 1L;

    //Attributs
    private int id = 0;
    private C_Intervention intervention = null;
    private C_Voiture voiture = null;
    private C_Garagiste garagiste = null;
    private Timestamp debut = null;
    private Timestamp fin = null;

    //Constructeurs
    public C_Rdv(){}
   
    public C_Rdv(C_Intervention intervention, C_Voiture voiture, C_Garagiste garagiste, Timestamp debut,
    		Timestamp fin) {
		this.intervention = intervention;
		this.voiture = voiture;
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

	public C_Intervention getIntervention() {
		return intervention;
	}

	public void setIntervention(C_Intervention intervention) {
		this.intervention = intervention;
	}

	public C_Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(C_Voiture voiture) {
		this.voiture = voiture;
	}

	public C_Garagiste getGaragiste() {
		return garagiste;
	}

	public void setGaragiste(C_Garagiste garagiste) {
		this.garagiste = garagiste;
	}

	public Timestamp getDebut() {
		return debut;
	}

	public void setDebut(Timestamp debut) {
		this.debut = debut;
	}

	public Timestamp getFin() {
		return fin;
	}

	public void setFin(Timestamp fin) {
		this.fin = fin;
	}
	
	// Methodes
	public boolean creer() {
		return Global.getFactory().getRdvDAO().creer(this);
	}
	
	public boolean modifier() {
		return Global.getFactory().getRdvDAO().modifier(this);
	}
	
	public boolean supprimer() {
		return Global.getFactory().getRdvDAO().supprimer(this);
	}
	
	public C_Rdv trouver() {
		return Global.getFactory().getRdvDAO().rechercher(this.id);
	}
	
	public C_Rdv trouver(int id) {
		return Global.getFactory().getRdvDAO().rechercher(id);
	}
	
	public static List<C_Rdv> lister(){
		return Global.getFactory().getRdvDAO().lister();
	}

	public static LocalDate ChargerSemaine(){
		LocalDate aujourdhui = LocalDate.now();
		if ( DayOfWeek.from(aujourdhui).getValue() <= 5)
		{
			return aujourdhui.with(previousOrSame(MONDAY));
		}
		else 
		{
			return aujourdhui.with(previousOrSame(MONDAY)).plusDays(7);
		}
	}

	public static List<String> VerifierMomentsDisponibles(LocalDate date, C_TypeIntervention typeIntervention){
		
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("ddMMuuuu");
		List<String> dispos = new LinkedList<String>();
		List<String> Tdispos = new LinkedList<String>();
		
		int Debut =8*60;
		int Fin =16*60;
		
		String debut = "";
		String fin = "";
		int intIDG = 0;
		
		while ((Debut + typeIntervention.getDureepr()) <= Fin)
		{
			Tdispos.add(String.format("%02d",((Debut-(Debut%60))/60))+":"+ String.format("%02d",(Debut%60)) +";"+String.format("%02d",(((Debut+ typeIntervention.getDureepr())-((Debut+ typeIntervention.getDureepr())%60))/60))+":"+String.format("%02d",((Debut+ typeIntervention.getDureepr())%60)));
			Debut+=30;
		}
		
		if(date.compareTo(LocalDate.now()) == 0)
		{
			LocalDateTime debutDispos = LocalDateTime.now().plusMinutes(15);
			int intDebut = (debutDispos.getHour()*60)+debutDispos.getMinute();
			int intDispo = 0;
			
			for (String dispo : Tdispos)
			{	
				debut = date.format(formatters) + dispo.split(":")[0] + (dispo.split(":")[1]).split(";")[0];
				//System.out.println(debut);
				fin = date.format(formatters) + (dispo.split(";")[1].split(":")[0]) + dispo.split(":")[2];
				//System.out.println(fin);
				intDispo = (Integer.parseInt(dispo.split(":")[0])*60)+Integer.parseInt((dispo.split(":")[1]).split(";")[0]);
				intIDG = C_Garagiste.rechercherGaragistesDispos(typeIntervention, debut, fin);
				if(intDispo >= intDebut && intIDG != 0 )dispos.add(dispo+","+intIDG);
			}

		}
		else
		{
			for (String dispo : Tdispos)
			{
				debut = date.format(formatters) + dispo.split(":")[0] + (dispo.split(":")[1]).split(";")[0];
				//System.out.println(debut);
				fin = date.format(formatters) + (dispo.split(";")[1].split(":")[0]) + dispo.split(":")[2];
				//System.out.println(fin);
				intIDG = C_Garagiste.rechercherGaragistesDispos(typeIntervention, debut, fin);
				//System.out.println(intIDG);
				if(intIDG != 0 )dispos.add(dispo+","+intIDG);
				//System.out.println(dispo+"|"+intIDG);
				
			}
		}
		
		
		return dispos;
	}
	
	public static String GenererMomentsDisponibles(LocalDate date, C_TypeIntervention typeIntervention, boolean checkflag){
		
		String HTML = "";
		List<String> dispos = new LinkedList<>();
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String debut = "";
		String fin = "";
		String id = "";
		String idg = "";
		String hour = "";
		
		if (date.compareTo(LocalDate.now()) >= 0)
		{
			dispos = VerifierMomentsDisponibles(date, typeIntervention);
			
			for(String dispo : dispos)
			{ 
				debut = date.format(formatters) + " " + dispo.split(";")[0] + ":00";
				fin = date.format(formatters) + " " + (dispo.split(";")[1]).split(",")[0] + ":00";
				id = date+""+dispo.split(",")[0];
				idg = dispo.split(",")[1];
				hour = (dispo.split(",")[0]).replace(";", " - ");
				if(checkflag){
					HTML +=	"<div class=\"form-group text-center m-3\">\n"
								+ "\t<input type=\"radio\" id=\""+id+"\" name=\"date\" value=\""+debut+";"+fin+";"+idg+"\" checked>\n"
								+ "\t<label for=\""+id+"\">"+hour+"</label>\n"+
							"</div>";
					checkflag = false;
				}
				else{
					HTML +=	"<div class=\"form-group text-center m-3\">\n"
								+ "\t<input type=\"radio\" id=\""+id+"\" name=\"date\" value=\""+debut+";"+fin+";"+idg+"\">\n"
								+ "\t<label for=\""+id+"\">"+hour+"</label>\n"+
							"</div>";
				}
				HTML += "\n";
			}
		}
		return HTML;
	}
	
	@Override
    public String toString() { 
        return String.format(debut + " a " + fin + " Garagiste : " + garagiste.getNom() + " " + garagiste.getPrenom() + " Voiture : " + voiture.getModele() + " " + voiture.getMarque()); 
    }
}
