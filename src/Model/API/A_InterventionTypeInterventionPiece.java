package Model.API;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Model.BEANS.C_Intervention;
import Model.BEANS.C_InterventionTypeInterventionPiece;
import Model.BEANS.C_Piece;
import Model.BEANS.C_TypeIntervention;
import Model.BEANS.Global;

@Path("InterTypeInterPiece")
public class A_InterventionTypeInterventionPiece {
	
	@POST
	@Path("creerInterTypeInterPiece")
	@Produces(MediaType.APPLICATION_JSON)
	public Response creerIntervention(
			@FormParam("intervention") String strIdIntervention,
			@FormParam("typeIntervention") String strIdTypeIntervention,
			@FormParam("piece") String strIdPiece,
			@FormParam("nombre") String strNombre){
		
		C_InterventionTypeInterventionPiece InterTypeInterPiece = new C_InterventionTypeInterventionPiece();
		C_Intervention inter = new C_Intervention();
		C_TypeIntervention typeInt = new C_TypeIntervention();
		C_Piece piece = new C_Piece();
		int idIntervention = Global.tryParseInt(strIdIntervention);
		int idTypeIntervention = Global.tryParseInt(strIdTypeIntervention);
		int idPiece = Global.tryParseInt(strIdPiece);
		int nombre = Global.tryParseInt(strNombre);
		
		InterTypeInterPiece.setIntervention(inter.trouver(idIntervention));
		InterTypeInterPiece.setTypeIntervention(typeInt.trouver(idTypeIntervention));
		InterTypeInterPiece.setPiece(piece.trouver(idPiece));
		InterTypeInterPiece.setNombre(nombre);
		
		if(InterTypeInterPiece.creer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(inter).build();				
	}	
	
	@PUT
	@Path("modifierInterTypeInterPiece")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifierIntervention(
			@FormParam("intervention") String strIdIntervention,
			@FormParam("typeIntervention") String strIdTypeIntervention,
			@FormParam("piece") String strIdPiece,
			@FormParam("nombre") String strNombre){
		
		C_InterventionTypeInterventionPiece InterTypeInterPiece = new C_InterventionTypeInterventionPiece();
		C_Intervention inter = new C_Intervention();
		C_TypeIntervention typeInt = new C_TypeIntervention();
		C_Piece piece = new C_Piece();
		int idIntervention = Global.tryParseInt(strIdIntervention);
		int idTypeIntervention = Global.tryParseInt(strIdTypeIntervention);
		int idPiece = Global.tryParseInt(strIdPiece);
		int nombre = Global.tryParseInt(strNombre);
		
		InterTypeInterPiece.setIntervention(inter.trouver(idIntervention));
		InterTypeInterPiece.setTypeIntervention(typeInt.trouver(idTypeIntervention));
		InterTypeInterPiece.setPiece(piece.trouver(idPiece));
		InterTypeInterPiece.setNombre(nombre);
		
		if(inter.modifier())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(null).build();
	}

	/*@DELETE
	@Path("supprimerInterTypeInterPiece")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerIntervention(@QueryParam("idInter") int idInter, @QueryParam("idTypeInter") int idTypeInter, @QueryParam("idPiece") int idPiece) {
		C_InterventionTypeInterventionPiece InterTypeInterPiece = new C_InterventionTypeInterventionPiece();
		inter.setId(id);
		
		if(inter.supprimer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null).build();		
	}*/
	
	/*@GET
	@Path("trouverInterTypeInterPiece")
	@Produces(MediaType.APPLICATION_JSON)
	public Response trouverIntervention(@QueryParam("idInter") int idInter, @QueryParam("idTypeInter") int idTypeInter, @QueryParam("idPiece") int idPiece) {
		C_InterventionTypeInterventionPiece InterTypeInterPiece = new C_InterventionTypeInterventionPiece();
		InterTypeInterPiece = InterTypeInterPiece.trouver(idInter, idTypeInter, idPiece);
		
		if(InterTypeInterPiece != null)
			return Response.status(Response.Status.OK).entity(InterTypeInterPiece).build();
		else
			return Response.status(Response.Status.NO_CONTENT).entity(null).build();
	}*/
	
	@GET
	@Path("listerInterTypeInterPiece")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerIntervention() {
		return Response.status(Response.Status.OK).entity(C_InterventionTypeInterventionPiece.lister()).build();
	}
}