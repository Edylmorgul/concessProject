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

import Model.BEANS.C_Specialisation;

@Path("Specialisation")
public class A_Specialisation {

	@POST
	@Path("creerSpecialisation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response creerSpecialisation(
			@FormParam("nom") String nom) {
		C_Specialisation spe = new C_Specialisation();
		spe.setNom(nom);
				
		if(spe.creer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(spe).build();				
	}	
	
	@PUT
	@Path("modifierSpecialisation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifierSpecialisation(
			@FormParam("nom") String nom)  {
		C_Specialisation spe = new C_Specialisation();
		spe.setNom(nom);
		
		if(spe.modifier())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.BAD_REQUEST).entity(null).build();
	}

	@DELETE
	@Path("supprimerSpecialisation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerSpecialisation(@QueryParam("id") int id) {
		C_Specialisation spe = new C_Specialisation();
		spe.setId(id);
		
		if(spe.supprimer())
			return Response.status(Response.Status.OK).entity(true).build();
		else
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(null).build();
		
	}
	
	@GET
	@Path("trouverSpecialisation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response trouverSpecialisation(@QueryParam("id") int id) {
		C_Specialisation spe = new C_Specialisation();
		spe = spe.trouver(id);
		
		if(spe != null)
			return Response.status(Response.Status.OK).entity(spe).build();
		else
			return Response.status(Response.Status.NO_CONTENT).entity(null).build();
	}
	
	@GET
	@Path("listerSpecialisation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerSpecialisation() {
		return Response.status(Response.Status.OK).entity(C_Specialisation.lister()).build();
	}
}
