<%@ page import="Model.BEANS.C_Piece" %>
<%@ page import="Model.BEANS.C_Rdv" %>
<%@ page import="Model.BEANS.C_TypeIntervention" %>
<%@ page import="java.util.List"%> 

<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>

	<!-- Liste piece servlet -->
	<% 	
		String euro = "&#x20AC;";
		C_Rdv rdv = (C_Rdv)session.getAttribute("rdv");
		C_TypeIntervention typeInter = rdv.getIntervention().getTypeIntervention();
		typeInter.listePieceParIntervention();
		List<C_Piece> liste = typeInter.getListPiece();
	%>
	
	<header>
		<!-- Titre -->
        <h1>Solde</h1>
        <div class="lineTitre"></div>
	</header>
	
	<!-- Formulaire piéce -->	
	<div class="container">
		<p>Veuillez indiquer la durée de l'opération ainsi que choisir les pièces et leurs nombres utilisées afin d'en calculer le prix :</p>	
		<form class="form-horizontal" method="POST" role="form" id="formPiece" action="${pageContext.request.contextPath}/CalculerSolde">	
			<!-- Définir durée réelle de l'intervention -->	
			<div class="form-group row my-3">
				<div class="col-sm-6">						   						
                    <label class="col-sm-4 control-label" for="marque">Durée de l'intervention : <span class="requis"> *</span></label>  
                    <div class="col-md-8">
                        <input type="number" class="form-control" name="duree" required maxlength="10">  
                    </div>                                 
				</div>
			</div>	
												
			<!-- Tableau des pièces -->
			<table id="tableSolde" class="table">
				<thead> 
					<tr> 
						<th>Nom</th>
						<th>Quantité</th>
					</tr>
				</thead>
				<tbody>
					<% for(C_Piece p : liste){%>				
						<tr> 
						  <td><input type="hidden" name="idPiece" value=<%=p.getId()%>></td>		
                          <td style="text-align:center !important"><%=p.getNom()%></td>              
                          <td><input type="number" class="form-control" value=0 name="qt" oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/[^0-9,]/g, '');this.value = this.value.replace(/(\..*)\./g, '$1');" min=0 required maxlength="3"></td>
						</tr>					
					<%}%> 
				</tbody>
			</table>	
			
			<!-- Submit -->
            <div class="form-group text-center my-3">
               	<button type="submit" id="submit" name="submit" class="btn btn-primary">Valider</button>
            </div> 					                                                   	                                                     		                                                             		 	                                                                                                                                     
		</form>
		<div class="erreur">${erreur}</div>		
	</div>
	
	<!-- Retour -->
	<div class="text-center my-3">
      <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Views/garagiste/home.jsp'" class="btn btn-primary">Retour</button>
    </div>
    
    <script type="text/javascript">
    	
    </script>
    
	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>