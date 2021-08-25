<%@ page import="Model.BEANS.C_Utilisateur" %>
<%@ page import="Model.BEANS.C_Garagiste" %>
<%@ page import="java.util.List"%>
<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
	<!-- Liste utilisateur servlet -->
	<% 
		List<C_Utilisateur> liste = (List)request.getAttribute("liste");
	%>
	
	<header>
		<!-- Titre -->
        <h1>Gestion utilisateur(s)</h1>
        <div class="lineTitre"></div>
	</header>
	
	<!-- Test si liste vide -->
	<% if(liste == null || liste.isEmpty()) {%>
		<h2> Aucun utilisateur présent sur le site ! </h2>
	<%}
	
	else{%>
		<form class="form-horizontal" method="POST" role="form" action="${pageContext.request.contextPath}/ListeUtilisateur">	
		<div class="container">
			<p>Trier par type utilisateur :</p>
			<div class="row">
				<!-- Choix type utilisateur -->
				<div class="col-sm-6">
					<select name="typeUtilisateur" id="typeUtilisateur">
        				<option value=1>Client</option>
        				<option value=2>Garagiste</option>
        				<option value=3>Tout</option>
    				</select>    	
				</div>
		
				<!-- Submit -->
				<div class="form-group my-4">
            		<button type="submit" id="submit" name="submit" class="btn btn-primary">Valider</button>
           		</div>       		
			</div>
		</div>                                  	
    	</form>
    	
		<!-- Tableau Utilisateurs -->
		<div class="container">
			<table class="table">
				<thead> 
					<tr> 
						<th>Nom</th>
						<th>Prenom</th>
						<th>Email</th>
						<th>Genre</th>
						<th>Actif</th>						
						<th>Statut</th> <!-- Definir statut compte -->	
						<th>Spécialisation</th> <!-- Definir spécialité(garagiste) -->	
						<th>Absence</th> <!-- Definir indisponibilité(garagiste) -->				
					</tr>
				</thead>
				<tbody>
					<% for(C_Utilisateur u : liste){%>
						<tr> 
							<td><%=u.getNom()%></td>
	 						<td><%=u.getPrenom()%></td>
	         				<td><%=u.getEmail()%></td>
	         				<td><%=u.getNomGenre()%></td>
	         				<td><%=u.getNomActif()%></td> 	         				  	         				
	         				<%
	         					if(u.getActif() == 1){%>
	         						<td><div class="btn"><a href="${pageContext.request.contextPath}/DesactiverCompte?id=<%=u.getId()%>">Desactiver</a></div></td>
	         					<%}
	         					else{%>
	         						<td><div class="btn"><a href="${pageContext.request.contextPath}/ReactiverCompte?id=<%=u.getId()%>">Activer</a></div></td>
	         					<%}%>
	         				<%
	         					if(u.recupTypeUti() instanceof C_Garagiste){%>
	         						<%C_Garagiste gara = (C_Garagiste)u.recupTypeUti();%>
	         						<%if(gara.getSpecialisation() == null || gara.getSpecialisation().getId() == 0){ %>
	         							<td><div class="btn"><a href="${pageContext.request.contextPath}/AjouterSpecialisation?id=<%=u.getId()%>">Ajouter</a></div></td>
	         						<%}
	         						else{%>
	         							<td><div class="btn"><a href="${pageContext.request.contextPath}/SupprimerSpecialisation?id=<%=u.getId()%>">Supprimer</a></div></td>
	         						<%}%>	
	         						<td><div class="btn"><a href="${pageContext.request.contextPath}/CreerIndisponibilite?id=<%=u.getId()%>">Absence</a></div></td>         						
	         					<%}    	         						
	         				%>
						</tr>					
					<%}%> 
				</tbody>
			</table>
		</div>	
	<%}%>
	
	<!-- Retour -->
	<div class="text-center my-3">
      <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Views/administrateur/home.jsp'" class="btn btn-primary">Retour</button>
    </div>
	
	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>