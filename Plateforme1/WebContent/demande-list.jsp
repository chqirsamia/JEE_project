<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>plateforme demenagement</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="#" class="navbar-brand"> liste des demandes </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/user-list"
					class="nav-link">listes des admins</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Liste des demandes</h3>
			<hr>
			<div class="container text-left">

				<a href="<c:url value='ShowDemandeNT'/>" class="btn btn-success">afficher les demandes non traitees</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>nom</th>
						<th>prenom</th>
						<th>nombre de carton petit</th>
						<th>nombre de carton grand</th>
						<th>nombre de carton moyen</th>
						<th>prix total</th>
						<th>etat de la demande</th>
						<th>Action</th>
						
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="demande" items="${listDemandes}">
						<c:forEach var="user" items="${listUsers}">
						<c:if test="${demande.id_user == user.id}">
					
				
						<tr>
							<td><c:out value="${user.nom}" /></td>
							<td><c:out value="${user.prenom}" /></td>
							<td><c:out value="${demande.carton_petit}" /></td>
							<td><c:out value="${demande.carton_grand}" /></td>
							<td><c:out value="${demande.carton_moyen}" /></td>
							<td><c:out value="${demande.prix_total}" /></td>
							<td><c:out value="${demande.etat}" /></td>
							<c:if test="${demande.etat != 'nt'}">
							<td><a href="EditDemande?id=<c:out value='${demande.id}' />">marquer comme traitee</a></td>
								</c:if>	
						</c:if>	
							
							
						</tr>
					</c:forEach>
		</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
