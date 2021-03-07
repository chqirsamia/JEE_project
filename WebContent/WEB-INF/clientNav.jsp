<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

<i class="fas fa-box-open fa-2x" style="color:white"></i>
  <a class="navbar-brand" style="font-family:cursive;font-size:30px;height:200%" href="Acceuil.jsp">HSA</a>
  
  <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item "><a></a></li>
      <li class="nav-item ">
        <a class="nav-link" style="font-size:20px" href="infoclient">Profile <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item "><a></a></li>
      <li class="nav-item ">
        <a class="nav-link" style="font-size:20px" href="offres-clt">Offres <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item "><a></a></li>
      <li class="nav-item ">
        <a class="nav-link" style="font-size:20px" href="histoclient">Historique <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item "><a></a></li>

      <li class="nav-item ">
        <a class="nav-link" style="font-size:20px" href="demandeclient">Demande <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item "><a></a></li>
      <li class="nav-item ">
        <a class="nav-link" style="font-size:20px" href="<c:url value='deconnexion'/>">Déconnexion <span class="sr-only">(current)</span></a>
      </li>
    </ul>
  </div>

</nav>