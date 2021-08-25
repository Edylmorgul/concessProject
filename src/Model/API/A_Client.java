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

import Model.BEANS.C_Client;
import Model.BEANS.Global;

@Path("Client")
public class A_Client {

	@POST
	@Path("creerClient")
	@Produces(MediaType.APPLICATION_JSON)
	public Response creerClient(
			@FormParam("email") String email,
			@FormParam("mdp") String mdp,
			@FormParam("nom") String nom,
			@FormParam("prenom") String prenom,
			@FormParam("genre") String strGenre,
			@FormParam("telephone") String telephone,
			@FormParam("actif") String strActif){
		C_Client cli = new C_Client();
		int genre = Global.tryParseInt(strGenre);
		int actif = Global.tryParseInt(strActif);
		cli.setEmail(email);
		cli.setMdp(mdp);
		cli.setNom(nom);
		cli.setPrenom(prenom);
		cli.setGenre(genre);
		cli.setTelephone(telephone);
		cli.setActif(actif);
				
		if(cli.creer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(cli).build();				
	}	
	
	@PUT
	@Path("modifierClient")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifierClient(
			@FormParam("id") String strId,
			@FormParam("email") String email,
			@FormParam("mdp") String mdp,
			@FormParam("nom") String nom,
			@FormParam("prenom") String prenom,
			@FormParam("genre") String strGenre,
			@FormParam("telephone") String telephone,
			@FormParam("actif") String strActif) {
		C_Client cli = new C_Client();
		int id = Global.tryParseInt(strId);
		int genre = Global.tryParseInt(strGenre);
		int actif = Global.tryParseInt(strActif);
		cli.setId(id);
		cli.setEmail(email);
		cli.setMdp(mdp);
		cli.setNom(nom);
		cli.setPrenom(prenom);
		cli.setGenre(genre);
		cli.setTelephone(telephone);
		cli.setActif(actif);
		
		if(cli.modifier())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(null).build();
	}

	@DELETE
	@Path("supprimerClient")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerClient(@QueryParam("id") int id) {
		C_Client cli = new C_Client();
		cli.setId(id);
		
		if(cli.supprimer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null).build();
		
	}
	
	@GET
	@Path("trouverClient")
	@Produces(MediaType.APPLICATION_JSON)
	public Response trouverClient(@QueryParam("id") int id) {
		C_Client cli = new C_Client();
		cli = cli.trouver(id);
		
		if(cli != null)
			return Response.status(Response.Status.OK).entity(cli).build();
		else
			return Response.status(Response.Status.NO_CONTENT).entity(null).build();
	}
	
	@GET
	@Path("listerClient")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerClient() {
		return Response.status(Response.Status.OK).entity(C_Client.lister()).build();
	}
}
