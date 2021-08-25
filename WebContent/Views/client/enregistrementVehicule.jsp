<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
	<header>
		<!-- Titre -->
        <h1>V�hicule</h1>
        <div class="lineTitre"></div>
	</header>
	
	<!-- Formulaire enregistrement v�hicule -->	
	<div class="container">
		<p>Veuillez remplir le formulaire ci-dessous pour ajouter un v�hicule :</p>
		<div class="row">
			<div class="card">
				<div class="card-header">Formulaire</div>
				<div class="card-body">
					<form class="form-horizontal" method="POST" role="form" action="${pageContext.request.contextPath}/CreerVoiture">
						<div class="form-group row my-3">
							<!-- Marque -->
							<div class="col-sm-6">						   						
                                <label class="col-sm-4 control-label" for="marque">Marque : <span class="requis"> *</span></label>  
                                <div class="col-md-8">
                                	<input type="text" class="form-control" name="marque" required maxlength="50">  
                                </div>                                 
							</div>
							
							<!-- Mod�le --> 
							<div class="col-sm-6">
								<label class="col-sm-4 control-label" for="modele">Mod�le : <span class="requis"> *</span></label> 
								<div class="col-md-8">
									<input type="text" class="form-control" name="modele" required maxlength="50">
								</div>                                               
							</div>
						</div>	                                                      	                                                     		                                                             		
 	                         
 	                    <div class ="form-group row my-3">
 	                    	<!-- Immatriculation -->
 	                    	<div class="col-sm-6">
 	                    		<label class="col-sm-4 control-label" for="numImm">Immatriculation : <span class="requis"> *</span></label>
                                <div class="col-md-8">
                                	<input type="text" class="form-control" name="numImm" placeholder ="Ex : AA-229-AA" required maxlength="10">
                                </div>   
 	                    	</div>
 	                    	
 	                    	<!-- Num�ro de chassis -->
 	                    	<div class="col-sm-6"> 	                    		
                                <label class="col-sm-4 control-label" for="numChass">Num�ro de chassis : <span class="requis"> *</span></label> 
                                <div class="col-md-8">
                                	<input type="text" class="form-control" name="numChass" placeholder ="Ex : 1FTLP62w4XH128703" required maxlength="17">
                                </div>                              		                               	                           
 	                    	</div>	                    	
 	                    </div>   	 	                                                                                                                                     		                                                              	                                                                                                                                                           		                               	                            

                        <div class="form-group row my-3">
                        	<!-- Couleur -->
                        	<div class="col-sm-6">
                        		<label class="col-sm-4 control-label" for="couleur">Couleur : <span class="requis"> *</span></label> 
                                <div class="col-md-8">
                                	<input type="text" class="form-control" name="couleur" required maxlength="50">
                                </div> 
                        	</div>
                        	
                        	<!-- Kilom�tre -->
                        	<div class="col-sm-6">
                        		<label class="col-sm-6 control-label" for="km">Kilom�tre : <span class="requis"> *</span></label> 
                                <div class="col-md-4">
                                	<input type="number" class="form-control" name="km" required maxlength="7">
                                </div>  
                        	</div>
                        </div>  
                        
                        <div class="form-group row my-3">
                        	<!-- Premi�re immatriculation -->
                        	<div class="col-sm-6">
                        		<label class="col-sm-8 control-label" for="primm">Premi�re immatriculation : <span class="requis"> *</span></label> 
                                <div class="col-md-8">
                                	<input type="date" class="form-control" name="primm" placeholder="12/01/1995" required>
                                </div> 
                        	</div>                        	                        	
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
	
	<!-- Retour -->
	<div class="text-center my-3">
      <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Views/client/home.jsp'" class="btn btn-primary">Retour</button>
    </div> 

	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>