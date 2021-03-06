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

<style type="text/css">
    <%@include file="../res/css/style.css" %>
</style>
<style type="text/css">
    <%@include file="../res/css/authentification.css" %>
</style>
   <style type="text/css">
    <%@include file="../res/css/bootstrap.min.css" %>
</style>
<!--  <link rel="stylesheet" href="http://localhost:8088/Plateforme/style.css"> -->
<!-- <link rel="stylesheet" href="./ressources/css/authentification.css"> -->



  <title>Profile</title>
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

<!-- Modal Message -->
<div class="modal fade" id="modalMessage" tabindex="-1" role="dialog" aria-labelledby="modalMessageLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Informations Personnelles</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body border-0">
      	<div class="text-center">
      	<i class="fas fa-${isEdited.icon} fa-5x"></i>
      		<h5 style="margin-top:10px"><c:out value="${isEdited.msg}"></c:out></h5>
      	</div>
       
      </div>
      <div class="modal-footer border-0">
        <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<!-- Modal Message -->
<div class="container py-5">
    <div class="row text-center mb-5" style="height:35px;margin-top:40px;">
                                    <span class="inscription-form-title" style="margin-left:200px;font-size:60px;text-align:center;height:77px;background-color: rgb(176,224,230); ">
                                        Mes informations personnelles
                                    </span>
                                    </div>	
<form action="infoclient" method="post">

<div class="container rounded bg-white mt-5" style="margin-top:-5px!important">
    <div class="row">
        <div class="col-md-4 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5"><span class="font-weight-bold" style="margin-top:100px!important;font-size:2.5em;text-transform : uppercase;"><c:out value="${client.nom}"/> <c:out value="${client.prenom}"/></span><span class="text-black-50">Client #<c:out value="${client.id}"/></span></div>
        </div>
        <div class="col-md-8">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                   <h6 class="text-left"></h6>
                    <h6 class="text-right" style="font-weight:bold;margin-top:-15px">Edit Profile</h6>
                </div>
                <div class="row mt-2">
                    <div class="col-md-6"><input type="text" class="form-control" placeholder="First name" name="nom" value="<c:out value="${client.nom}"/>"></div>
                    <div class="col-md-6"><input type="text" class="form-control" name="prenom" value="<c:out value="${client.prenom}"/>" placeholder="Last name"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6"><input type="text" class="form-control" name="email" placeholder="Email" value="<c:out value="${client.email}"/>"></div>
                    <div class="col-md-6"><input type="text" class="form-control" name="tel" value="<c:out value="${client.tel}"/>" placeholder="Phone number"></div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6 mx-auto">
                    	<select class="form-control" name="sexe">
						  <option value="F" <c:if test="${client.sexe == 'F'}"> selected </c:if>>Féminin</option>
						  <option value="M" <c:if test="${client.sexe == 'M'}"> selected </c:if>>Masculin</option>
						</select>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6"><input type="password" class="form-control" placeholder="Password" name="pw"></div>
                    <div class="col-md-6"><input type="password" class="form-control" placeholder="Confirmer mot de passe" name="cpw"></div>
                </div>
                <div class="mt-5 text-right"><button class="btn btn-dark profile-button" type="submit">Save Profile</button></div>
            </div>
        </div>
    </div>
</div>
</form>
</div>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<c:if test="${!empty isEdited}">
	<script type="text/javascript">
		$(window).on('load', function() {
			$('#modalMessage').modal('show');
		});
	</script>
</c:if>
</body>
</html>