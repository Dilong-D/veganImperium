<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="./">Wegańskie imperium</a>
    </div>
    <c:if test="${not empty currentUser}">
	    <ul class="nav navbar-nav">
	      <li><a href="products">Produkty</a></li>
	      <c:if test="${currentUser.role eq 'ADMIN'}">
	      	<li><a href="users">Użytkownicy</a></li>
	      </c:if>
	    </ul>
	    <ul class="nav navbar-nav navbar-right">
	      <li><p class="navbar-text"><span class="glyphicon glyphicon-user"></span> ${currentUser.username}: ${currentUser.role}</p></li>
	      <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span>Wyloguj</a></li>
	    </ul>
    </c:if>
    <c:if test="${empty currentUser}">
    <ul class="nav navbar-nav navbar-right">
      <li><a href="login"><span class="glyphicon glyphicon-log-in"></span> Zaloguj</a></li>
    </ul>
    </c:if>
  </div>
</nav>