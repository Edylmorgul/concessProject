<%@ page import="Model.BEANS.C_Indisponibilite" %>
<%@ page import="java.util.List"%> 
<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>	
	<!-- Liste voiture servlet -->
	<% 
		List<C_Indisponibilite> liste = (List)request.getAttribute("liste");
	%>
	<header>
		<!-- Titre -->
        <h1>Absence</h1>
        <div class="lineTitre"></div>
	</header>
	
	<!-- Formulaire type intervention -->
	<div class="container">
		<p> Veuillez définir la durée de l'absence <p>
		<div class="row justify-content-center">
			<div class="card col-md-6">
				<div class="card-header">Formulaire</div>
				<div class="card-body">
					<form class="form-horizontal" method="POST" role="form" action="${pageContext.request.contextPath}/CreerIndisponibilite">
						<!-- Date de début --> 
						<label class="col-sm-9 control-label" for="typeIntervention">Date de début : <span class="requis"> *</span></label>
						<div class="col-md-8">
							<input type="date" class="form-control" name="debut" placeholder="12/01/1995" required>
						</div>                                
                        							                                 						
						<!-- Date de fin -->					   						
                        <label class="col-sm-4 control-label" for="honoraire">Date de fin : <span class="requis"> *</span></label>  
                        <div class="col-md-8">
                            <input type="date" class="form-control" name="fin" placeholder="12/01/1995" required>  
                        </div>                                                      
                        
                        <!-- Submit -->
                        <div class="form-group text-center my-3">
                            <button type="submit" id="submit" name="submit" class="btn btn-primary">Enregister</button>
                        </div>                               
					</form>
					<div class="erreur">${erreur}</div>
				</div>
			</div>
		</div>		
	</div>
	
	<div class="ligneSeparation">
    </div>
	
	<!-- Absence du gragiste -->
	<h1>Ses absences</h1>
	<!-- Test si liste vide -->
	<% if(liste == null || liste.isEmpty()) {%>
		<h2> Aucune absence ! </h2>
	<%}
	
	else{%>
		<!-- Tableau des véhicules -->
		<div class="container">
			<table class="table">
				<thead> 
					<tr> 
						<th>Debut</th>
						<th>Fin</th>
						<th></th> <!-- Supprimer -->
					</tr>
				</thead>
				<tbody>
					<% for(C_Indisponibilite i : liste){%>
						<tr> 
							<td><%=i.getDebut()%></td>
	 						<td><%=i.getFin()%></td>
	         				<td><div class="btn"><a href="${pageContext.request.contextPath}/SupprimerIndisponibilite?id=<%=i.getId()%>">Supprimer</a></div></td>
						</tr>					
					<%}%> 
				</tbody>
			</table>
		</div>	
	<%}%>
	
	<!-- Retour -->
	<div class="text-center my-3">
      <button type="button" onclick="window.location.href='/ProjectConcessApi/Views/administrateur/home.jsp'" class="btn btn-primary">Retour</button>
    </div>

	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>