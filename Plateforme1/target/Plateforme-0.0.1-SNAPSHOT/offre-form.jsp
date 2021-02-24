<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>HSA</title>
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
				<a href="https://www.xadmin.net" class="navbar-brand"> ajouter admin </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Users</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${offre != null}">
					<form action="updateOffre" method="post">
				</c:if>
				<c:if test="${offre == null}">
					<form action="insertOffre" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${Offre != null}">
            			Edit Offre
            		</c:if>
						<c:if test="${offre == null}">
            			Add New Offre
            		</c:if>
					</h2>
				</caption>

				<c:if test="${offre != null}">
					<input type="hidden" name="id" value="<c:out value='${offre.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>id admin </label> <input type="text"
						value="<c:out value='${offre.id_admin}' />" class="form-control"
						name="id_admin" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>description</label> <input type="text"
						value="<c:out value='${offre.description}' />" class="form-control"
						name="description" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>carton petit</label> <input type="text"
						value="<c:out value='${offre.carton_petit}' />" class="form-control"
						name="carton_petit">
				</fieldset>
				<fieldset class="form-group">
					<label>carton moyen</label> <input type="text"
						value="<c:out value='${offre.carton_moyen}' />" class="form-control"
						name="carton_moyen">
				</fieldset>
				<fieldset class="form-group">
					<label>carton grand</label> <input type="text"
						value="<c:out value='${offre.carton_grand}' />" class="form-control"
						name="carton_grand">
				</fieldset>
				<fieldset class="form-group">
					<label>reduction</label> <input type="text"
						value="<c:out value='${offre.reduction_offre}' />" class="form-control"
						name="reduction_offre" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>