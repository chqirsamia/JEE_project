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

<link rel="stylesheet" href="http://localhost:8088/Plateforme/style.css">
<link rel="stylesheet" href="./ressources/css/authentification.css">

  <title>demandes</title>
  
  <style type="text/css">
  	body {
	    background: #E0EEEE;/* linear-gradient(to right, #c04848, #480048); */
	    min-height: 100vh
	}
	

	fieldset
{
  background-color:#B5DAE5;
  max-width:500px;
  padding:16px;	
}
	
	.text-gray {
	    color: #aaa
	}

  </style>
</head>

<body>

<!-- Menu -->

<c:import url="navbar-admin.jsp"/>

	
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${user != null}">
            			Modifier Admin
            		</c:if>
						<c:if test="${user == null}">
            			Ajouter Admin
            		</c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Nom </label> <input type="text"
						value="<c:out value='${user.nom}' />" class="form-control"
						name="nom" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Prenom</label> <input type="text"
						value="<c:out value='${user.prenom}' />" class="form-control"
						name="prenom">
				</fieldset>

				<fieldset class="form-group">
					<label>Email</label> <input type="text"
						value="<c:out value='${user.email}' />" class="form-control"
						name="email">
				</fieldset>
				<fieldset class="form-group">
					<label>telephone</label> <input type="text"
						value="<c:out value='${user.tel}' />" class="form-control"
						name="tel">
				</fieldset>
				<fieldset class="form-group">
					<label>sexe</label> <input type="text"
						value="<c:out value='${user.sexe}' />" class="form-control"
						name="sexe">
				</fieldset>
				<fieldset class="form-group">
					<label>password</label> <input type="text"
						value="<c:out value='${user.password}' />" class="form-control"
						name="password">
				</fieldset>
				

				<button type="submit" class="btn btn-info">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>