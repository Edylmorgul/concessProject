<%@ page import="Model.BEANS.C_Piece" %>
<%@ page import="Model.BEANS.C_TypeIntervention" %>
<%@ page import="java.util.List"%> 
  
<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
	<!-- Liste piece servlet -->
<% 
	String euro = "&#x20AC;";		
	C_TypeIntervention typeInter = (C_TypeIntervention)request.getAttribute("typeInter");
	List<C_Piece> piecesAvec = (List<C_Piece>)request.getAttribute("piecesAvec");
	List<C_Piece> piecesSans = (List<C_Piece>)request.getAttribute("piecesSans");
%>

<header>
	<!-- Titre -->
       <h1>Ajouter pièce</h1>
       <div class="lineTitre"></div>
</header>	

<!-- Formulaire piéce -->	
<div class="container">
	<p>Veuillez ajouter les pièces pour le type de l'intervention ou choisir parmi les pièces déjà présentes</p>

	<div class="row">
		<div class="card my-3">
			<div class="card-header">Pièces rattachées à ce type d'interventions</div>
			<div class="card-body">
				<% if(piecesAvec == null || piecesAvec.isEmpty()) {%>
					<h2> Ce type d'interventions ne contient aucune pièce.</h2>
				<%} 
				else{%>
					<!-- Choix type intervention -->
          				<table id="tabAvec" class="table">
          					<thead>
           					<tr>
                   				<th class="col-sm-6">Référence</th>
                   				<th class="col-sm-2">Prix</th>
                   				<th class="col-sm-2"></th><!-- Délier -->
                   				<th class="col-sm-2"></th><!-- Supprimer -->
                   			</tr>
                  			</thead>
						<tbody>	
          					<% for(C_Piece p : piecesAvec){%>
                      			<tr>
                      				<td><%=p.getNom()%></td>
                      				<td style="text-align:right !important"><%=String.format("%.2f",p.getPrix())+euro%></td>
                      				<td style="text-align:center !important"><div class="btn"><a href="${pageContext.request.contextPath}/SupprimerTypeInterventionPiece?idt=<%=typeInter.getId()%>&idp=<%=p.getId()%>">Délier</a></div></td>
                      				<td style="text-align:center !important"><div class="btn"><a href="${pageContext.request.contextPath}/SupprimerPiece?id=<%=p.getId()%>&idt=<%=typeInter.getId()%>">Supprimer</a></div></td>
                      			</tr>
                      		<%}%>
						</tbody>
          				</table>
         				<%}%>
          	 	</div>
      	 	</div>
      	 	<div class="card my-3">
          	 	<div class="card-header">Pièces non - rattachées à ce type d'interventions</div>
			<div class="card-body">
				<% if(piecesAvec == null || piecesAvec.isEmpty()) {%>
					<h2> Ce type d'interventions contient toutes les pièces.</h2>
				<%} 
				else{%>
					<!-- Choix type intervention -->
          				<table id="tabSans" class="table">
          					<thead>
           					<tr>
                   				<th class="col-sm-6">Référence</th>
                   				<th class="col-sm-2">Prix</th>
                   				<th class="col-sm-2"></th><!-- Lier -->
                   				<th class="col-sm-2"></th><!-- Supprimer -->
                   			</tr>
                  			</thead>
						<tbody>	
          					<% for(C_Piece p : piecesSans){%>
                      			<tr>
                      				<td><%=p.getNom()%></td>
                      				<td style="text-align:right !important"><%=String.format("%.2f",p.getPrix())+euro%></td>
                      				<td style="text-align:center !important"><div class="btn"><a href="${pageContext.request.contextPath}/AjouterTypeInterventionPiece?idt=<%=typeInter.getId()%>&idp=<%=p.getId()%>">Lier</a></div></td>
                      				<td style="text-align:center !important"><div class="btn"><a href="${pageContext.request.contextPath}/SupprimerPiece?id=<%=p.getId()%>&idt=<%=typeInter.getId()%>">Supprimer</a></div></td>
                      			</tr>
                      		<%}%>
						</tbody>
          				</table>
         				<%}%>
          	 	</div> 
      	 	</div> 
      	 	<div class="card my-3">
			<div class="card-header">Formulaire</div>
			<div class="card-body">
				<form class="form-horizontal" method="POST" role="form" id="formAjouterPiece" action="${pageContext.request.contextPath}/CreerPiece?id=<%=typeInter.getId()%>">
					<div class="form-group row my-3">
						<!-- Nom -->
						<div class="col-sm-6">						   						
                               <label class="col-sm-4 control-label" for="nom">Référence :</label>  
                               <div class="col-md-8">
                               	<input type="text" class="form-control" name="nom" required maxlength="50">  
                               </div>                                 
						</div>
						
						<!-- Prix --> 
						<div class="col-sm-6">
							<label class="col-sm-4 control-label" for="prix">Prix :</label> 
							<div class="col-md-85">
								<input type="text" class="form-control" name="prix" required maxlength="10">
							</div>                                               
						</div>
					</div>	 
					                    
					<!-- Submit -->
                       <div class="form-group text-center my-3">
                           <button type="submit" id="submit" name="submit" class="btn btn-primary">Ajouter</button>
                       </div>
				</form>	
				<div class="erreur">${erreur}</div>		
			</div>
		</div>
	</div>
</div>

<!-- Valider -->
<div class="text-center my-3">
     	<button type="button" id="btnRetour" onclick="window.location.href='Views/administrateur/home.jsp'" class="btn btn-primary">Retour</button>
   </div>
   
<!-- Footer - informations -->
<%@include file="../layout/footer.jsp" %>