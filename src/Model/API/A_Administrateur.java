package Model.API;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Model.BEANS.C_Administrateur;
import Model.BEANS.Global;

@Path("Administrateur")
public class A_Administrateur {

	@POST
	@Path("creerAdministrateur")
	@Produces(MediaType.APPLICATION_JSON)
	public Response creerAdministrateur(
			@FormParam("email") String email,
			@FormParam("mdp") String mdp,
			@FormParam("nom") String nom,
			@FormParam("prenom") String prenom,
			@FormParam("genre") String strGenre,
			@FormParam("telephone") String telephone,
			@FormParam("actif") String strActif){
		C_Administrateur admin = new C_Administrateur();
		int genre = Global.tryParseInt(strGenre);
		int actif = Global.tryParseInt(strActif);
		admin.setEmail(email);
		admin.setMdp(mdp);
		admin.setNom(nom);
		admin.setPrenom(prenom);
		admin.setGenre(genre);
		admin.setTelephone(telephone);
		admin.setActif(actif);
				
		if(admin.creer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(admin).build();				
	}	
	
	@PUT
	@Path("modifierAdministrateur")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifierAdministrateur(
			@FormParam("id") String strId,
			@FormParam("email") String email,
			@FormParam("mdp") String mdp,
			@FormParam("nom") String nom,
			@FormParam("prenom") String prenom,
			@FormParam("genre") String strGenre,
			@FormParam("telephone") String telephone,
			@FormParam("actif") String strActif) {
		C_Administrateur admin = new C_Administrateur();
		int id = Global.tryParseInt(strId);
		int genre = Global.tryParseInt(strGenre);
		int actif = Global.tryParseInt(strActif);
		
		admin.setId(id);
		admin.setEmail(email);
		admin.setMdp(mdp);
		admin.setNom(nom);
		admin.setPrenom(prenom);
		admin.setGenre(genre);
		admin.setTelephone(telephone);
		admin.setActif(actif);
		
		if(admin.modifier())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(null).build();
	}

	@DELETE
	@Path("supprimerAdministrateur")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerAdministrateur(@QueryParam("id") int id) {
		C_Administrateur admin = new C_Administrateur();
		admin.setId(id);
		
		if(admin.supprimer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null).build();
		
	}
	
	@GET
	@Path("trouverAdministrateur")
	@Produces(MediaType.APPLICATION_JSON)
	public Response trouverAdministrateur(@QueryParam("id") int id) {
		C_Administrateur admin = new C_Administrateur();
		admin = admin.trouver(id);
		
		if(admin != null)
			return Response.status(Response.Status.OK).entity(admin).build();
		else
			return Response.status(Response.Status.NO_CONTENT).entity(null).build();
	}
	
	@GET
	@Path("listerAdministrateur")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerAdministrateur() {
		return Response.status(Response.Status.OK).entity(C_Administrateur.lister()).build();
	}
}
