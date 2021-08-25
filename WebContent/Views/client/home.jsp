<%@ page import="Model.BEANS.C_Client" %>
<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
<%
    C_Client client = null;
    if (session.getAttribute("client") != null)
        client = (C_Client) session.getAttribute("client");
        
    else 
        response.sendRedirect("Views/home/index.jsp");
%>
	<header>	
		<!-- Navbar -->
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
  			<a class="navbar-brand" href="#">CondorAuto</a>
  			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    			<span class="navbar-toggler-icon"></span>
  			</button>
  			
  			<!-- Option navbar -->
  			<div class="collapse navbar-collapse" id="navbarNav">
    			<ul class="navbar-nav">			     			
      				<li class="nav-item active">
        				<a class="nav-link" href="${pageContext.request.contextPath}/ListeVoiture"><i class="fas fa-car"></i> Vos voitures</a>
      				</li>
      				<li class="nav-item">
        				<a class="nav-link" href="${pageContext.request.contextPath}/ModifierUtilisateur"><i class="fas fa-address-card"></i> Modifier compte</a>
      				</li>
      				<li class="nav-item">
        				<a class="nav-link" href="${pageContext.request.contextPath}/ListeRdvClient"><i class="fas fa-address-book"></i> Historique</a>
      				</li>
    			</ul>
  			</div>
  			
  			<div class="navbar-form navbar-right mx-5">
  				<button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Deconnexion'" class="btn btn-primary">Déconnexion</button>
  			</div>
		</nav>
		
		<!-- Titre -->
        <h1>Bievenue : <%=client.getNom() %> <%=client.getPrenom()%></h1>
        <div class="lineTitre"></div> 
	</header>
	
	<!-- Rendez-vous -->
	<h2>Prendre rendez-vous :</h2>
	<div class="text-center">
		<p>Un problème avec votre véhicule ? Prenez un rendez-vous avec nos meilleurs garagistes !</p>
	</div>
	
	<!-- Description -->
	<h2>L'entreprise :</h2>
	<div class="text-center">
		<p> 
			CondorAuto est une entreprise à la base familial, ayant évolué grandement depuis quelques années, </br>
			les besoins et le nombre de personnels augmentant au fur des années, le besoin d'un site internet se faisait ressentir. </br>
			C'est donc avec plaisir que nous vous accueillons au seins de celui-ci.
		</p>
	</div>
	
	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>