<%@ page import="Model.BEANS.C_Rdv" %>
<%@ page import="Model.BEANS.C_Garagiste" %>
<%@ page import="java.util.List"%> 
<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
	<!-- Liste rdv servlet -->
	<% 
		C_Garagiste gara = (C_Garagiste) session.getAttribute("garagiste");
		List<C_Rdv> liste = (List)request.getAttribute("liste");
	%>
	
	<header>
		<!-- Titre -->
        <h1>Historique</h1>
        <div class="lineTitre"></div>
	</header>
	
	<!-- Tableaux des rendez-vous -->
	<!-- Test si liste vide -->
	<% if(liste == null || liste.isEmpty()) {%>
		<h2> Votre historique est vide ! </h2>
	<%}
	
	else{%>
		<!-- Tableau des véhicules -->
		<div class="container">
			<p>Vous pouvez voir le détail de vos interventions ou calculer le solde de celles effectuées.</p>
			<table class="table">
				<thead> 
					<tr> 
						<th style="text-align:center !important">Client</th>
						<th style="text-align:center !important">Marque</th>
						<th style="text-align:center !important">Modèle</th>
						<th style="text-align:center !important">Début</th>
						<th style="text-align:center !important">Fin</th>
						<th style="text-align:center !important">Type intervention</th>
						<th style="text-align:center !important">Intervention</th>
					</tr>
				</thead>
				<tbody>
					<% for(C_Rdv rdv : liste){%>
						<tr> 
							<td style="text-align:center !important"><%=rdv.getVoiture().getClient().getNom()%> - <%=rdv.getVoiture().getClient().getPrenom()%></td>
							<td style="text-align:center !important"><%=rdv.getVoiture().getMarque()%></td>
	 						<td style="text-align:center !important"><%=rdv.getVoiture().getModele()%></td>
	         				<td style="text-align:center !important"><%=rdv.getDebut()%></td>
	         				<td style="text-align:center !important"><%=rdv.getFin()%></td>
	         				<td style="text-align:center !important"><%=rdv.getIntervention().getTypeIntervention().getNom()%> </td>
	         				<!-- Si le cout de l'intervention est de 0... pas encore fait -->
	         				<%if(rdv.getIntervention().getDuree() == 0 && rdv.getIntervention().getCt() == 0.0){ %>
	         					<td style="text-align:center !important"><div class="btn"><a href="${pageContext.request.contextPath}/CalculerSolde?id=<%=rdv.getId()%>">Solde</a></div></td>
	         				<%}
	         				
	         				else{%>
	         					<td style="text-align:center !important"><div class="btn"><a href="${pageContext.request.contextPath}/DetailIntervention?id=<%=rdv.getId()%>">Détails</a></div></td>
	         				<%}%>
						</tr>					
					<%}%> 
				</tbody>
			</table>
		</div>	
	<%}%>
	
	<!-- Retour -->
	<div class="text-center my-3">
      <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Views/garagiste/home.jsp'" class="btn btn-primary">Retour</button>
    </div>
	
	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>