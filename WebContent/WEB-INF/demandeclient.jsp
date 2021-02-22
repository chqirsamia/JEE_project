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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
crossorigin="anonymous">
<!--Fichiers-->

<link rel="stylesheet" href="./ressources/css/style.css">
<link rel="stylesheet" href="./ressources/css/authentification.css">



  <title>Connexion</title>
  <style type="text/css">
  	body {
   		background: #E0EEEE
	}
	
	.form-control:focus {
	    box-shadow: none;
	    border-color: #E0EEEE
	}
	
	.profile-button {
/* 	    background: #E0EEEE;
 */	    box-shadow: none;
	    border: none
	}
	
/* 	.profile-button:hover {
	    background: #682773
	}
	
	.profile-button:focus {
	    background: #682773;
	    box-shadow: none
	}
	
	.profile-button:active {
	    background: #682773;
	    box-shadow: none
	}
	
/* 	.back:hover {
	    color: #682773;
	    cursor: pointer
	} */ */
  </style>
</head>

<body>

<!-- Menu -->

<c:import url="clientNav.jsp"/>

<div class="container py-5">
<div class="row text-center mb-5">
        <div class="col-lg-7 mx-auto">
            <h1>Nouvelle Demande</h1>
        </div>
</div>
<form action="demandeclient" method="post">

<div class="container rounded bg-white mt-5">
    <div class="row">
        <div class="col-md-4 border-right d-flex justify-content-center ">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
            <i class="fas fa-box-open fa-5x"></i>
            <span class="text-black-50">#Création de demande</span></div>
        </div>
        <div class="col-md-8">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <div class="d-flex flex-row align-items-center back"><i class="fa fa-long-arrow-left mr-1 mb-1"></i>
                        <h6>Back to home</h6>
                    </div>
                    <h6 class="text-right">Nouvelle Demande</h6>
                </div>
                <div class="row mt-2">
                    <div class="col-md-6"><input type="number" class="form-control" placeholder="Petit Carton" name="pc" ></div>
                    <div class="col-md-6"><input type="number" class="form-control" name="mc" placeholder="Moyen Carton"></div>
                </div>

                <div class="row mt-3">
                    <div class="col-md-6 mx-auto">
                  		<input type="number" class="form-control" name="gc" placeholder="Grand Carton">                    	
                    </div>
                </div>

                <div class="mt-5 text-right"><button class="btn btn-dark profile-button" type="submit">Estimer le prix</button></div>
            </div>
        </div>
    </div>
</div>
</form>
</div>
<script type="text/javascript" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>