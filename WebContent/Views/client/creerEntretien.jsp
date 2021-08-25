<%@ page import="java.util.List"%>
<%@ page import="Model.BEANS.C_Voiture" %>
<%@ page import="Model.BEANS.C_TypeIntervention" %>
<%@ page import="Model.BEANS.C_Utilisateur" %>
<%@ page import="Model.BEANS.C_Garagiste" %>
<%@ page import="Model.BEANS.C_Rdv" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>

<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
	<!-- Voiture + liste type intervention servlet -->
	<% 
		C_Voiture v = (C_Voiture)request.getAttribute("voiture");
		List<C_TypeIntervention> liste = (List)request.getAttribute("liste");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM YYYY");
		LocalDate jour;
		LocalDate lundi 	= C_Rdv.ChargerSemaine();
		LocalDate mardi 	= lundi.plusDays(1);
		LocalDate mercredi 	= mardi.plusDays(1);
		LocalDate jeudi 	= mercredi.plusDays(1);
		LocalDate vendredi 	= jeudi.plusDays(1);
		String flagChecker = "";
		boolean checkflag = true;
		C_TypeIntervention typeIntervention = new C_TypeIntervention();
		if(!liste.isEmpty())typeIntervention = liste.get(0);
	%>
	
	<header>
		<!-- Titre -->
        <h1>Rendez-vous</h1>
        <div class="lineTitre"></div>
	</header>
		
	<!-- Formulaire entretien -->
	<div class="container">
		<p>Veuillez remplir le formulaire ci-dessous pour prendre un rendez-vous</p>
		<p>Votre voiture : <%=v.getMarque()%> - <%=v.getModele()%></p>
		<div class="row">
			<div class="card">
				<div class="card-header">Formulaire</div>
				<div class="card-body">
					<form class="form-horizontal" id="form" method="POST" role="form" action="${pageContext.request.contextPath}/CreerEntretien">
						<!-- Test si liste vide -->
						<% if(liste == null || liste.isEmpty()) {%>
							<h2> Aucune intervetion de disponible ! </h2>
						<%} 
						else{%>
							<!-- Choix type intervention -->
							<label class="col-sm-9 control-label" for="typeSpe">Type intervention :</label>                                
                        	<select name="typeSpe" id="typeSpe">
                        		<% for(C_TypeIntervention t : liste){%>
                        			<option value=<%=t.getId()%>><%=t.toString()%></option>
                        		<%}%>   
            				</select>
            				<table id="tabTypeSpe" class="table" style="display:none">
            					<% for(C_TypeIntervention t : liste){%>
                        			<tr>
                        				<td><%=t.getId()%></td>
                        				<td><%=t.getDureepr()%></td>
                        				<td><%=(t.getSpecialisation()).getId()%></td>
                        			</tr>
                        		<%}%> 
            				</table>
							<table class="table">
								<thead> 
									<tr> 
										<th>
											<div class="form-group text-center m-3">
												<input type="button" id="CSP" class="btn btn-primary" value="Précédent">
											</div>
										</th>
										<th id="thLundi" value="<%=lundi%>">
											<div class="form-group text-center m-3">
												<%=formatter.format(lundi)%>
											</div>
										</th>
										<th id="thMardi" value="<%=mardi%>">
											<div class="form-group text-center m-3">
												<%=formatter.format(mardi)%>
											</div>
										</th>
										<th id="thMercredi" value="<%=mercredi%>">
											<div class="form-group text-center m-3">
												<%=formatter.format(mercredi)%>
											</div>
										</th>
										<th id="thJeudi" value="<%=jeudi%>">
											<div class="form-group text-center m-3">
												<%=formatter.format(jeudi)%>
											</div>
										</th>
										<th id="thVendredi" value="<%=vendredi%>">
											<div class="form-group text-center m-3">
												<%=formatter.format(vendredi)%>
											</div>
										</th>
										<th>
											<div class="form-group text-center m-3">
												<input type="button" id="CSS" class="btn btn-primary" value="Suivant">
											</div>
										</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
										</td>
										<td id="tdLundi">
											<%=flagChecker = C_Rdv.GenererMomentsDisponibles(lundi, typeIntervention, checkflag)%>
											<%if (!(flagChecker.equals(""))) checkflag = false;%>
										</td>
										<td id="tdMardi">
											<%=flagChecker = C_Rdv.GenererMomentsDisponibles(mardi, typeIntervention, checkflag)%>
											<%if (!(flagChecker.equals(""))) checkflag = false;%>
										</td>
										<td id="tdMercredi">
											<%=flagChecker = C_Rdv.GenererMomentsDisponibles(mercredi, typeIntervention, checkflag)%>
											<%if (!(flagChecker.equals(""))) checkflag = false;%>
										</td>
										<td id="tdJeudi">
											<%=flagChecker = C_Rdv.GenererMomentsDisponibles(jeudi, typeIntervention, checkflag)%>
											<%if (!(flagChecker.equals(""))) checkflag = false;%>
										</td>
										<td id="tdVendredi">
											<%=flagChecker = C_Rdv.GenererMomentsDisponibles(vendredi, typeIntervention, checkflag)%>
										</td>
										<td>
										</td>
									</tr>
								</tbody>
							</table>
            				<!-- Submit -->
                        	<div class="form-group text-center my-3">
								<input type="button" id="dateChecker" class="btn btn-primary" value="Valider">
							</div>
            			<%}%>
					</form>
					<div class="erreur">${erreur}</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Retour -->
	<div class="text-center my-3">
      <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/Views/client/home.jsp'" class="btn btn-primary">Retour</button>
    </div>
    
    <script type="text/javascript">
    	
    	let checkflag = true;
    	let duree = document.getElementById("tabTypeSpe").getElementsByTagName("tr")[0].getElementsByTagName("td")[1].innerHTML;
    	let spe = document.getElementById("tabTypeSpe").getElementsByTagName("tr")[0].getElementsByTagName("td")[2].innerHTML;		
    	
   		function comparerDureeEtSpe() {
   			
   		  	var table, tr, td, typeIntervention;
   		  	table = document.getElementById("tabTypeSpe");
   		  	tr = table.getElementsByTagName("tr");
   		  	typeIntervention = document.getElementById("typeSpe").value;

   		  	for (i = 0; i < tr.length; i++) {
	   		  	td = tr[i].getElementsByTagName("td")[0].innerHTML;
	   		    if (td == typeIntervention) {
	   		    	if (duree != tr[i].getElementsByTagName("td")[1].innerHTML || spe != tr[i].getElementsByTagName("td")[2].innerHTML) {
	   		    		duree = tr[i].getElementsByTagName("td")[1].innerHTML;
	   		    		spe = tr[i].getElementsByTagName("td")[2].innerHTML;
	   		    		completerDispos();
	   		    	}
		   		    break;
	   		    }
   		  	}
   		}	
    
	    function completerDisposJour (str){
	    	var jour = document.getElementById("th"+str).attributes["value"].value;
	    	var typeIntervention = document.getElementById("typeSpe").value
		    var xhr = new XMLHttpRequest();
			
			xhr.onreadystatechange = function () {
				if(xhr.readyState === 4 && xhr.status === 200) {
					document.getElementById("td"+str).innerHTML = xhr.responseText;
					if (checkflag && xhr.responseText != "") checkflag = false;
				}
			};
			xhr.open("GET","ChercherDispos?jour="+encodeURIComponent(jour)+"&typeIntervention="+encodeURIComponent(typeIntervention)+"&checkflag="+encodeURIComponent(checkflag),false);
			xhr.send();
		};
	    
    	function completerDispos(){
    		
    		checkflag = true;
    		
    		document.getElementById("tdLundi").innerHTML = "";
    		document.getElementById("tdMardi").innerHTML = "";
    		document.getElementById("tdMercredi").innerHTML = "";
    		document.getElementById("tdJeudi").innerHTML = "";
    		document.getElementById("tdVendredi").innerHTML = "";
    		
    	    completerDisposJour("Lundi");
    	    completerDisposJour("Mardi");
    	    completerDisposJour("Mercredi");
    	    completerDisposJour("Jeudi");
    	    completerDisposJour("Vendredi");
    		
    	}
    
    	function completerJours(str){

    		var lundi = document.getElementById("thLundi");
    	    var mardi = document.getElementById("thMardi");
    	    var mercredi = document.getElementById("thMercredi");
    	    var jeudi = document.getElementById("thJeudi");
    	    var vendredi = document.getElementById("thVendredi");
			
			lundi.attributes["value"].value = str.split(';')[0];
			lundi.children[0].innerHTML = str.split(';')[1];
			mardi.attributes["value"].value = str.split(';')[2];
			mardi.children[0].innerHTML = str.split(';')[3];
			mercredi.attributes["value"].value = str.split(';')[4];
			mercredi.children[0].innerHTML = str.split(';')[5];
			jeudi.attributes["value"].value = str.split(';')[6];
			jeudi.children[0].innerHTML = str.split(';')[7];
			vendredi.attributes["value"].value = str.split(';')[8];
			vendredi.children[0].innerHTML = str.split(';')[9];
			
			completerDispos();
    	}
		
		document.getElementById("CSS").onclick = function(){
			var jour = document.getElementById("thLundi").attributes["value"].value;
    	    var xhr = new XMLHttpRequest();

			xhr.onreadystatechange = function () {
				if(xhr.readyState === 4 && xhr.status === 200) {
					completerJours(xhr.responseText);
				}
			};
			xhr.open("GET","ChangerSemaine?jour="+encodeURIComponent(jour)+"&jours=7",false);
			xhr.send();
		};
		
		document.getElementById("CSP").onclick = function(){
			
			var jour = document.getElementById("thLundi").attributes["value"].value;
    	    var xhr = new XMLHttpRequest();

			xhr.onreadystatechange = function () {
				if(xhr.readyState === 4 && xhr.status === 200) {
					if(xhr.responseText != "") {
						completerJours(xhr.responseText);
				    }
				}
			};
			xhr.open("GET","ChangerSemaine?jour="+encodeURIComponent(jour)+"&jours=-7",false);
			xhr.send();
		};
		
		document.getElementById("typeSpe").onchange = function(){
			comparerDureeEtSpe();
		};
		
		document.getElementById("dateChecker").onclick = function(){
			var flag = 0;
            var radios = document.getElementsByName('date');

            for (var i = 0, length = radios.length; i < length; i++) {
                if (radios[i].checked) {
                	flag = 1;
                    document.getElementById("form").submit();
                    break;
                }
            }
            if(flag == 0)alert("Veuillez sélectionner une date et une heure pour le rendez - vous !");
        };
    	
	</script>

	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>