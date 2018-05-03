<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

<title>Wegańskie imperium</title>
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

		<form method="get" action="products">
			<div class="form-group row">
				<div class="col-sm-6">
					<input type="text" class="form-control" name="name"
						placeholder="Nazwa lub kod kreskowy" id="name"></input>
				</div>
				<div class="col-sm-2">
					<div class="dropdown">
						<button class="btn btn-default dropdown-toggle" type="button"
							data-toggle="dropdown">
							Diety <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><input type="checkbox" name="vegan" value="yes" />
								Wegańska</li>
							<li><input type="checkbox" name="vegetarian" value="yes" />
								Wegetariańska</li>
							<li><input type="checkbox" name="palmOil" value="yes" />
								Bez oleju palmowego</li>
						</ul>
					</div>
				</div>
				<div class="col-sm-2">
					<div class="dropdown">
						<button class="btn btn-default dropdown-toggle" type="button"
							data-toggle="dropdown">
							Sklepy <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<c:forEach items="${shopsList}" var="shop" varStatus="status">
								<li><input type="checkbox" name="shop-${shop.id}" />
									${shop.name}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="col-sm-2">
					<button type="submit" class="btn btn-primary">Szukaj</button>
				</div>
			</div>
		</form>

		<c:if test="${empty currentUser}">
			<jsp:include page="user_prod_table.jsp" />
		</c:if>

		<c:if test="${not empty currentUser}">
			<jsp:include page="mod_prod_table.jsp" />
		</c:if>
	</div>



	<script>
		$('#editProdModal')
				.on(
						'show.bs.modal',
						function(event) {
							var button = $(event.relatedTarget);
							var barcode = button.data('barcode');
							var name = button.data('name');
							var modal = $(this)
							var post_url = "product/" + barcode;
							var request_method = "post";

							$
									.ajax({
										url : post_url,
										type : request_method,
									})
									.done(
											function(response) {
												var product = response.result;
												modal.find('.modal-body #barcode')
														.val(product.barcode);
												modal.find('.modal-body #productId')
														.val(product.barcode);
												modal.find('.modal-body #name')
														.val(product.name);
												modal.find('.modal-body #vegan')
														.val(product.vegan);
												modal.find('.modal-body #vegetarian')
														.val(product.vegetarian);
												modal.find('.modal-body #palmOil')
														.val(product.palmOil);
												modal.find('.modal-body #kindId')
														.val(product.kindId);
												modal.find('.modal-body #mealId')
														.val(product.mealId);
												var avalibility = response.avalibilityResult;
												avalibility
														.forEach(function(aval) {
															$('#avalibilityDiv')
																	.append(
																			'<form action="avalibility/'+aval.id+'/delete" method="POST"><button class="btn btn-danger" type="submit">Usuń '
																					+ aval.shopId
																					+ '</button></form>');
														});

											});
						})
		/* $(function() {
			$("#queryField").autocomplete({
				source : 'autocomplete',
				minLenght : 1,
				select : function(event, ui) {
					document.getElementById('queryField').value = ui.item.value;
					document.getElementById('searchForm').submit();
				}
			});
		}); */
	</script>

</body>
</html>