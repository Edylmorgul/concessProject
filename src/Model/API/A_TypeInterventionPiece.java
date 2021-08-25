package Model.API;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Model.BEANS.C_Piece;
import Model.BEANS.C_TypeIntervention;
import Model.BEANS.C_TypeInterventionPiece;
import Model.BEANS.Global;

@Path("TypeInterPiece")
public class A_TypeInterventionPiece {

	@POST
	@Path("creerTypeInterPiece")
	@Produces(MediaType.APPLICATION_JSON)
	public Response creerIntervention(
			@FormParam("typeIntervention") String strIdTypeIntervention,
			@FormParam("piece") String strIdPiece){
		
		C_TypeInterventionPiece typeInterPiece = new C_TypeInterventionPiece();
		C_TypeIntervention typeInt = new C_TypeIntervention();
		C_Piece piece = new C_Piece();
		int idTypeIntervention = Global.tryParseInt(strIdTypeIntervention);
		int idPiece = Global.tryParseInt(strIdPiece);
		
		typeInterPiece.setTypeIntervention(typeInt.trouver(idTypeIntervention));
		typeInterPiece.setPiece(piece.trouver(idPiece));
		
		if(typeInterPiece.creer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(typeInterPiece).build();				
	}	
	
	@PUT
	@Path("modifierTypeInterPiece")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifierIntervention(
			@FormParam("typeIntervention") String strIdTypeIntervention,
			@FormParam("piece") String strIdPiece){
		
		C_TypeInterventionPiece typeInterPiece = new C_TypeInterventionPiece();
		C_TypeIntervention typeInt = new C_TypeIntervention();
		C_Piece piece = new C_Piece();
		int idTypeIntervention = Global.tryParseInt(strIdTypeIntervention);
		int idPiece = Global.tryParseInt(strIdPiece);
		
		typeInterPiece.setTypeIntervention(typeInt.trouver(idTypeIntervention));
		typeInterPiece.setPiece(piece.trouver(idPiece));
		
		if(typeInterPiece.modifier())
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
	@Path("listerTypeInterPiece")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerIntervention() {
		return Response.status(Response.Status.OK).entity(C_TypeInterventionPiece.lister()).build();
	}
}