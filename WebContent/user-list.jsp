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

<title>Admins</title>
<style type="text/css">
  	body {
	    background: #E0EEEE;/* linear-gradient(to right, #c04848, #480048); */
	    min-height: 100vh
	}
	
	table {
border: medium solid #6495ed!important;
border-collapse: collapse;
width: 50%;
}
th {
border: thin solid #6495ed!important;

background-color: #D0E3FA;
}
td {
border: thin solid #6495ed!important;

background-color: #ffffff;
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

                <div class="row">
                    <div class="wrap-inscription insc" style="margin-bottom: 100px;height:auto; width:1000px;margin-left:auto;margin-right:auto;">
                        <div class="container">
                            <div class="row">
                                    <span class="inscription-form-title" style="margin-left: 137px;margin-right:auto;margin-top: -20px;font-size:400%;text-align:center;">
                                        Liste des admins
                                    </span>	
			<div class="container text-left"  style="margin-left: -40px;">
<div class="container text-left" style="margin-left: -48px;" >
<br>
				<a href="<%=request.getContextPath()%>/new" class="btn btn-info" style="margin-left:40px">Ajouter un admin</a>
			</div>
			<br>
			<table class="table table-bordered" style="margin-left:-10px">
				<thead>
					<tr style="color:steelblue">
						<th>Nom</th>
						<th>Prenom</th>
						<th>Email</th>
						<th>Télephone</th>
						<th>Sexe</th>
						
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="user" items="${listUser}">

						<tr>
							<td><c:out value="${user.nom}" /></td>
							<td><c:out value="${user.prenom}" /></td>
							<td><c:out value="${user.email}" /></td>
							<td><c:out value="${user.tel}" /></td>
							<td><c:out value="${user.sexe}" /></td>
							
							<td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
	</div></div></div></div></div></div></section>
</body>
</html>