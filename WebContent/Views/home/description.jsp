<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
	<header>
		<!-- Titre -->
        <h1>Description</h1>
        <div class="lineTitre"></div>
	</header>
	
	<!-- Présentation -->
	<section class="presentation">
		<p> Ce site web a été réalisé par Pierard Loic et Casani Julien afin de pouvoir valider leur cursus scolaire. </br>
			Eleve de 3e année, bachelier en informatique de gestion à la haute-école Condorcet. 
		 </p>
	</section>
	
	<!-- Retour -->
	<div class="text-center my-3">
      <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Views/home/index.jsp'" class="btn btn-primary">Retour</button>
    </div>
	
	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>