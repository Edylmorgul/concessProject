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

import Model.BEANS.C_Intervention;
import Model.BEANS.C_TypeIntervention;
import Model.BEANS.Global;

@Path("Intervention")
public class A_Intervention {

	@POST
	@Path("creerIntervention")
	@Produces(MediaType.APPLICATION_JSON)
	public Response creerIntervention(
			@FormParam("duree") String strDuree,
			@FormParam("ct") String strCt,
			@FormParam("typeIntervention") String strIdTypeIntervention){
		
		C_Intervention inter = new C_Intervention();
		C_TypeIntervention typeInt = new C_TypeIntervention();
		int duree = Global.tryParseInt(strDuree);
		Double ct = Global.tryParseDouble(strCt);
		int idTypeIntervention = Global.tryParseInt(strIdTypeIntervention);
		
		inter.setDuree(duree);
		inter.setCt(ct);
		inter.setTypeIntervention(typeInt.trouver(idTypeIntervention));
		
		if(inter.creer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(inter).build();				
	}	
	
	@PUT
	@Path("modifierIntervention")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifierIntervention(
			@FormParam("id") String strId,
			@FormParam("duree") String strDuree,
			@FormParam("ct") String strCt,
			@FormParam("typeIntervention") String strIdTypeIntervention){
		
		C_Intervention inter = new C_Intervention();
		C_TypeIntervention typeInt = new C_TypeIntervention();
		int id = Global.tryParseInt(strId);
		int duree = Global.tryParseInt(strDuree);
		Double ct = Global.tryParseDouble(strCt);
		int idTypeIntervention = Global.tryParseInt(strIdTypeIntervention);
		
		inter.setId(id);	
		inter.setDuree(duree);
		inter.setCt(ct);
		inter.setTypeIntervention(typeInt.trouver(idTypeIntervention));
		
		if(inter.modifier())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(null).build();
	}

	@DELETE
	@Path("supprimerIntervention")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerIntervention(@QueryParam("id") int id) {
		C_Intervention inter = new C_Intervention();
		inter.setId(id);
		
		if(inter.supprimer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null).build();		
	}
	
	@GET
	@Path("trouverIntervention")
	@Produces(MediaType.APPLICATION_JSON)
	public Response trouverIntervention(@QueryParam("id") int id) {
		C_Intervention inter = new C_Intervention();
		inter = inter.trouver(id);
		
		if(inter != null)
			return Response.status(Response.Status.OK).entity(inter).build();
		else
			return Response.status(Response.Status.NO_CONTENT).entity(null).build();
	}
	
	@GET
	@Path("listerIntervention")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerIntervention() {
		return Response.status(Response.Status.OK).entity(C_Intervention.lister()).build();
	}
}
