<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/veganImperiumStyle.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
<title>Użytkownicy</title>
</head>

<body>
<jsp:include page="navbar.jsp" />
	<div class="container">
		<c:if test="${not empty message}">
			<div class="alert alert-info">
				<strong>Info! </strong>${message}
			</div>
		</c:if>
		
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger">
				<strong>Niepowodzenie! </strong>${errorMessage}
			</div>
		</c:if>
		
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Login</th>
					<th>Hasło</th>
					<th>Rola</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td></td>
					<td>Nowy użytkownik</td>
					<td></td>
					<td></td>
					<spring:url value="/users/${user.id}/delete" var="deleteUrl" />
					<td><button type="button" class="btn btn-primary"
							data-toggle="modal" data-target="#newUserModal">Dodaj</button></td>
				</tr>

				<c:forEach items="${userList}" var="user" varStatus="status">
					<tr>
						<td>${user.id}</td>
						<td>${user.username}</td>
						<td>******</td>
						<td>${user.role}</td>
						<td>
							<button type="button" class="btn btn-primary"
								data-toggle="modal" data-target="#editUserModal"
								data-userid="${user.id}" data-username="${user.username}"
								data-userpass="${user.password}" data-userrole="${user.role}">Edytuj
							</button>
						</td>
						<td>
							<form action="users/${user.id}/delete" method="POST">
								<button class="btn btn-danger" type="submit">Usuń</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="modal fade" id="editUserModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">Edytuj
							użytkownika</h4>
					</div>
					<div class="modal-body">
						<form:form method="post" action="editUser"
							modelAttribute="userForm">
							<div class="form-group">
								<spring:bind path="id">
									<form:input type="hidden" path="id" id="id"></form:input>
								</spring:bind>
								<spring:bind path="username">
									<label for="username" class="control-label">Login:</label>
									<form:input type="text" class="form-control" path="username"
										placeholder="Username" autofocus="true" id="username"></form:input>
									<form:errors path="username"></form:errors>
								</spring:bind>

								<spring:bind path="password">
									<form:input type="hidden" path="password"
										placeholder="Password" class="form-control" id="password"></form:input>
									<form:errors path="password"></form:errors>
								</spring:bind>

								<spring:bind path="role">
									<label for="role" class="control-label">Rola:</label>
									<form:select path="role" class="form-control" id="role">
										<form:option value="ADMIN" label="ADMIN" />
										<form:option value="MODERATOR" label="MODERATOR" />
									</form:select>
									<form:errors path="role"></form:errors>
								</spring:bind>
								<button type="submit" class="btn btn-primary">Edytuj</button>
							</div>
						</form:form>
						<form:form method="post" action="editUserPassword"
							modelAttribute="userPasswordForm">
							<div class="form-group">
								<h2>Zmień hasło</h2>
								<spring:bind path="id">
									<form:input type="hidden" path="id" id="editpassid"></form:input>
								</spring:bind>
								<spring:bind path="username">
									<form:input class="form-control" path="username"
										placeholder="Username" type="hidden" id="editpassusername"></form:input>
									<form:errors path="username"></form:errors>
								</spring:bind>

								<spring:bind path="password">
									<label for="editpassword" class="control-label">Hasło:</label>
									<form:input type="password" path="password"
										placeholder="Password" class="form-control"
										id="editpasspassword"></form:input>
									<form:errors path="password"></form:errors>
								</spring:bind>

								<spring:bind path="role">
									<form:input type="hidden" path="role" class="form-control"
										id="editpassrole"></form:input>
									<form:errors path="role"></form:errors>
								</spring:bind>
								<button type="submit" class="btn btn-primary">Zmień
									hasło</button>
							</div>
						</form:form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Zamknij</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="newUserModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">Dodaj
							użytkownika</h4>
					</div>
					<div class="modal-body">
						<form:form method="post" action="newUser"
							modelAttribute="newUserForm">
							<div class="form-group">
								<spring:bind path="username">
									<label for="new_username" class="control-label">Login:</label>
									<form:input type="text" class="form-control" path="username"
										placeholder="Username" autofocus="true" id="new_username"></form:input>
									<form:errors path="username"></form:errors>
								</spring:bind>

								<spring:bind path="password">
									<label for="new_password" class="control-label">Hasło:</label>
									<form:input type="password" path="password"
										placeholder="Password" class="form-control" id="new_password"></form:input>
									<form:errors path="password"></form:errors>
								</spring:bind>

								<spring:bind path="role">
									<label for="new_role" class="control-label">Rola:</label>
									<form:select path="role" class="form-control" id="new_role">
										<form:option value="ADMIN" label="ADMIN" />
										<form:option value="MODERATOR" label="MODERATOR" />
									</form:select>
									<form:errors path="role"></form:errors>
								</spring:bind>
								<button type="submit" class="btn btn-primary">Dodaj</button>
							</div>
						</form:form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Zamknij</button>
					</div>
				</div>
			</div>
		</div>
	</div>



	<script>
		$('#editUserModal').on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget) // Button that triggered the modal
			var userId = button.data('userid') // Extract info from data-* attributes
			var userName = button.data('username')
			var userRole = button.data('userrole')
			var userPass = button.data('userpass')
			// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
			// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
			var modal = $(this)
			modal.find('.modal-title').text('Edytuj użytkownika: ' + userName)
			modal.find('.modal-body #username').val(userName)
			modal.find('.modal-body #id').val(userId)
			modal.find('.modal-body #password').val(userPass)
			modal.find('.modal-body #role').val(userRole)

			modal.find('.modal-body #editpassusername').val(userName)
			modal.find('.modal-body #editpassid').val(userId)
			modal.find('.modal-body #editpassrole').val(userRole)
		})
	</script>

</body>
</html>