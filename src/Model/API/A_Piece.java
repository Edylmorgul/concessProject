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

import Model.BEANS.C_Piece;
import Model.BEANS.Global;

@Path("Piece")
public class A_Piece {

	@POST
	@Path("creerPiece")
	@Produces(MediaType.APPLICATION_JSON)
	public Response creerPiece(
			@FormParam("nom") String nom,
			@FormParam("prix") String strPrix) {
		
		C_Piece piece = new C_Piece();
		Double prix = Global.tryParseDouble(strPrix);
		
		piece.setNom(nom);
		piece.setPrix(prix);
				
		if(piece.creer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(piece).build();				
	}	
	
	@PUT
	@Path("modifierPiece")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifierPiece(
			@FormParam("nom") String strId,
			@FormParam("nom") String nom,
			@FormParam("prix") String strPrix) {
		
		C_Piece piece = new C_Piece();
		int id = Global.tryParseInt(strId);
		Double prix = Global.tryParseDouble(strPrix);
		
		piece.setId(id);
		piece.setNom(nom);
		piece.setPrix(prix);
		
		if(piece.modifier())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(null).build();
	}

	@DELETE
	@Path("supprimerPiece")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerPiece(@QueryParam("id") int id) {
		C_Piece piece = new C_Piece();
		piece.setId(id);
		
		if(piece.supprimer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null).build();
		
	}
	
	@GET
	@Path("trouverPiece")
	@Produces(MediaType.APPLICATION_JSON)
	public Response trouverPiece(@QueryParam("id") int id) {
		C_Piece piece = new C_Piece();
		piece = piece.trouver(id);
		
		if(piece != null)
			return Response.status(Response.Status.OK).entity(piece).build();
		else
			return Response.status(Response.Status.NO_CONTENT).entity(null).build();
	}
	
	@GET
	@Path("listerPiece")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerPiece() {
		return Response.status(Response.Status.OK).entity(C_Piece.lister()).build();
	}
}
