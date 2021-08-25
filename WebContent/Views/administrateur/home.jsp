<%@ page import="Model.BEANS.C_Administrateur" %>
<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
<%
    C_Administrateur admin = null;
    if (session.getAttribute("administrateur") != null)
        admin = (C_Administrateur) session.getAttribute("administrateur");
        
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
        				<a class="nav-link" href="${pageContext.request.contextPath}/ListeUtilisateur"><i class="fas fa-users"></i>Utilisateur</a>
      				</li>
      				<li class="nav-item">
        				<a class="nav-link" href="${pageContext.request.contextPath}/CreerTypeIntervention"><i class="fas fa-key"></i>Intervention</a>
      				</li>
    			</ul>
  			</div>
  			
  			<div class="navbar-form navbar-right mx-5">
  				<button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Deconnexion'" class="btn btn-primary">Déconnexion</button>
  			</div>
		</nav>
		
		<!-- Titre -->
        <h1>Administration</h1>
        <div class="lineTitre"></div> 
	</header>
	
	<div class="text-center">
		<p>Attention, toutes modifications doivent d'abord être validées par les instances supérieurs</p>
	</div>
	
	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>