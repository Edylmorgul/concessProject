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

import Model.BEANS.C_Garagiste;
import Model.BEANS.C_Indisponibilite;
import Model.BEANS.Global;

@Path("Indisponibilite")
public class A_Indisponibilite {
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	@POST
	@Path("creerIndisponibilite")
	@Produces(MediaType.APPLICATION_JSON)
	public Response creerIndisponibilite(
			@FormParam("debut") String strDebut,
			@FormParam("fin") String strFin,
			@FormParam("garagiste") String strIdGaragiste){
		
		C_Indisponibilite indi = new C_Indisponibilite();
		C_Garagiste gara = new C_Garagiste();
		Date debut = Global.tryParseDate(strDebut, formatter);
		Date fin = Global.tryParseDate(strFin, formatter);
		int idGaragiste = Global.tryParseInt(strIdGaragiste);
		indi.setDebut(debut);
		indi.setFin(fin);
		indi.setGaragiste(gara.trouver(idGaragiste));
				
		if(indi.creer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(indi).build();				
	}	
	
	@PUT
	@Path("modifierIndisponibilite")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifierIndisponibilite(
			@FormParam("id") String strId,
			@FormParam("debut") String strDebut,
			@FormParam("fin") String strFin,
			@FormParam("garagiste") String strIdGaragiste){

		C_Indisponibilite indi = new C_Indisponibilite();
		C_Garagiste gara = new C_Garagiste();
		int id = Global.tryParseInt(strId);
		Date debut = Global.tryParseDate(strDebut, formatter);
		Date fin = Global.tryParseDate(strFin, formatter);
		int idGaragiste = Global.tryParseInt(strIdGaragiste);
		
		indi.setId(id);
		indi.setDebut(debut);
		indi.setFin(fin);
		indi.setGaragiste(gara.trouver(idGaragiste));
		
		if(indi.modifier())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(null).build();
	}

	@DELETE
	@Path("supprimerIndisponibilite")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerIndisponibilite(@QueryParam("id") int id) {
		C_Indisponibilite indi = new C_Indisponibilite();
		indi.setId(id);
		
		if(indi.supprimer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null).build();
		
	}
	
	@GET
	@Path("trouverIndisponibilite")
	@Produces(MediaType.APPLICATION_JSON)
	public Response trouverIndisponibilite(@QueryParam("id") int id) {
		C_Indisponibilite indi = new C_Indisponibilite();
		indi = indi.trouver(id);
		
		if(indi != null)
			return Response.status(Response.Status.OK).entity(indi).build();
		else
			return Response.status(Response.Status.NO_CONTENT).entity(null).build();
	}
	
	@GET
	@Path("listerIndisponibilite")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerIndisponibilite() {
		return Response.status(Response.Status.OK).entity(C_Indisponibilite.lister()).build();
	}
}
