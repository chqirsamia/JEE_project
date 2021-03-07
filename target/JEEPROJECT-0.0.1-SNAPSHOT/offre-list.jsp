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
<title>Offres</title>
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

	<c:import url="navbar-admin.jsp"/>
<section id="blog" class="py-3" style="background: url('https://nsa40.casimages.com/img/2021/03/01/210301103059138869.jpg');background-size: cover;">
   <div  style=" margin-top: -20px; margin-bottom: -20px;">
		<div class="container-inscription">

            <div class="container">

                <!--Authentification par un compte existant-->
                <div class="row">
                    <div class="wrap-inscription insc" style="margin-bottom: 100px;height:auto; width:1400px;margin-left:auto;margin-right:auto;">
                        <div class="container">
                            <div class="row">
                                    <span class="inscription-form-title" style="margin-left: 227px;margin-right:auto;margin-top: -20px;font-size:400%;text-align:center;">
                                        Liste des offres
                                    </span>	

		<div class="container" style="margin-left: auto;margin-right:auto;" >
			<div class="container text-left" style="margin-left: -48px;" >
<br>
				<a href="<c:url value='newOffre'/>" class="btn btn-info">Ajouter une offre</a>
			</div>
			<br>
			<table class="table table-bordered" style="margin-left: -47px;">
				<thead>
					<tr style="color:steelblue" >
						<th  style="width:300px; text-align:center;">Description</th>
						<th style="text-align:center;">montant de reduction</th>
						<th style="text-align:center;">carton petit</th>
						<th style="text-align:center;">carton moyen</th>
						
						<th style="text-align:center;">carton grand</th>
						<th style="text-align:center;">id admin</th>
						
						<th style="width:130px; text-align:center;">Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="offre" items="${listOffre}">

						<tr>
							<td style="text-align:center;"><c:out value="${offre.description}" /></td>
							<td style="text-align:center;"><c:out value="${offre.reduction_offre}" /></td>
							<td style="text-align:center;"><c:out value="${offre.carton_petit}" /></td>
							<td style="text-align:center;"><c:out value="${offre.carton_moyen}" /></td>
							
							<td style="text-align:center;"><c:out value="${offre.carton_grand}" /></td>
							
							<td style="text-align:center;"><c:out value="${offre.id_admin}" /></td>
							
							<td style="text-align:center;"><a href="editOffre?id=<c:out value='${offre.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="deleteOffre?id=<c:out value='${offre.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div></div></section>
</body>
</html>