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
				<a href="https://www.xadmin.net" class="navbar-brand"> list des offres </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list-offre"
					class="nav-link">offres</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List des offres</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/newOffre" class="btn btn-success">Add
					New offre</a>
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
						<th>nom admin</th>
						<th>prenom admin</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="offre" items="${listUser}">

						<tr>
							<td><c:out value="${offre.description}" /></td>
							<td><c:out value="${offre.reduction_offre}" /></td>
							<td><c:out value="${offre.carton_moyen}" /></td>
							<td><c:out value="${offre.carton_petit}" /></td>
							<td><c:out value="${offre.carton_grand}" /></td>
							<td><c:out value="${offre.nom}" /></td>
							<td><c:out value="${offre.prenom}" /></td>
							<td> <a
								href="delete?id=<c:out value='${offre.id_offre}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>