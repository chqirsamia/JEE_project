<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--Liens css pour icones fas-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
          crossorigin="anonymous">
   <!--  <link rel="stylesheet" href="ressources/css/style.css"> -->
       <style type="text/css">
    <%@include file="../res/css/style.css" %>
</style>
   <style type="text/css">
    <%@include file="../res/css/bootstrap.min.css" %>
</style> 
    <!--Fichiers-->
    <link rel="icon" type="image/png" href="ressources/logo.png"/>
  <!--  <link rel="stylesheet" type="text/css" href="ressources/css/bootstrap.min.css"> -->
	<link rel="stylesheet" type="text/css" href="ressources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <title>HSA</title>
</head>

<body style="background-color:rgb(192,192,192);">

<!-- Menu -->

<c:import url="clientNav.jsp"/>

<!-- Page d'acceuil -->
<section id="slide" style="background: url('https://nsa40.casimages.com/img/2021/03/01/210301125329772203.jpg');background-size: cover;" >
<div class="carousel-item carousel-image-1   active"  >
 <div class="carousel-caption d-non d-sm-block text-center" style="margin-bottom:260px;">
<h2 class="display-4 d-inline mylead" style="background-color: rgb(119,136,153); font-family:  Lucida Console; font-size:110px;">Offres Actuelles</h2> 

</div>
</div>
<div class="container" style="margin-top:-260px;">
	<div class="row mt-5" >
		<c:forEach items="${offres}" var="offre" varStatus="status">
			<div class="col-4" style="margin-top:10px; margin-bottom:10px;">
			<div class="card text-center">
			  <div class="card-header" style="background : #E0EEEE;">
			  <i class="fas fa-star fa-2x"></i> 
			    <fmt:formatNumber var="perc" value="${offre.reduction_offre * 100}" maxFractionDigits="0" />
			    Offre de ${perc} %
			  </div>
			  <div class="card-body">
			    <h5 class="card-title"><i class="fas fa-box-open"></i> Descriptif </h5>
			    <p class="card-text">
			    
			    	<a style="font-weight:bold;">Nombre de cartons : </a><br>
			    	<c:if test = "${offre.carton_moyen ne -1}"> Petit Carton : ${offre.carton_petit} <br> </c:if>
			    	<c:if test = "${offre.carton_moyen ne -1}"> Carton Moyen : ${offre.carton_moyen} <br> </c:if>
			    	<c:if test = "${offre.carton_grand ne -1}"> Grand carton : ${offre.carton_grand} <br> </c:if>
			    </p>
			    
			  </div>
<!-- 			  <div class="card-footer text-muted">
			    2 days ago
			  </div> -->
			</div>
		</div>
		</c:forEach>
	</div>
</div>
</section>

<!-- Offres -->

<!-- Offres -->



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