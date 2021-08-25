package Model.API;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import Model.BEANS.C_Utilisateur;
import Model.BEANS.Global;

import javax.ws.rs.core.MediaType;

@Path("Utilisateur")
public class A_Utilisateur {
	
	@POST
	@Path("creerUtilisateur") // Comme il n'y a qu'un seul POST, pas besoin de préciser Path mais je préfère comme ça
	@Produces(MediaType.APPLICATION_JSON)
	public Response creerUtilisateur(
			@FormParam("email") String email,
			@FormParam("mdp") String mdp,
			@FormParam("nom") String nom,
			@FormParam("prenom") String prenom,
			@FormParam("genre") String strGenre,
			@FormParam("telephone") String telephone,
			@FormParam("actif") String strActif){
		C_Utilisateur uti = new C_Utilisateur();
		int genre = Global.tryParseInt(strGenre);
		int actif = Global.tryParseInt(strActif);
		uti.setEmail(email);
		uti.setMdp(mdp);
		uti.setNom(nom);
		uti.setPrenom(prenom);
		uti.setGenre(genre);
		uti.setTelephone(telephone);
		uti.setActif(actif);
				
		if(uti.creer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(uti).build();				
	}	
	
	@PUT
	@Path("modifierUtilisateur")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifierUtilisateur(
			@FormParam("id") String strId,
			@FormParam("email") String email,
			@FormParam("mdp") String mdp,
			@FormParam("nom") String nom,
			@FormParam("prenom") String prenom,
			@FormParam("genre") String strGenre,
			@FormParam("telephone") String telephone,
			@FormParam("actif") String strActif) {
		C_Utilisateur uti = new C_Utilisateur();
		int id = Global.tryParseInt(strId);
		int actif = Global.tryParseInt(strActif);
		int genre = Global.tryParseInt(strGenre);
		uti.setId(id);
		uti.setEmail(email);
		uti.setMdp(mdp);
		uti.setNom(nom);
		uti.setPrenom(prenom);
		uti.setGenre(genre);
		uti.setTelephone(telephone);
		uti.setActif(actif);
		
		if(uti.modifier())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(null).build();
	}

	@DELETE
	@Path("supprimerUtilisateur")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerUtilisateur(@QueryParam("id") int id) {
		C_Utilisateur uti = new C_Utilisateur();
		uti.setId(id);
		
		if(uti.supprimer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null).build();
		
	}
	
	@GET
	@Path("trouverUtilisateur")
	@Produces(MediaType.APPLICATION_JSON)
	public Response trouverUtilisateur(@QueryParam("id") int id) {
		C_Utilisateur uti = new C_Utilisateur();
		uti = uti.trouver(id);
		
		if(uti != null)
			return Response.status(Response.Status.OK).entity(uti).build();
		else
			return Response.status(Response.Status.NO_CONTENT).entity(null).build();
	}
	
	@GET
	@Path("listerUtilisateur")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerUtilisateur() {
		return Response.status(Response.Status.OK).entity(C_Utilisateur.lister()).build();
	}
}