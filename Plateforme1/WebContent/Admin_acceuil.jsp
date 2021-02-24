<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--Liens css pour icones fas-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
          crossorigin="anonymous">
    <link rel="stylesheet" href="ressources/css/style.css">
    
    <!--Fichiers-->
    <link rel="icon" type="image/png" href="ressources/logo.png"/>
    <link rel="stylesheet" type="text/css" href="ressources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="ressources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <title>HSA</title>
</head>

<body style="background-color:rgb(192,192,192);">

<!-- Menu -->

<c:import url="navbar-admin.jsp"/>

<!-- Page d'acceuil -->
<section id="slide" style="background: url('./ressources/demenagement2.jpg');background-size: cover;">
<div class="carousel-item carousel-image-1   active">
 <div class="carousel-caption d-non d-sm-block text-center" >
<h2 class="display-4 d-inline mylead" style="background-color: rgb(119,136,153); font-family:  Lucida Console; font-size:110px;">Bienvenue à HSA </h2> 
<p></p>
<p class="lead textbg" style="background-color:rgba(41, 128, 185,0.6);font-size:30px;font-weight:bold;font-family:garamond">  Une équipe de professionnels qui vous accompagne pour vivre autrement votre expérience de déménagement <br> </p><br><br><br><br><br><br><br>
</div>
</div>
</section>
<!--Nos services  -->
<div class="row" id="apropos" style="margin-top: 60px;margin-bottom: 60px;">


 <!-- Service Client -->
 <div class="col-12 col-lg-3 srv1" >
    <div class="single-service-area mb-80">
        <div class="service-icon" >
            <i class="far fa-check-circle" style="margin-top: 25px;"></i>
            </div>
            <h5  style="font-weight: bold;">Services de qualité</h5>
            <p></p>
        </div>
        <center>
            Personnel rigoureux et bien formé<br> Professionnalisme et sérieux 
            <br> <br> <br>
        </center>
    </div>
    
    <div class="col-12 col-lg-3 srv2">
        <div class="single-service-area mb-80">
            <div class="service-icon">
            <i class="fas fa-dollar-sign" style="margin-top: 25px;"></i>
            </div>
            <h5 style="font-weight: bold;">Meilleur rapport qualité prix</h5>
            <p></p>
        </div>
        <center>
           Offrant ponctualité ainsi que la flexibilité <br>  adaptée à vos propres critères<br><br>
        </center>
    </div>

   <div class="col-12 col-lg-3 srv3">
        <div class="single-service-area mb-80">
            <div class="service-icon">
                <i class="fas fa-gavel" style="margin-top: 25px;"></i>
            </div>
            <h5  style="font-weight: bold;">Tous les équipements disponibles</h5>
            <p></p>
        </div>
        <center>
        Emballage spécial pour vos biens <span style="font-weight: bold;color: steelblue;"> fragiles </span><br> Experts du juste à temps<br><br>
        </center>
    </div>
   <div class="col-12 col-lg-3 srv4" >
<div class="single-service-area mb-80">
        <div class="service-icon" >
                <i class="fas fa-shield-alt" style="margin-top: 25px;"></i>
            </div>
            <h5  style="font-weight: bold;">Sécurité et disponibilité</h5>
            <p></p>
        </div>
        <center>
            Vos biens seront entre mains expertes <br> et traverseront leur trajet en toute sécurité
            <br> <br> 
        </center>
    </div>

</div>

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

<!--Complément JS-->

<!--Mouvement lors de l'affichage-->

<script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>
<script>
ScrollReveal().reveal('.mylead',{
    origin:'left',
    duration:2000,
    distance:'60px'
});
ScrollReveal().reveal('.textbg',{
    origin:'bottom',
    duration:2000,
    distance:'60px'
});
   
</script>


</body>

</html>