<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  
<!--CODE CSS-->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
crossorigin="anonymous">
<!--Fichiers-->

<link rel="stylesheet" href="/ressources/css/style.css">
<link rel="stylesheet" href="/ressources/css/authentification.css">
<link rel="stylesheet" href="/ressources/css/validation.css">


  <title>Connexion</title>
</head>

<body>

<!-- Menu -->

<c:import url="/navbar.jsp"/>
  
  <section id="blog" class="py-3" style="background: url('../ressources/fond_authentif.jpg');background-size: cover;">
   <div  style=" margin-top: -20px; margin-bottom: -20px;">
		<div class="container-inscription">

            <div class="container">

                <!--Authentification par un compte existant-->
                <div class="row">
                    <div class="wrap-inscription insc" style="margin-bottom: 100px;height:440px;">
                        <div class="container">
                            <div class="row">
                                <form method="post" action="<c:url value='/authentification'/> " style="align-self: center;">
                                    <span class="inscription-form-title" style="margin-left: -15px;margin-top: -20px;font-size:400%;text-align:center;">
                                        Authentification
                                    </span>			
                                    <!--Email-->
                                    <div  class="wrap-input100 validate-input" data-validate = "Le format valide: exemple@abc.xyz">
                                        <input class="input100" type="text" value="<c:out value='${user.email}'/>" name="email" placeholder="Email">
                                        <span class="symbol-input100">
                                        </span>
                                        <span style="color:red;">${form.erreurs['email']}</span>
                                    </div>
                                
                                <!--MOT de PASSE-->
                                    <div  class="wrap-input100 validate-input" data-validate = "Le format valide: exemple@abc.xyz">
                                        <input class="input100" type="password" name="motdepasse" placeholder="Mot de passe">
                                        <span class="symbol-input100">
                                           
                                        </span>
                                        <span style="color:red;">${form.erreurs['motdepasse']}</span>
                                        <span style="color:red;">${form.resultat}</span>
                                    </div>
                                
                
                                <!--Buton pour connecter-->
                                <button type="submit" class="authentification-form-btn" style="margin-top:30px"> <br>

                                    <span>
                                        Connexion 
                                    </span>
                                </button>


                                </form> 
                            </div>
                            <label for="check1"><br><a style="font-size:15px;margin-left:70px;">Si vous n'avez pas encore de compte <br></a><a style="font-size:18px;margin-left:80px;font-weight:bold;hover:black;" href="<c:url value="/inscription"/>"> Créez un nouveau compte</a></label>
                            
                               
                            </div>

                        </div>

                    </div>
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
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
        crossorigin="anonymous"></script>

<script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>
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