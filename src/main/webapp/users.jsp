<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html>
<head>
    <title>Użytkownicy</title>
</head>

<body>
	<form:form method="POST" action="newUser" modelAttribute="userForm">
		<h2>Nowy użytkownik</h2>
		<spring:bind path="username">
			<form:input type="text" path="username" placeholder="Username"
				autofocus="true"></form:input>
			<form:errors path="username"></form:errors>
		</spring:bind>

		<spring:bind path="password">
			<form:input type="password" path="password" placeholder="Password"></form:input>
			<form:errors path="password"></form:errors>
		</spring:bind>

		<spring:bind path="role">
			<form:select path="role">
				<form:option value="ADMIN" label="ADMIN" />
				<form:option value="MODERATOR" label="MODERATOR" />
			</form:select>
			<form:errors path="role"></form:errors>
		</spring:bind>
		<button type="submit">Submit</button>
	</form:form>
	
	<table>
	<tr>
		<th>Id</th>
		<th>Login</th>
		<th>Hasło</th>
		<th>Rola</th>
	</tr>
	
	<c:forEach items="${userList}" var="user" varStatus="status">
		<form:form method="post" action="editUser" modelAttribute="userForm">
		<tr>
			<td>
			<spring:bind path="username">
			<form:input type="hidden" path="id" value="${user.id}"></form:input>
			<form:input type="text" path="username" placeholder="${user.username}"
				autofocus="true"></form:input>
			<form:errors path="username"></form:errors>
		</spring:bind></td>
		<td><button type="submit">Submit</button></td>
		</tr>
		</form:form>
	</c:forEach>
	</table>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>