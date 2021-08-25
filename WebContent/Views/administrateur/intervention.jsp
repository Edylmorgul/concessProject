<%@ page import="java.util.List"%>
<%@ page import="Model.BEANS.C_TypeIntervention" %>
<%@ page import="Model.BEANS.C_Specialisation" %>
<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
	<header>
		<!-- Titre -->
        <h1>Type intervention</h1>
        <div class="lineTitre"></div>
	</header>
	
	<% 
		List<C_TypeIntervention> typesIntervention = (List)request.getAttribute("typesIntervention");
		List<C_Specialisation> specialisations = (List)request.getAttribute("specialisations");
	%>
		
	<!-- Formulaire type intervention -->
	<div class="container">
		<div class="row justify-content-center">
			<div class="card col-md-6">
				<table class="table">
					<thead> 
						<tr> 
							<th class="col-md-5" style="text-align:center !important">Type intervention</th>
							<th class="col-md-3"style="text-align:center !important">Spécialisation</th>
 							<th class="col-md-2"></th> <!-- Bouton Pièces-->
							<th class="col-md-2"></th> <!-- Bouton Supprimer-->
						</tr>
					</thead>
					<tbody>
						<% for(C_TypeIntervention t : typesIntervention){%>
		           			<tr>
		           				<td><%=t.getNom()%></td>
		           				<td style="text-align:center !important"><%=(t.getSpecialisation()).getNom()%></td>
		           				<td style="text-align:center !important"><div class="btn"><a href="${pageContext.request.contextPath}/CreerPiece?id=<%=t.getId()%>">Pièces</a></div></td>
		           				<td style="text-align:center !important"><div class="btn"><a href="${pageContext.request.contextPath}/SupprimerTypeIntervention?id=<%=t.getId()%>">Supprimer</a></div></td>
		           			</tr>
		           		<%}%>
           			</tbody>
           		</table>
           		
			</div>
			<div class="card col-md-6">
				
				<div class="card-header">Formulaire</div>
				<div class="card-body">
					<form class="form-horizontal" method="POST" role="form" action="${pageContext.request.contextPath}/CreerTypeIntervention">
						<!-- Nom type intervention --> 
						<label class="col-sm-9 mt-3 control-label" for="typeIntervention">Type intervention : <span class="requis"> *</span></label>
						<div class="col-md-8">
							<input type="text" class="form-control" name="typeIntervention" required maxlength="50">
						</div>                                
                        							                                 						
						<!-- Honoraire -->					   						
                        <label class="col-sm-4 control-label" for="honoraire">Honoraire : <span class="requis"> *</span></label>  
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="honoraire" required maxlength="10">  
                        </div>                                 
						
						<!-- Durée prévue -->						   						
                        <label class="col-sm-4 control-label" for="dureePrev">Durée prévue : <span class="requis"> *</span></label>  
                        <div class="col-md-8">
                           <input type="number" class="form-control" name="dureePrev" required maxlength="10">  
                        </div>  
                        
                        <!-- Choix de la spécialisation -->
                        <div class="col-md-12 my-3">
	                        <label class="col-sm-4 control-label" for="typeSpecialite">Spécialisation requise : <span class="requis"> *</span></label>
	                        <select name="typeSpecialite" id="typeSpecialite">
	                        	<% for(C_Specialisation s : specialisations){%>
                        			<option value="<%=s.getId()%>"><%=s.getNom()%></option>
                        		<%}%> 
	   						</select>
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
	
	<!-- Retour -->
	<div class="text-center my-3">
      <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Views/administrateur/home.jsp'" class="btn btn-primary">Retour</button>
    </div>
    
	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>