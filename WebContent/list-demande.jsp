<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<style type="text/css">
    <%@include file="./res/css/style.css" %>
</style>
<style type="text/css">
    <%@include file="./res/css/authentification.css" %>
</style>
   <style type="text/css">
    <%@include file="./res/css/bootstrap.min.css" %>
</style>
<!--  <link rel="stylesheet" href="./ressources/css/style.css"> -->
<!-- <link rel="stylesheet" href="./ressources/css/authentification.css"> -->

  <title>Demandes</title>
  
  <style type="text/css">
  	body {
	    background: #E0EEEE;/* linear-gradient(to right, #c04848, #480048); */
	    min-height: 100vh
	}
	
	
	
	.text-gray {
	    color: #aaa
	}
  </style>
</head>

<body>

<!-- Menu -->

<c:import url="navbar-admin.jsp"/>
<section id="blog" class="py-3" style="background: url('https://nsa40.casimages.com/img/2021/03/01/210301103059138869.jpg');background-size: cover;">
   <div  style=" margin-top: -20px; margin-bottom: -20px;">
		<div class="container-inscription" >

            <div class="container" >

                <div class="row">
                    <div class="wrap-inscription insc" style="height:auto; width:1500px;margin-left:auto;margin-right:auto;">
                        <div class="container" style="margin-left:-50px;margin-right:auto;" >
                            <div class="row">
                                    <span class="inscription-form-title" style="margin-left: auto;margin-right:auto;margin-top: -20px;font-size:400%;text-align:center;">
                                       Liste des demandes effectuées 
                                    </span>	
    <div class="row">
        <div class="col-lg-16 mx-auto">
        <br>
        
           <div class="container text-left" style="margin-bottom:-40px;">

				<a  href="<%=request.getContextPath()%>/ShowDemandeNF" class="btn btn-info">Demandes non traitées</a>
			</div>
			<div class="container text-right" style="margin-bottom:20px;">

				<a href="<%=request.getContextPath()%>/ShowDemandeF" class="btn btn-info">Demandes traitées</a>
			</div>
		
		
            <!-- List group-->
            <ul class="list-group shadow" style="margin-left:-45px;margin-right:-45px;width:1090px;">
                <!-- list group item-->
                <li class="list-group-item">
	                    <!-- Custom content-->
	                    <div class="row mt-0 font-weight-bold mb-2 dislay-4">
	                    	<div class="col-sm-6">
	                    		<h5 class="text-center"><i class="fas fa-box-open"></i> Demande</h5>
	                    	</div>
	                    	
	                    	<div class="col-sm-3">
	                    		<h5 class="text-center"><i class="fas fa-box-open"></i> Détails</h5>
	                    	</div>
	                    	
	                    	<div class="col-sm-3" style="width:1px">
	                    		<h5 class="text-center" style="margin-right:-70px;">Actions</h5>
	                    	</div>
	                    	
	                    	
	                    	
	                    </div>
	                </li>
                <c:forEach items="${demandes}" var="d" >
						<c:forEach var="user" items="${listUsers}">
						<c:if test="${d.demande.id_client == user.id}">
					
                	<li class="list-group-item">
	                    <!-- Custom content-->
	                    <div class="row mt-0 font-weight-bold mb-2 dislay-4">
	                    	
	                    	<div class="col-sm-6">
	                    		<div class="row  border border-info rounded" style="margin-right:0.5px;">
	                    		<c:set var="totalDmd" value="${0}"/>
	                    			<c:forEach items="${d.lds}" var="lc" varStatus="status">
	                    				<div class="col">
	                    					<c:choose>
	                    					<c:when test="${lc.carton.libelle_carton=='carton petit'}">
	                    					<h5><c:out value="${lc.carton.libelle_carton}"/> </h5>
	                    					<h6><i class="fa fa-long-arrow-right mr-1 mb-1"></i> <a style="font-weight:bold">Quantité : </a><c:set var="a" value="${lc.cd.nbr}"/><c:out value="${a}"/></h6>
	                    					<h6><i class="fa fa-long-arrow-right mr-1 mb-1"></i><a style="font-weight:bold"> Prix : </a><c:set var="pa" value="${lc.carton.prix} Dhs"/><c:out value="${pa}"/></h6>
	                    					<c:set var="totalDmd" value="${totalDmd + lc.cd.nbr * lc.carton.prix}" />
	                    					</c:when>
	                    					<c:when test="${lc.carton.libelle_carton=='carton moyen'}">
	                    					<h5><c:out value="${lc.carton.libelle_carton}"/> </h5>
	                    					<h6><i class="fa fa-long-arrow-right mr-1 mb-1"></i><a style="font-weight:bold"> Quantité :</a> <c:set var="b" value="${lc.cd.nbr}"/><c:out value="${b}"/></h6>
	                    					<h6><i class="fa fa-long-arrow-right mr-1 mb-1"></i> <a style="font-weight:bold">Prix : </a><c:set var="pb" value="${lc.carton.prix} Dhs"/><c:out value="${pb}"/></h6>
	                    					<c:set var="totalDmd" value="${totalDmd + lc.cd.nbr * lc.carton.prix}" />
	                    					</c:when>
	                    					<c:when test="${lc.carton.libelle_carton=='carton grand'}">
	                    					<h5><c:out value="${lc.carton.libelle_carton}"/> </h5>
	                    					<h6><i class="fa fa-long-arrow-right mr-1 mb-1"></i><a style="font-weight:bold"> Quantité :</a> <c:set var="c" value="${lc.cd.nbr}"/><c:out value="${c}"/></h6>
	                    					<h6><i class="fa fa-long-arrow-right mr-1 mb-1"></i><a style="font-weight:bold"> Prix : </a><c:set var="pc" value="${lc.carton.prix} Dhs"/><c:out value="${pc}"/></h6>
	                    					<c:set var="totalDmd" value="${totalDmd + lc.cd.nbr * lc.carton.prix}" />
	                    					</c:when>
	                    				</c:choose>
	                    				</div>
	                    				
<c:forEach items="${offre}" var="entry">
<c:set var ="entrykey" value="${entry.key}"/>
    <c:if test="${entrykey['carton grand'] ==c && entrykey['carton moyen'] ==b && entrykey['carton petit'] ==a  }">
    
    <c:set var="red" value = "${entry.value}" />
    <fmt:formatNumber var="totalDmd" value="${totalDmd *(1-red)}" maxFractionDigits="0"/>
    
    </c:if>
</c:forEach>
	                    			</c:forEach>
	                    		</div>
	                    	</div>
	                    	<div class="col-sm-4">
	                    		<div class="row  border border-info rounded">
	                    		
	                    			
	                    					<h6><i class="fa fa-long-arrow-right mr-1 mb-1"></i><a style="font-weight:bold"> Numéro demande : </a><c:out value="${d.demande.id}"/></h6>
	                    					<h6><i class="fa fa-long-arrow-right mr-1 mb-1"></i><a style="font-weight:bold"> Date de creation : </a><c:out value="${d.demande.date_creation}"/></h6>
	                    					<h6><i class="fa fa-long-arrow-right mr-1 mb-1"></i><a style="font-weight:bold"> Total : </a><c:out value="${totalDmd}"/> Dhs</h6>
	                    					
	                    				
	                    				
	                    			
	                    		</div>
	                    		
	                    		<div class="row  border border-info rounded">
	                    		
	                    			
	                    					<h6><i class="fa fa-long-arrow-right mr-1 mb-1"></i><a style="font-weight:bold"> Nom client : </a><c:out value="${user.nom} ${user.prenom}"/><br></h6>
	                    					<h6><i class="fa fa-long-arrow-right mr-1 mb-1"></i><a style="font-weight:bold"> Email client : </a><c:out value="${user.email}"/></h6>
	                    					<h6><i class="fa fa-long-arrow-right mr-1 mb-1"></i><a style="font-weight:bold"> Numéro de telephone: </a><c:out value="${user.tel}" /></h6>
	                    					
	                    				
	                    				
	                    			
	                    		</div>
	                    	
	                    	</div>
	                    	
	                    	<div class="col-sm-1" style="text-align:center;margin-left:35px;">
	                    	<c:if test="${d.demande.etat == 'NF'}">
							<td><a href="EditDemande?id=<c:out value='${d.demande.id}'/>" >marquer comme traitee</a></td>
								</c:if>	
	                    	</div>
	                    </div>
	                    	</c:if>	
							
							
						
					</c:forEach>
	                    <%-- <div class="media align-items-lg-center flex-column flex-lg-row p-3">
	                        <div class="media-body order-2 order-lg-1">
	                            <h5 class="mt-0 font-weight-bold mb-2">Demande #<c:out value="${d.demande.id}"/> Date Création : <c:out value="${d.demande.date_creation}"/></h5>
	                        </div>
	                    </div> --%> <!-- End -->
	                </li>
                </c:forEach>
            </ul> <!-- End -->
        </div>
      </div>
      </div>
      </div>  
    </div>
</div>
</div></div></div></section>

</body>
<script type="text/javascript" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</html>