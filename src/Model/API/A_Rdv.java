package Model.API;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
import Model.BEANS.C_Intervention;
import Model.BEANS.C_Rdv;
import Model.BEANS.C_Voiture;
import Model.BEANS.Global;

@Path("Rdv")
public class A_Rdv {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@POST
	@Path("creerRdv")
	@Produces(MediaType.APPLICATION_JSON)
	public Response creerRdv(
			@FormParam("debut") String strDebut,
			@FormParam("fin") String strFin,
			@FormParam("intervention") String strIdIntervention,
			@FormParam("voiture") String strIdVoiture,
			@FormParam("garagiste") String strIdGaragiste) {
		
		C_Rdv rdv = new C_Rdv();
		C_Voiture v = new C_Voiture();
		C_Intervention inter = new C_Intervention();
		C_Garagiste gara = new C_Garagiste();
		Timestamp debut = Timestamp.valueOf(strDebut+"00");
		Timestamp fin = Timestamp.valueOf(strFin+"00");
		int idIntervention = Global.tryParseInt(strIdIntervention);
		int idVoiture = Global.tryParseInt(strIdVoiture);
		int idGaragiste = Global.tryParseInt(strIdGaragiste);
		
		rdv.setDebut(debut);
		rdv.setFin(fin);
		rdv.setIntervention(inter.trouver(idIntervention));
		rdv.setVoiture(v.trouver(idVoiture));
		rdv.setGaragiste(gara.trouver(idGaragiste));
				
		if(rdv.creer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(rdv).build();				
	}	
	
	@PUT
	@Path("modifierRdv")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifierRdv(
			@FormParam("debut") String strId,
			@FormParam("debut") String strDebut,
			@FormParam("fin") String strFin,
			@FormParam("intervention") String strIdIntervention,
			@FormParam("voiture") String strIdVoiture,
			@FormParam("garagiste") String strIdGaragiste)  {
		
		int id = Global.tryParseInt(strId);
		C_Rdv rdv = new C_Rdv();
		C_Voiture v = new C_Voiture();
		C_Intervention inter = new C_Intervention();
		C_Garagiste gara = new C_Garagiste();
		Timestamp debut = Timestamp.valueOf(strDebut+"00");
		Timestamp fin = Timestamp.valueOf(strFin+"00");
		int idIntervention = Global.tryParseInt(strIdIntervention);
		int idVoiture = Global.tryParseInt(strIdVoiture);
		int idGaragiste = Global.tryParseInt(strIdGaragiste);
		
		rdv.setId(id);
		rdv.setDebut(debut);
		rdv.setFin(fin);
		rdv.setIntervention(inter.trouver(idIntervention));
		rdv.setVoiture(v.trouver(idVoiture));
		rdv.setGaragiste(gara.trouver(idGaragiste));
		
		if(rdv.modifier())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(null).build();
	}

	@DELETE
	@Path("supprimerRdv")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerRdv(@QueryParam("id") int id) {
		C_Rdv rdv = new C_Rdv();
		rdv.setId(id);
		
		if(rdv.supprimer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null).build();
		
	}
	
	@GET
	@Path("trouverRdv")
	@Produces(MediaType.APPLICATION_JSON)
	public Response trouverRdv(@QueryParam("id") int id) {
		C_Rdv rdv = new C_Rdv();
		rdv = rdv.trouver(id);
		
		if(rdv != null)
			return Response.status(Response.Status.OK).entity(rdv).build();
		else
			return Response.status(Response.Status.NO_CONTENT).entity(null).build();
	}
	
	@GET
	@Path("listerRdv")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerRdv() {
		return Response.status(Response.Status.OK).entity(C_Rdv.lister()).build();
	}
}
