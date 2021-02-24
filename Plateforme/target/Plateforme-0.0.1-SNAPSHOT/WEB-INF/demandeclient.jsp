<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
 <c:if test = "${empty sessionScope.userId}"> <c:redirect url="authentification"></c:redirect></c:if>
<c:import url="clientNav.jsp"/>

<%-- <c:set var="isDemandeInserted" value="Message"/>
 --%>
 <!-- Modal Message -->
<div class="modal fade" id="modalMessage" tabindex="-1" role="dialog" aria-labelledby="modalMessageLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Création d'une nouvelle demande</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body border-0">
      	<div class="text-center">
      		<i class="fas fa-${isDemandeInserted.icon} fa-5x"></i>
      		<h5><c:out value="${isDemandeInserted.msg}"></c:out></h5>
      	</div>
       
      </div>
      <div class="modal-footer border-0">
        <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<!-- Modal Message -->

 <!-- Modal Message -->
<div class="modal fade" id="modalPrix" tabindex="-1" role="dialog" aria-labelledby="modalPrixLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Prix Total</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body border-0">
      	<div class="text-center">
      		<c:if test="${qte.size() eq 1}">
      			<i class="fa fa-long-arrow-right fa-5x mr-1 mb-1"></i>
      			<h5>Veuillez insérer des nombres de cartons afin de récupérer le prix total de la demande.</h5>
      		</c:if>
      		<c:if test="${qte.size() gt 1}">
      			<table class="table">
				  <thead class="thead-light">
				    <tr>
				      <th scope="col">#</th>
				      <th scope="col">Type Carton</th>
				      <th scope="col">Quantité</th>
				    </tr>
				  </thead>
				  <tbody>
				  <c:forEach var="entry" items="${qte }" varStatus="status">        
					      <c:if test="${entry.key != 'msg'}">
					        <tr>
						      <th scope="row">${status.index}</th>
						      <td>${entry.key} Carton</td>
						      <td>${entry.value}</td>
						    </tr>
					      </c:if>
					</c:forEach>
				   <tr> 
				   	<td colspan="3" class="text-center"> Total : ${cost} Dhs</td>
				   </tr>
				  </tbody>
				</table>
      		</c:if>
      		<c:if test="${!empty offre}">
      			<div class="row">
      				<div class="col-4"><i class="fas fa-star fa-7x"></i></div>
      				<div class="col pull-right">
      					<h6><fmt:formatNumber var="perc" value="${offre.reduction_offre * 100}" maxFractionDigits="0" />
      					Vous bénéficiez d'une réduction de ${perc}%.
      					Votre nouveau total est de : 
      					<fmt:formatNumber var="total" value="${cost *(1-offre.reduction_offre)}" maxFractionDigits="0" /> <span style="color=blue; font-style:italic; font-weight:bold;">${total}</span> Dhs.</h6>
      				</div>
      			</div>
      		</c:if>
      	</div>
       
      </div>
      <div class="modal-footer border-0">
        <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<!-- Modal Message -->
<!-- Menu -->


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
                    <div class="col-md-6"><input type="number" class="form-control" min="1" placeholder="Petit Carton" name="pc" ></div>
                    <div class="col-md-6"><input type="number" class="form-control" min="1" name="mc" placeholder="Moyen Carton"></div>
                </div>

                <div class="row mt-3">
                    <div class="col-md-6 mx-auto">
                  		<input type="number" class="form-control" min="1" name="gc" placeholder="Grand Carton">                    	
                    </div>
                </div>

                <button class="btn btn-dark profile-button pull-right mt-5" type="submit" name="action" value="save">Enregistrer la demande</button>
                <button class="btn btn-dark profile-button text-left mt-5" type="submit" name="action" value="price">Prix total de la demande</button>
            </div>
        </div>
    </div>
</div>
</form>
</div>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<c:if test="${!empty isDemandeInserted}">
	<script type="text/javascript">
		$(window).on('load', function() {
			$('#modalMessage').modal('show');
		});
	</script>
</c:if>

<c:if test="${!empty qte}">
	<script type="text/javascript">
		$(window).on('load', function() {
			$('#modalPrix').modal('show');
		});
	</script>
</c:if>
</body>
</html>