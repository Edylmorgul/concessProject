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

import Model.BEANS.C_Garagiste;
import Model.BEANS.C_Specialisation;
import Model.BEANS.C_TypeIntervention;
import Model.BEANS.Global;

@Path("Garagiste")
public class A_Garagiste {

	@POST
	@Path("creerGaragiste")
	@Produces(MediaType.APPLICATION_JSON)
	public Response creerGaragiste(
			@FormParam("email") String email,
			@FormParam("mdp") String mdp,
			@FormParam("nom") String nom,
			@FormParam("prenom") String prenom,
			@FormParam("genre") String strGenre,
			@FormParam("telephone") String telephone,
			@FormParam("actif") String strActif){
		C_Garagiste gara = new C_Garagiste();
		int genre = Global.tryParseInt(strGenre);
		int actif = Global.tryParseInt(strActif);
		gara.setEmail(email);
		gara.setMdp(mdp);
		gara.setNom(nom);
		gara.setPrenom(prenom);
		gara.setGenre(genre);
		gara.setTelephone(telephone);
		gara.setActif(actif);
				
		if(gara.creer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(gara).build();				
	}	
	
	@PUT
	@Path("modifierGaragiste")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifierGaragiste(
			@FormParam("id") String strId,
			@FormParam("email") String email,
			@FormParam("mdp") String mdp,
			@FormParam("nom") String nom,
			@FormParam("prenom") String prenom,
			@FormParam("genre") String strGenre,
			@FormParam("telephone") String telephone,
			@FormParam("actif") String strActif,
			@FormParam("specialisation") String strIdSpecialisation){
		C_Garagiste gara = new C_Garagiste();
		C_Specialisation spe = new C_Specialisation();
		int id = Global.tryParseInt(strId);
		int genre = Global.tryParseInt(strGenre);
		int actif = Global.tryParseInt(strActif);
		int idSpecialisation = Global.tryParseInt(strIdSpecialisation);
		gara.setId(id);
		gara.setEmail(email);
		gara.setMdp(mdp);
		gara.setNom(nom);
		gara.setPrenom(prenom);
		gara.setGenre(genre);
		gara.setTelephone(telephone);
		gara.setActif(actif);
		gara.setSpecialisation(spe.trouver(idSpecialisation));
		
		if(gara.modifier())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(null).build();
	}

	@DELETE
	@Path("supprimerGaragiste")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerGaragiste(@QueryParam("id") int id) {
		C_Garagiste gara = new C_Garagiste();
		gara.setId(id);
		
		if(gara.supprimer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null).build();
		
	}
	
	@GET
	@Path("trouverGaragiste")
	@Produces(MediaType.APPLICATION_JSON)
	public Response trouverGaragiste(@QueryParam("id") int id) {
		C_Garagiste gara = new C_Garagiste();
		gara = gara.trouver(id);
		
		if(gara != null)
			return Response.status(Response.Status.OK).entity(gara).build();
		else
			return Response.status(Response.Status.NO_CONTENT).entity(null).build();
	}
	
	@GET
	@Path("listerGaragiste")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerGaragiste() {
		return Response.status(Response.Status.OK).entity(C_Garagiste.lister()).build();
	}
	
	@GET
    @Path("rechercherGaragistesDispos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response rechercherGaragistesDispos(@QueryParam("idtypeInter") int idtypeInter, @QueryParam("debut") String debut, @QueryParam("idtypeInter") String fin) {
        C_TypeIntervention typeInter = new C_TypeIntervention();
        C_Garagiste.rechercherGaragistesDispos(typeInter.trouver(idtypeInter), debut, fin);

        return Response.status(Response.Status.OK).entity(null).build();
    }
	
}
