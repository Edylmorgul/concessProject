<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
	<header>
		<!-- Titre -->
        <h1>Enregistrement</h1>
        <div class="lineTitre"></div>
	</header>
	
	<!-- Formulaire enregistrement -->	
	<div class="container">
		<p>ATTENTION, si vous êtes un employé de l'entreprise, veuillez indiquer votre clé pour valider votre compte "garagiste" </p>
		<div class="row">
			<div class="card">
				<div class="card-header">Formulaire</div>
				<div class="card-body">
					<form class="form-horizontal" method="POST" role="form" action="${pageContext.request.contextPath}/CreerUtilisateur">
						<div class="form-group row my-3">
							<!-- Nom -->
							<div class="col-sm-6">						   						
                                <label class="col-sm-4 control-label" for="nom">Nom : <span class="requis"> *</span></label>  
                                <div class="col-md-8">
                                	<input type="text" class="form-control" name="nom" required maxlength="50">  
                                </div>                                 
							</div>
							
							<!-- Prenom --> 
							<div class="col-sm-6">
								<label class="col-sm-4 control-label" for="prenom">Prénom : <span class="requis"> *</span></label> 
								<div class="col-md-8">
									<input type="text" class="form-control" name="prenom" required maxlength="50">
								</div>                                               
							</div>
						</div>
						
						<div class="form-group row my-3">
							<!-- Genre --> 
							<div class="col-sm-6">
								<label class="col-sm-6 control-label" for="typeGenre">Genre : <span class="requis"> *</span></label>                        
                                <select name="typeGenre" id="typeGenre">
                					<option value=1>Homme</option>
                					<option value=2>Femme</option>
                					<option value=3>Autre</option>
            					</select>                             
							</div>
						</div>		                                                      	                                                     		                                                             		
 	                         
 	                    <div class ="form-group row my-3">
 	                    	<!-- Telephone -->
 	                    	<div class="col-sm-6">
 	                    		<label class="col-sm-4 control-label" for="telephone">Téléphone : <span class="requis"> *</span></label>
                                <div class="col-md-8">
                                	<input type="text" class="form-control" name="telephone" required maxlength="15">
                                </div>   
 	                    	</div>
 	                    	
 	                    	<!-- Email -->
 	                    	<div class="col-sm-6"> 	                    		
                                <label class="col-sm-4 control-label" for="email">Email : <span class="requis"> *</span></label> 
                                <div class="col-md-10">
                                	<input type="email" class="form-control" name="email" placeholder ="Ex: Pendu@outlook.com" required maxlength="50">
                                </div>                              		                               	                           
 	                    	</div>	                    	
 	                    </div>   	 	                                                                                                                                     		                                                              	                                                                                                                                                           		                               	                            

                        <div class="form-group row my-3">
                        	<!-- Mot de passe -->
                        	<div class="col-sm-6">
                        		<label class="col-sm-4 control-label" for="mdp">Mot de passe : <span class="requis"> *</span></label> 
                                <div class="col-md-8">
                                	<input type="password" class="form-control" name="mdp" placeholder = "Ex: MAJ + CHIFFRE" required maxlength="50">
                                </div> 
                        	</div>
                        	
                        	<!-- Confirmer mot de passe -->
                        	<div class="col-sm-6">
                        		<label class="col-sm-6 control-label" for="mdpConfirm">Confirmer mot de passe : <span class="requis"> *</span></label> 
                                <div class="col-md-8">
                                	<input type="password" class="form-control" name="mdpConfirm" required maxlength="50">
                                </div>  
                        	</div>
                        </div>                                                      
                                                    
                        <div class="form-group row my-5">
                        	<!-- Type utilisateur -->
                        	<div class="col-sm-6">
                        		<label class="col-sm-6 control-label" for="typeUtilisateur">Type de votre compte : <span class="requis"> *</span></label>                                
                                <select name="typeUtilisateur" id="typeUtilisateur">
                					<option value=1>Client</option>
                					<option value=2>Garagiste</option>
            					</select>                               
                        	</div>
                        </div>
						<!-- Submit -->
                        <div class="form-group text-center my-3">
                            <button type="submit" id="submit" name="submit" class="btn btn-primary">S'enregister</button>
                        </div>
					</form>	
					<div class="erreur">${erreur}</div>					 		
				</div>
			</div>
		</div>
	</div> 
	
	<!-- Retour -->
	<div class="text-center my-3">
      <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Views/home/index.jsp'" class="btn btn-primary">Retour</button>
    </div>             
	
	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>