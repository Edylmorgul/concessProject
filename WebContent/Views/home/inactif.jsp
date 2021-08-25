<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
	<header>
		<!-- Titre -->
        <h1>Erreur</h1>
        <div class="lineTitre"></div>
	</header>

	<p> Votre compte a été desactivé, veuillez contacter l'administrateur ! </p>
	
	<!-- Retour -->
	<div class="text-center my-3">
      <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Views/home/index.jsp'" class="btn btn-primary">Retour</button>
    </div> 
    
	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>