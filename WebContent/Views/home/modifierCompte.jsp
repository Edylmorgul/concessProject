<%@ page import="Model.BEANS.C_Client" %>
<%@ page import="Model.BEANS.C_Garagiste" %>
<%@ page import="Model.BEANS.C_Utilisateur" %>
<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
	<!-- Reception session utilisateur courante -->
	<% 
		C_Utilisateur uti = new C_Utilisateur();
		if(session.getAttribute("client") != null)
			uti = (C_Client)session.getAttribute("client");
			
		else
			uti = (C_Garagiste)session.getAttribute("garagiste");
					
		// Afin de supprimer un problème d'affichage à cause des espaces dans une chaine
		String tel = uti.getTelephone().replaceAll("\\s+","");
	%>
	<header>
		<!-- Titre -->
        <h1>Modifier compte</h1>
        <div class="lineTitre"></div>
	</header>
	
	<!-- Formulaire modification -->	
	<div class="container">
		<div class="row justify-content-center">
			<div class="card col-md-6">
				<div class="card-header">Modifier vos informations</div>
				<div class="card-body">
					<form class="form-horizontal" method="POST" role="form" action="${pageContext.request.contextPath}/ModifierUtilisateur">
						<!-- Nom -->									   						
                        <label class="col-sm-4 control-label" for="nom">Nom :</label>  
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="nom" value=<%=uti.getNom()%> required maxlength="50">  
                        </div>                                 
													
						<!-- Prenom --> 
						<label class="col-sm-4 control-label" for="prenom">Prénom :</label> 
						<div class="col-md-8">
							<input type="text" class="form-control" name="prenom" value=<%=uti.getPrenom()%> required maxlength="50">
						</div>                                               	                                                      	                                                     		                                                             		
 	                         
 	                    <!-- Telephone -->
 	                    <label class="col-sm-8 control-label" for="telephone">Téléphone :</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="telephone" value=<%=tel%> required maxlength="15">
                        </div>                        	                    	                    	
 	                	 	                                                                                                                                     		                                                              	                                                                                                                                                           		                               	                                              
                        <!-- Mot de passe -->
                        <label class="col-sm-8 control-label" for="mdp">Nouveau mot de passe :</label> 
                        <div class="col-md-10">
                            <input type="password" class="form-control" name="mdp" required maxlength="50">
                        </div>   
                        
                        <!-- Confirmer mot de passe -->
                        <label class="col-sm-8 control-label" for="mdp">Confirmer nouveau mot de passe :</label> 
                        <div class="col-md-10">
                            <input type="password" class="form-control" name="mdpConfirm" required maxlength="50">
                        </div>                 
                                                                                                                                              
						<!-- Submit -->
                        <div class="form-group text-center my-2">
                            <button type="submit" id="submit" name="submit" class="btn btn-primary">Valider</button>
                        </div>
					</form>	
					<div class="erreur">${erreur}</div>
				</div>
			</div>
		</div>
	</div>  
	
	<!-- Retour -->
	<% 
		if(session.getAttribute("client") != null){%>
			<div class="text-center my-3">
		      <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Views/client/home.jsp'" class="btn btn-primary">Retour</button>
		    </div>
		<%}

		else{%>
			<div class="text-center my-3">
		      <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Views/garagiste/home.jsp'" class="btn btn-primary">Retour</button>
		    </div>
		<%}				
	%> 

	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>