<%@ page import="Model.BEANS.C_Rdv" %>
<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
	
	<% 
		C_Rdv rdv = (C_Rdv)request.getAttribute("rdv");
		String euro = "&#x20AC;";
	%>
	
	<header>
		<!-- Titre -->
        <h1>Détail intervention</h1>
        <div class="lineTitre"></div>
	</header>
	
	<!-- Tableaux détails des rendez-vous -->
	<div class="container">
		<table class="table">
			<thead> 
				<tr> 
					<th style="text-align:center !important">Client</th>
					<th style="text-align:center !important">Marque</th>
					<th style="text-align:center !important">Modèle</th>
					<th style="text-align:center !important">Début</th>
					<th style="text-align:center !important">Fin</th>
					<th style="text-align:center !important">Durée</th>
					<th style="text-align:center !important">Solde</th>
				</tr>
			</thead>
			<tbody>
				<tr> 
					<td style="text-align:center !important"><%=rdv.getVoiture().getClient().getNom()%> - <%=rdv.getVoiture().getClient().getPrenom()%></td>
					<td style="text-align:center !important"><%=rdv.getVoiture().getMarque()%></td>
	 				<td style="text-align:center !important"><%=rdv.getVoiture().getModele()%></td>
	         		<td style="text-align:center !important"><%=rdv.getDebut()%></td>
	         		<td style="text-align:center !important"><%=rdv.getFin()%></td>	
	         		<td style="text-align:center !important"><%=rdv.getIntervention().getDuree()%></td>
	         		<td style="text-align:right !important"><%=String.format("%.2f",rdv.getIntervention().getCt())+euro%></td>
	         		<td></td>         			
				</tr>					
			</tbody>
		</table>
	</div>	
	
	<!-- Retour -->
	<div class="text-center my-3">
      <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Views/garagiste/home.jsp'" class="btn btn-primary">Retour</button>
    </div>
	
	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>