package Model.BEANS;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import Model.DAO.FactoryDAO;

//Variables globales et utilitaires du projet
public class Global {
	
	public static final float tva = 1.21f;
	public static final float primeElectricien = 1.15f;
	public static final float primeMecanicien = 1.10f;
	public static final float primeCarrossier = 1.05f;
	public static final String euro = "&#x20AC;";
	private static FactoryDAO factory = new FactoryDAO();
	private static String letterPattern = "[a-zA-Z]+$"; // N'autorise que les lettres minuscules/majuscules
	private static String numberPattern = "^[0-9]*$"; // N'autorise que les nombres;
	private static String emailPattern = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$"; // V�rifie la validit� d'une adresse mail
	private static String phonePattern = "^[0-9]{3,4}[- .]?[0-9]{2}[- .]?[0-9]{2}[- .]?[0-9]{2}$"; // 10 nombres + permet les espaces, traits et points
	private static String passwordPattern = "^(?=.*[0-9])" // Doit contenir un chiffre
            				+ "(?=.*[a-z])(?=.*[A-Z])" // Doit contenir majuscule et minuscule
            				+ "(?=\\S+$).{4,20}$"; // Pas d'espace permis + longueur mdp 4 - 20
	private static String vinPattern ="^(?=.*[0-9])(?=.*[A-z])[a-zA-Z0-9]{11}[0-9]{6}$"; // Validiter du CODE VIN pour voiture
	
	public static FactoryDAO getFactory() {
		return factory;
	}	
	public static String getLetterPattern() {
		return letterPattern;
	}
	public static String getNumberPattern() {
		return numberPattern;
	}
	public static String getEmailPattern() {
		return emailPattern;
	}
	public static String getPhonePattern() {
		return phonePattern;
	}
	public static String getPasswordPattern() {
		return passwordPattern;
	}
	public static String getVinPattern() {
		return vinPattern;
	}
	
	public static Integer tryParseInt(String valeur) {
		try {
	        return Integer.parseInt(valeur);
	    } 
		catch (NumberFormatException e) {
			e.printStackTrace();
	        return null;
	    }
	}
	
	public static Double tryParseDouble(String valeur) {
		try {
	        return Double.parseDouble(valeur);
	    } 
		catch (NumberFormatException e) {
			e.printStackTrace();
	        return null;
	    }
	}
	
	public static Date tryParseDate(String valeur, SimpleDateFormat formatter) {
		try {
			return formatter.parse(valeur);
		}
		
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	public static Double renvoyerFormatMonetaire(Double montant) {
        return Math.round(montant * 100.0) / 100.0;
    }
	
}
