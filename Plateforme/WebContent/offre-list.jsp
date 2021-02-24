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
<title>admin</title>
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

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Liste des Offres</h3>
			<hr>
			<div class="container text-left">

				<a href="<c:url value='newOffre'/>" class="btn btn-info">ajouter une offre</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Description</th>
						<th>montant de reduction</th>
						<th>carton moyen</th>
						<th>carton petit</th>
						<th>carton grand</th>
						<th>id admin</th>
						
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="offre" items="${listOffre}">

						<tr>
							<td><c:out value="${offre.description}" /></td>
							<td><c:out value="${offre.reduction_offre}" /></td>
							<td><c:out value="${offre.carton_moyen}" /></td>
							<td><c:out value="${offre.carton_petit}" /></td>
							<td><c:out value="${offre.carton_grand}" /></td>
							
							<td><c:out value="${offre.id_admin}" /></td>
							
							<td><a href="editOffre?id=<c:out value='${offre.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="deleteOffre?id=<c:out value='${offre.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
