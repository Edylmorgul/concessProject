package Model.API;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import Model.BEANS.C_Client;
import Model.BEANS.C_Voiture;
import Model.BEANS.Global;

@Path("Voiture")
public class A_Voiture {
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	@POST
	@Path("creerVoiture")
	@Produces(MediaType.APPLICATION_JSON)
	public Response creerVoiture(
			@FormParam("numimm") String numimm,
			@FormParam("numchas") String numchas,
			@FormParam("couleur") String couleur,
			@FormParam("km") String strKm,
			@FormParam("primm") String strPrimm,
			@FormParam("marque") String marque,
			@FormParam("modele") String modele,
			@FormParam("client") String strIdClient){
		C_Voiture v = new C_Voiture();
		C_Client c = new C_Client();
		int km = Global.tryParseInt(strKm);
		Date primm = Global.tryParseDate(strPrimm, formatter);
		int idClient = Global.tryParseInt(strIdClient);
		
		v.setNumimm(numimm);
		v.setNumchas(numchas);
		v.setCouleur(couleur);
		v.setKm(km);
		v.setPrimm(primm);
		v.setMarque(marque);
		v.setModele(modele);
		v.setClient(c.trouver(idClient));
				
		if(v.creer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(v).build();				
	}	
	
	@PUT
	@Path("modifierVoiture")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifierVoiture(
			@FormParam("numimm") String strId,
			@FormParam("numimm") String numimm,
			@FormParam("numchas") String numchas,
			@FormParam("couleur") String couleur,
			@FormParam("km") String strKm,
			@FormParam("primm") String strPrimm,
			@FormParam("marque") String marque,
			@FormParam("modele") String modele,
			@FormParam("client") String strIdClient){
		C_Voiture v = new C_Voiture();
		C_Client c = new C_Client();
		int id = Global.tryParseInt(strId);
		int km = Global.tryParseInt(strKm);
		Date primm = Global.tryParseDate(strPrimm, formatter);
		int idClient = Global.tryParseInt(strIdClient);
		
		v.setId(id);
		v.setNumimm(numimm);
		v.setNumchas(numchas);
		v.setCouleur(couleur);
		v.setKm(km);
		v.setPrimm(primm);
		v.setMarque(marque);
		v.setModele(modele);
		v.setClient(c.trouver(idClient));
		
		if(v.modifier())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(null).build();
	}

	@DELETE
	@Path("supprimerVoiture")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerVoiture(@QueryParam("id") int id) {
		C_Voiture v = new C_Voiture();
		v.setId(id);
		
		if(v.supprimer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null).build();		
	}
	
	@GET
	@Path("trouverVoiture")
	@Produces(MediaType.APPLICATION_JSON)
	public Response trouverVoiture(@QueryParam("id") int id) {
		C_Voiture v = new C_Voiture();
		v = v.trouver(id);
		
		if(v != null)
			return Response.status(Response.Status.OK).entity(v).build();
		else
			return Response.status(Response.Status.NO_CONTENT).entity(null).build();
	}
	
	@GET
	@Path("listerVoiture")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerVoiture() {
		return Response.status(Response.Status.OK).entity(C_Voiture.lister()).build();
	}
}
