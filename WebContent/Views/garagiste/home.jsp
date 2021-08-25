<%@ page import="Model.BEANS.C_Garagiste" %>
<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
<%
    C_Garagiste gara = null;
    if (session.getAttribute("garagiste") != null)
        gara = (C_Garagiste) session.getAttribute("garagiste");
        
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
      				<li class="nav-item">
        				<a class="nav-link" href="${pageContext.request.contextPath}/ModifierUtilisateur"><i class="fas fa-address-card"></i> Modifier compte</a>
      				</li>
      				<li class="nav-item">
        				<a class="nav-link" href="${pageContext.request.contextPath}/ListeRdvGaragiste"><i class="fas fa-address-book"></i> Historique</a>
      				</li>
    			</ul>
  			</div>
  			
  			<div class="navbar-form navbar-right mx-5">
  				<button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Deconnexion'" class="btn btn-primary">Déconnexion</button>
  			</div>
		</nav>
		
		<!-- Titre -->
        <h1>Bievenue : <%=gara.getNom() %> <%=gara.getPrenom()%></h1>
        <div class="lineTitre"></div> 
	</header>
	
	<div class="text-center">
		<p>N'oubliez pas de regarder votre historique afin de voir vos rendez-vous en cours !</p>
	</div>

	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>