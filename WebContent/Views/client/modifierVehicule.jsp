<%@ page import="Model.BEANS.C_Voiture" %>
<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
	<!-- Reception servlet voiture -->
	<% 
		C_Voiture v = (C_Voiture)session.getAttribute("voiture");
		//C_Voiture v = (C_Voiture)request.getAttribute("voiture");
	%>
	<header>
		<!-- Titre -->
        <h1>Modifier v�hicule</h1>
        <div class="lineTitre"></div>
	</header>	
	
	<!-- Formulaire modifier v�hicule -->	
	<div class="container">
		<p>Veuillez modifier les informations de votre v�hicule</p>
		<div class="row">
			<div class="card">
				<div class="card-header">Formulaire</div>
				<div class="card-body">
					<form class="form-horizontal" method="POST" role="form" action="${pageContext.request.contextPath}/ModifierVoiture">
						<div class="form-group row my-3">
							<!-- Marque -->
							<div class="col-sm-6">						   						
                                <label class="col-sm-4 control-label" for="marque">Marque :</label>  
                                <div class="col-md-8">
                                	<input type="text" class="form-control" name="marque" value=<%=v.getMarque()%> required maxlength="50">  
                                </div>                                 
							</div>
							
							<!-- Mod�le --> 
							<div class="col-sm-6">
								<label class="col-sm-4 control-label" for="modele">Mod�le :</label> 
								<div class="col-md-8">
									<input type="text" class="form-control" name="modele" value=<%=v.getModele()%> required maxlength="50">
								</div>                                               
							</div>
						</div>	                                                      	                                                     		                                                             		
 	                         
 	                    <div class ="form-group row my-3">
 	                    	<!-- Immatriculation -->
 	                    	<div class="col-sm-6">
 	                    		<label class="col-sm-4 control-label" for="numImm">Immatriculation :</label>
                                <div class="col-md-8">
                                	<input type="text" class="form-control" name="numImm" value=<%=v.getNumimm()%> required maxlength="10">
                                </div>   
 	                    	</div>
 	                    	
 	                    	<!-- Num�ro de chassis -->
 	                    	<div class="col-sm-6"> 	                    		
                                <label class="col-sm-4 control-label" for="numChass">Num�ro de chassis :</label> 
                                <div class="col-md-8">
                                	<input type="text" class="form-control" name="numChass" value=<%=v.getNumchas()%> required maxlength="17">
                                </div>                              		                               	                           
 	                    	</div>	                    	
 	                    </div>   	 	                                                                                                                                     		                                                              	                                                                                                                                                           		                               	                            

                        <div class="form-group row my-3">
                        	<!-- Couleur -->
                        	<div class="col-sm-6">
                        		<label class="col-sm-4 control-label" for="couleur">Couleur :</label> 
                                <div class="col-md-8">
                                	<input type="text" class="form-control" name="couleur" value=<%=v.getCouleur()%> required maxlength="50">
                                </div> 
                        	</div>
                        	
                        	<!-- Kilom�tre -->
                        	<div class="col-sm-6">
                        		<label class="col-sm-6 control-label" for="km">Kilom�tre :</label> 
                                <div class="col-md-4">
                                	<input type="number" class="form-control" name="km" value=<%=v.getKm()%> required maxlength="7">
                                </div>  
                        	</div>
                        </div>  
                        
                        <div class="form-group row my-3">
                        	<!-- Premi�re immatriculation -->
                        	<div class="col-sm-6">
                        		<label class="col-sm-8 control-label" for="primm">Premi�re immatriculation :</label> 
                                <div class="col-md-8">
                                	<input type="date" class="form-control" name="primm" value=<%=v.getPrimm()%> required>
                                </div> 
                        	</div>                        	                        	
                        </div>                                                    
                                                             
						<!-- Submit -->
                        <div class="form-group text-center my-3">
                            <button type="submit" id="submit" name="submit" class="btn btn-primary">Valider</button>
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