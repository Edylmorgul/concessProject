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

import Model.BEANS.C_TypeIntervention;
import Model.BEANS.Global;

@Path("TypeIntervention")
public class A_TypeIntervention {

	@POST
	@Path("creerTypeIntervention")
	@Produces(MediaType.APPLICATION_JSON)
	public Response creerTypeIntervention(
			@FormParam("nom") String nom,
			@FormParam("honoraire") String strHonoraire,
			@FormParam("dureepr") String strDureepr){
		
		C_TypeIntervention typeInt = new C_TypeIntervention();
		Double honoraire = Global.tryParseDouble(strHonoraire);
		int dureepr = Global.tryParseInt(strDureepr);
		
		typeInt.setNom(nom);
		typeInt.setHonoraire(honoraire);
		typeInt.setDureepr(dureepr);
				
		if(typeInt.creer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(typeInt).build();				
	}	
	
	@PUT
	@Path("modifierTypeIntervention")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifierTypeIntervention(
			@FormParam("nom") String strId,
			@FormParam("nom") String nom,
			@FormParam("honoraire") String strHonoraire,
			@FormParam("dureepr") String strDureepr){
		C_TypeIntervention typeInt = new C_TypeIntervention();
		int id = Global.tryParseInt(strId);
		Double honoraire = Global.tryParseDouble(strHonoraire);
		int dureepr = Global.tryParseInt(strDureepr);
		
		typeInt.setId(id);
		typeInt.setNom(nom);
		typeInt.setHonoraire(honoraire);
		typeInt.setDureepr(dureepr);
		
		if(typeInt.modifier())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(null).build();
	}

	@DELETE
	@Path("supprimerTypeIntervention")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerTypeIntervention(@QueryParam("id") int id) {
		C_TypeIntervention typeInt = new C_TypeIntervention();
		typeInt.setId(id);
		
		if(typeInt.supprimer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null).build();		
	}
	
	@GET
	@Path("trouverTypeIntervention")
	@Produces(MediaType.APPLICATION_JSON)
	public Response trouverTypeIntervention(@QueryParam("id") int id) {
		C_TypeIntervention typeInt = new C_TypeIntervention();
		typeInt = typeInt.trouver(id);
		
		if(typeInt != null)
			return Response.status(Response.Status.OK).entity(typeInt).build();
		else
			return Response.status(Response.Status.NO_CONTENT).entity(null).build();
	}
	
	@GET
	@Path("listerTypeIntervention")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerTypeIntervention() {
		return Response.status(Response.Status.OK).entity(C_TypeIntervention.lister()).build();
	}
}
