<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">

<!--Liens-->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
crossorigin="anonymous">
<!--Fichiers-->
<link rel="stylesheet" href="./ressources/css/style.css">
<link rel="stylesheet" href="./ressources/css/inscription.css">
<link rel="stylesheet" href="./ressources/css/validation.css">
  <title>Inscription</title>
</head>

<body>

<!-- Menu -->

<c:import url="/navbar.jsp"/>

  <section id="blog" class="py-3" style="background: url('./ressources/fond_authentif.jpg');background-size: cover;">
		<div class="container-inscription" style="margin-top: -20px; margin-bottom: -20px;width:100%"  >

			<div class="wrap-inscription insc" style="height:700px;">
			
                            <div class="row" style="margin-left:0px;">
			 
                <span class="inscription-form-title" style="margin-left: 55px;margin-top: 7px;font-size:400%;text-align:center;padding-bottom:-100px;">
                    Inscription
                </span>
                
				<form method="post" action="<c:url value='/inscription'/>" >

                   <!--NOM-->
                    <div class="wrap-input100 validate-input" data-validate ="">
						<input class="input100" type="text" name="nom" value="<c:out value='${user.nom}'/>" placeholder="Nom">
                        <span style="color:red">${form.erreurs['nom']}</span>
					</div>

					
					 <!--PRENOM-->
                   
                     <div class="wrap-input100 validate-input" data-validate = "Champ obligatoire">
						<input class="input100" type="text" value="<c:out value='${user.prenom}'/>" name="prenom" placeholder="Prénom">
                         <span style="color:red">${form.erreurs['prenom']}</span>
					</div>

                    
                   
					<!--Email-->
					<div class="wrap-input100 validate-input" data-validate = "Le format valide: exemple@abc.xyz">
						<input class="input100" type="email" value="<c:out value='${user.email}'/>" name="email" placeholder="Email">
						
                        <span style="color:red">${form.erreurs['email']}</span>
					</div>
                   <!--Sexe-->
                    
                    <div class="mylabel" style="font-weight: bold; margin-left: 50px;"> Sexe: </div>
                    <div class="mylabel">
                        <input type="radio" id="H" name="sexe"  value="H" checked>
                        <div class="slidinggroove"></div>
                        <label class="mylabel" for="H"><p class="labelterm">Homme</p></label>
                    </div>

                    <div class="mylabel" style="margin-left: 150px;">
                        <input type="radio" id="F" name="sexe"  value="F">
                        <div class="slidinggroove"></div>
                        <label class="mylabel" for="F" ><p class="labelterm">Femme</p></label>
                    </div>
                   <!--MOT de PASSE-->
                    <div class="wrap-input100 validate-input" data-validate = "Champ obligatoire">
						<input class="input100" type="password" value="<c:out value='${user.password}'/>" name="motdepasse" placeholder="Mot de passe">

                        <span style="color:red">${form.erreurs['motdepasse']}</span>
					</div>

					
					<!--Confirmer mot de passe -->
					
					 <div class="wrap-input100 validate-input" data-validate = "Champ obligatoire">
						<input class="input100" type="password" name="confirmationmotdepasse" placeholder="Confirmer mot de passe">

                    </div>
                    
                   <!--Les checkbox-->
                       <div class="checkbox icheck-check1">
                        <input type="checkbox" id="check1" value="1" name="accepte"/>
                        <label for="check1" style="font-weight:bold;">J'accepte les termes et conditions et la politique de confidentialité</label>
                    </div>
                    <span style="color:red">${form.erreurs['accepte']}</span>
                
                
                <!--Buton pour envoyer demande d'inscription-->
                
                
					<div class="container-inscription-form-btn" >
						<input type="submit" style="margin-right: 30px;" class="inscription-form-btn" value="Envoyer" />
					</div>
				</form>
			</div>
		</div>
	</div>

  </section>

  
  

<!-- Bas de page -->

<footer class="bg-light text-center text-lg-start" id="contact" >

<div style="padding: 50px 0; background: #333; text-align: left; color: #aaa;width:100%;">
        <div class="container p-4" >
            <div class="row">
                <div class="col-lg-6 col-md-12 mb-4 mb-md-0">
                    <h3 style="font-weight:bold">Contact</h3>
                    <br>
                    <p><i class="fas fa-map-marker-alt"></i> Rue Al Maouz, Hay Riad, Rabat</p>
                    <p><i class="fas fa-phone" ></i> +212 5 36 37 38 39</p>
                    <p><i class="fas fa-envelope"></i><a href="mailto:my@domain.com" class="a"> contact@has.com</a></p>
                </div>
                
                <div class="col-lg-6 col-md-12 mb-4 mb-md-0">
                    <br>
                    <p style="font-weight:bold;color:while;">
                        Un service client disponible et réactif<br><br>Une équipe qui travaille  pour vous offrir des conseils fidèles<br><br><span style="color:steelblue;font-size:20px;">Notre but principal est de vous satisfaire</span>
                    </p>
                </div>

                
            </div>
        </div>
        </div>
</footer>

<!--CODE JAVASCRIPT-->



<!--Liens-->

<script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>

<script src="./ressources/js/moment.min.js"></script>
<script src="./ressources/js/global.js"></script>
<script src="./ressources/js/validation.js"></script>

<script>
    /*MOVEMENT LORS LE SITE SE LANCE*/
  
    //navbar
    ScrollReveal().reveal('.navmov',{
        origin:'top',
        duration:2000,
        distance:'60px'
    });
    //slide
    ScrollReveal().reveal('.insc',{
        origin:'bottom',
        duration:2000,
        distance:'60px'
    });
    //footer
    ScrollReveal().reveal('.colfooter0',{
          origin:'bottom',
          duration:2000,
          distance:'60px'
      });
      ScrollReveal().reveal('.colfooter1',{
          origin:'bottom',
          duration:2000,
          distance:'60px'
      });
      ScrollReveal().reveal('.colfooter2',{
          origin:'bottom',
          duration:2000,
          distance:'60px'
      });
      ScrollReveal().reveal('.rowfooter',{
          origin:'right',
          duration:2000,
          distance:'60px'
      });
      ScrollReveal().reveal('.inscriremv' ,{
          origin:'bottom',
          duration:2000,
          distance:'60px'
      });
    
  </script>
  
  
  </body>
  </html>
  