<%@ page import="Model.BEANS.C_Voiture" %>
<%@ page import="java.util.List"%> 
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="Model.BEANS.Global" %>
<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
	<!-- Liste voiture servlet -->
	<% 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM YYYY");
		List<C_Voiture> liste = (List)request.getAttribute("liste");
	%>
	
	<header>
		<!-- Titre -->
        <h1>Gestion voiture(s)</h1>
        <div class="lineTitre"></div>
	</header>
	
	<h2>Liste de vos voitures enregistrées</h2>
	<p>Vous pouvez ici gérer vos différents véhicules :</p>
	
	<!-- Test si liste vide -->
	<% if(liste == null || liste.isEmpty()) {%>
		<h2> Vous n'avez pas de voiture ! </h2>
	<%}
	
	else{%>
		<!-- Tableau des véhicules -->
		<div class="container">
			<table class="table">
				<thead> 
					<tr> 
						<th>Marque</th>
						<th>Modèle</th>
						<th>Couleur</th>
						<th>Numéro de chassis</th>
						<th>Immatriculation</th>
						<th>Pre.immatriculation</th>
						<th>Kilomètre</th>
						<th></th> <!-- Entretien/réparation -->
						<th></th> <!-- Modifier -->
						<th></th> <!-- Supprimer -->
					</tr>
				</thead>
				<tbody>
					<% for(C_Voiture v : liste){%>
						<tr> 		
                          <td style="text-align:center !important"><%=v.getMarque()%></td>
                          <td style="text-align:center !important"><%=v.getModele()%></td>
                          <td style="text-align:center !important"><%=v.getCouleur()%></td>
                          <td style="text-align:center !important"><%=v.getNumchas()%></td>
                          <td style="text-align:center !important"><%=v.getNumimm()%></td>
                          <td style="text-align:center !important"><%=formatter.format(Global.convertToLocalDateViaInstant(v.getPrimm()))%></td>
                          <td style="text-align:right !important"><%=v.getKm()%></td>
                          <td><div class="btn"><a href="${pageContext.request.contextPath}/CreerEntretien?id=<%=v.getId()%>">Réparation</a></div></td>
                          <td><div class="btn"><a href="${pageContext.request.contextPath}/ModifierVoiture?id=<%=v.getId()%>">Modifier</a></div></td>
                          <td><div class="btn"><a href="${pageContext.request.contextPath}/SupprimerVoiture?id=<%=v.getId()%>">Supprimer</a></div></td>            
						</tr>					
					<%}%> 
				</tbody>
			</table>
		</div>	
	<%}%>
	
	<div class="ligneSeparation">
    </div>
	
	<!-- Ajouter un véhicule -->
	<h2>Ajouter un véhicule</h2>
	<p>Si voulez-vous ajouter un nouveau véhicule, veuillez cliquer sur le bouton</p>
	
	<div class="text-center">
      <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/CreerVoiture'" class="btn btn-primary">Ajouter véhicule</button>
    </div>
    
    <!-- Retour -->
	<div class="text-center my-3">
      <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Views/client/home.jsp'" class="btn btn-primary">Retour</button>
    </div>

	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>