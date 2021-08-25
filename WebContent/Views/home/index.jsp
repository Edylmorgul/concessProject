<!-- Header - informations -->
<%@include file="../layout/header.jsp" %>
	<header>	
		<!-- Navbar -->
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
  			<a class="navbar-brand" href="#">CondorAuto</a>
  			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="true" aria-label="Toggle navigation">
    			<span class="navbar-toggler-icon"></span>
  			</button>
  			
  			<!-- Option navbar -->
  			<div class="collapse navbar-collapse" id="navbarNav">
    			<ul class="navbar-nav">			     			
      				<li class="nav-item active">
        				<a class="nav-link" href="${pageContext.request.contextPath}/CreerUtilisateur"><i class="fas fa-address-book"></i> S'enregistrer </a>
      				</li>
      				<!-- Utilité de créer une servlet pour une page qui affiche juste une description ? Ne pas respecter le MVC peut-être ? -->
      				<li class="nav-item">
        				<a class="nav-link" href="${pageContext.request.contextPath}/Views/home/description.jsp"><i class="fas fa-align-justify"></i> Description</a>
      				</li>
      				<li>
      					<a class="nav-link" href="#!">Mot de passe oublié ?</a>
      				 </li>
    			</ul>
  			</div>
  			
  			<!-- Formulaire connexion -->
  			<form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/Connexion" method="POST">
             <div class="input-group input-group-sm" style="max-width: 380px;">
                  <input class="form-control form-horizontal" placeholder="Email..." name="email" type="email" required maxlength="50">
                  <input class="form-control form-horizontal" placeholder="Mot de passe..." name="mdp" type="password" required maxlength="50">
                  <div class="input-group-btn" style="max-width:120px;">
                       <button type="submit" class="btn btn-default" data-toggle="dropdown" value="submit" name="submit">Connexion</button>
                   </div>
              </div>       
        </form>
		</nav>
		<div class="erreurNav">${erreur}</div>
		
		<!-- Titre -->
        <h1>CondorEchec Automobile</h1>
        <p> L'automobile de l'échec ! </p>
        <div class="lineTitre"></div> 
	</header>
	
	<!-- Presentation -->
	<section class="presentation">
		<p> 
			Bienvenue sur le site de condorEchec Automobile ! </br>
			Afin de profiter de toutes nos fonctionnalités, nous vous conseillons d'y créer un compte ou de vous connecter le cas échéant !
		 </p>
	</section>
	
	<!-- principal -->
	<section class="principal">
		<p> 
			Nous réparons vos voitures depuis 10 ans déjà ! </br>
			Nos meilleurs garagistes selon vos différents problème, disponible toute la semaine du lundi au vendredi.
		</p>
	</section>
	
	<!-- Footer - informations -->
	<%@include file="../layout/footer.jsp" %>