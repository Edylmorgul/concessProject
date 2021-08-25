<%@ page import="java.util.List"%> 
<%@ page import="Model.BEANS.C_Specialisation" %>
<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
	<!-- Liste specialisation servlet -->
	<% 
		List<C_Specialisation> liste = (List)request.getAttribute("liste");
	%>

	<header>
		<!-- Titre -->
        <h1>Gestion spécialisation</h1>
        <div class="lineTitre"></div>
	</header>
	
	<!-- Formulaire specialisation -->
	<div class="container">
		<p>Veuillez définir la/les spécialitée(s) du garagiste :</p>
		<div class="row justify-content-center">
			<div class="card col-md-6">
				<div class="card-header">Formulaire</div>
				<div class="card-body">
					<form class="form-horizontal" method="POST" role="form" action="${pageContext.request.contextPath}/AjouterSpecialisation">
						<!-- Test si liste vide -->
						<% if(liste == null || liste.isEmpty()) {%>
							<h2> Aucune spécialisation présentes ! </h2>
						<%} 
						else{%>
							<!-- Choix specialisation -->
							<label class="col-sm-9 control-label" for="typeSpe">Spécialitée :</label>                                
                        	<select name="typeSpe" id="typeSpe">
                        		<% for(C_Specialisation spe : liste){%>
                        			<option value=<%=spe.getId()%>><%=spe.getNom()%></option>
                        		<%}%>   
            				</select>
            				
            				<!-- Submit -->
                        	<div class="form-group text-center my-3">
                            	<button type="submit" id="submit" name="submit" class="btn btn-primary">Valider</button>
                        	</div>
            			<%}%>
					</form>
					<div class="erreur">${erreur}</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Retour -->
	<div class="text-center my-3">
      <button type="button" onclick="window.location.href='/ProjectConcessApi/Views/administrateur/home.jsp'" class="btn btn-primary">Retour</button>
    </div>

	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>