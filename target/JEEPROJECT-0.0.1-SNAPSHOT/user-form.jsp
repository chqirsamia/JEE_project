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
    <%@include file="./res/css/style.css" %>
</style>
<style type="text/css">
    <%@include file="./res/css/authentification.css" %>
</style>
   <style type="text/css">
    <%@include file="./res/css/bootstrap.min.css" %>
</style>
<!--  <link rel="stylesheet" href="http://localhost:8088/Plateforme/style.css"> -->
<!-- <link rel="stylesheet" href="./ressources/css/authentification.css"> -->

  <title>Admin</title>
  
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
  border-radius: 25px;
}
	
	.text-gray {
	    color: #aaa
	}
  </style>
</head>

<body>

<!-- Menu -->

<c:import url="navbar-admin.jsp"/>

	
	<div class="container-inscription">
		<div class="container">

                
                    <div class="wrap-inscription insc" style="margin-bottom: 100px;height:auto; width:700px;margin-left:auto;margin-right:auto;">
                        <div class="container" style="margin-left:-50px">
                            
                                    
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
<span class="inscription-form-title" style="margin-left: auto;margin-right:auto;margin-top: -20px;font-size:60px;text-align:center;">
							Modifier Admin</span>            		</c:if>
						<c:if test="${user == null}">
            			<span class="inscription-form-title" style="margin-left: auto;margin-right:auto;margin-top: -20px;font-size:60px;text-align:center;">
							Ajouter Admin</span>
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
					<label>Prénom</label> <input type="text"
						value="<c:out value='${user.prenom}' />" class="form-control"
						name="prenom">
				</fieldset>

				<fieldset class="form-group">
					<label>Email</label> <input type="email" 
						value="<c:out value='${user.email}' />" class="form-control"
						name="email">
				</fieldset>
				<fieldset class="form-group">
					<label>Téléphone</label> <input type="tel" pattern="[0-9]{10}"
						value="<c:out value='${user.tel}' />" class="form-control"
						name="tel">
				</fieldset>
				<fieldset class="form-group">
					<label>Sexe</label> <select class="form-control" name="sexe">
					<option value="H" <c:if test="${user.sexe == 'H'}"> selected </c:if>>Masculin</option>
						  <option value="F" <c:if test="${user.sexe == 'F'}"> selected </c:if>>Féminin</option>
						  
						</select>
				</fieldset>
				<fieldset class="form-group">
					<label>Mot de passe</label> <input type="text" class="form-control"
						name="password">
				</fieldset>
				

				<button type="submit" class="btn btn-info" style="margin-left:200px">Save</button>
				</form>
			</div>
		</div>
		</div>
		</div>
	</div>
</body>
</html>