<%@ page import="Model.BEANS.C_Rdv" %>
<%@ page import="Model.BEANS.Global" %>
<%@ page import="java.util.List"%> 
<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
	<header>
		<!-- Liste rdv servlet -->
		<% 
			List<C_Rdv> liste = (List)request.getAttribute("liste");
		%>
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
			<table class="table">
				<thead> 
					<tr> 
						<th style="text-align:center !important">Marque</th>
						<th style="text-align:center !important">Modèle</th>
						<th style="text-align:center !important">Début</th>
						<th style="text-align:center !important">Fin</th>
						<th style="text-align:center !important">Garagiste</th>
						<th style="text-align:center !important">Type intervention</th>
						<th style="text-align:center !important">Coût total</th>
					</tr>
				</thead>
				<tbody>
					<% for(C_Rdv rdv : liste){%>
						<tr> 
							<td style="text-align:center !important"><%=rdv.getVoiture().getMarque()%></td>
	 						<td style="text-align:center !important"><%=rdv.getVoiture().getModele()%></td>
	         				<td style="text-align:center !important"><%=rdv.getDebut()%></td>
	         				<td style="text-align:center !important"><%=rdv.getFin()%></td>
	         				<td style="text-align:center !important"><%=rdv.getGaragiste().getNom()%> - <%=rdv.getGaragiste().getPrenom()%></td>
	         				<td style="text-align:center !important"><%=rdv.getIntervention().getTypeIntervention().getNom()%> </td>
	         				<td style="text-align:right !important"><%=String.format("%.2f",rdv.getIntervention().getCt())+Global.euro%> </td>
						</tr>					
					<%}%> 
				</tbody>
			</table>
		</div>	
	<%}%>
	
	<!-- Retour -->
	<div class="text-center my-3">
      <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Views/client/home.jsp'" class="btn btn-primary">Retour</button>
    </div>

	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>