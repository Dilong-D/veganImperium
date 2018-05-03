<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<table class="table table-hover">
	<thead>
		<tr>
			<th>Nazwa</th>
			<th>Wegańki</th>
			<th>Wegetariański</th>
			<th>Olej palmowy</th>
			<th>Danie</th>
			<th>Posiłek</th>
			<th>Dostępność</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>Nowy produkt</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>
				<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#newProdModal">Dodaj</button>
			</td>
		</tr>
	
		<c:forEach items="${list}" var="product" varStatus="status">
			<tr>
				<td>${product.name}</td>
				<td>${product.vegan}</td>
				<td>${product.vegetarian}</td>
				<td>${product.palmOil}</td>
				<td>${product.kind}</td>
				<td>${product.meal}</td>
				<td>${product.shops}</td>

				<td>
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#editProdModal" data-barcode="${product.barcode}"
						data-name="${product.name}">Edytuj</button>
				</td>
				<td>
					<form action="product/${product.barcode}/delete" method="POST">
						<button class="btn btn-danger" type="submit">Usuń</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="modal fade" id="editProdModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">Edytuj produkt</h4>
			</div>
			<div class="modal-body">
				<form:form method="post" action="editProduct"
					modelAttribute="editProdForm">
					<div class="form-group">
						<spring:bind path="barcode">
							<label for="barcode" class="control-label">Kod kreskowy:</label>
							<form:input type="text" class="form-control" path="barcode"
								placeholder="Kod kreskowy" autofocus="true" id="barcode"></form:input>
							<form:errors path="barcode"></form:errors>
						</spring:bind>

						<spring:bind path="name">
							<label for="name" class="control-label">Nazwa</label>
							<form:input type="text" class="form-control" path="name"
								placeholder="Nazwa" autofocus="true" id="name"></form:input>
							<form:errors path="name"></form:errors>
						</spring:bind>

						<spring:bind path="vegan">
							<label for="vegan" class="control-label">Wegański:</label>
							<form:select path="vegan" class="form-control" id="vegan">
								<form:option value="yes" label="TAK" />
								<form:option value="maybe" label="PRAWDOPODOBNIE TAK" />
								<form:option value="no" label="NIE" />
							</form:select>
							<form:errors path="vegan"></form:errors>
						</spring:bind>

						<spring:bind path="vegetarian">
							<label for="vegetarian" class="control-label">Wegetariańki:</label>
							<form:select path="vegetarian" class="form-control"
								id="vegetarian">
								<form:option value="yes" label="TAK" />
								<form:option value="maybe" label="PRAWDOPODOBNIE TAK" />
								<form:option value="no" label="NIE" />
							</form:select>
							<form:errors path="vegetarian"></form:errors>
						</spring:bind>

						<spring:bind path="palmOil">
							<label for="palmOil" class="control-label">Olej plamowy:</label>
							<form:select path="palmOil" class="form-control" id="palmOil">
								<form:option value="yes" label="TAK" />
								<form:option value="maybe" label="PRAWDOPODOBNIE TAK" />
								<form:option value="no" label="NIE" />
							</form:select>
							<form:errors path="palmOil"></form:errors>
						</spring:bind>

						<spring:bind path="kindId">
							<label for="kindId" class="control-label">Rodzaj:</label>
							<form:select path="kindId" class="form-control" id="kindId">
								<c:forEach items="${kindList}" var="k" varStatus="status">
									<form:option value="${k.id}" label="${k.name}" />
								</c:forEach>
							</form:select>
							<form:errors path="kindId"></form:errors>
						</spring:bind>

						<spring:bind path="mealId">
							<label for="mealId" class="control-label">Danie:</label>
							<form:select path="mealId" class="form-control" id="mealId">
								<c:forEach items="${mealList}" var="m" varStatus="status">
									<form:option value="${m.id}" label="${m.name}" />
								</c:forEach>
							</form:select>
							<form:errors path="mealId"></form:errors>
						</spring:bind>

						<button type="submit" class="btn btn-primary">Edytuj</button>
					</div>
				</form:form>
				<div id="avalibilityDiv">
					<h4>Dostępny w sklepach</h4>
					<form:form method="post" action="newAvalibility"
						modelAttribute="avalProdForm">
						<div class="form-group">
							<spring:bind path="productId">
								<form:input type="hidden" class="form-control" path="productId"
									id="productId"></form:input>
							</spring:bind>

							<spring:bind path="shopId">
								<label for="shopId" class="control-label">Sklep:</label>
								<form:select path="shopId" class="form-control" id="shopId">
									<c:forEach items="${shopList}" var="shop" varStatus="status">
										<form:option value="${shop.id}" label="${shop.name}" />
									</c:forEach>
								</form:select>
								<form:errors path="shopId"></form:errors>
							</spring:bind>

							<button type="submit" class="btn btn-primary">Dodaj
								sklep</button>
						</div>
					</form:form>

				</div>


			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Zamknij</button>
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="newProdModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">Dodaj produkt</h4>
			</div>
			<div class="modal-body">
				<form:form method="post" action="newProduct"
					modelAttribute="newProdForm">
					<div class="form-group">
						<spring:bind path="barcode">
							<label for="newbarcode" class="control-label">Kod
								kreskowy:</label>
							<form:input type="text" class="form-control" path="barcode"
								placeholder="Kod kreskowy" autofocus="true" id="newbarcode"></form:input>
							<form:errors path="barcode"></form:errors>
						</spring:bind>

						<spring:bind path="name">
							<label for="newname" class="control-label">Nazwa</label>
							<form:input type="text" class="form-control" path="name"
								placeholder="Nazwa" autofocus="true" id="newname"></form:input>
							<form:errors path="name"></form:errors>
						</spring:bind>

						<spring:bind path="vegan">
							<label for="newvegan" class="control-label">Wegański:</label>
							<form:select path="vegan" class="form-control" id="newvegan">
								<form:option value="yes" label="TAK" />
								<form:option value="maybe" label="PRAWDOPODOBNIE TAK" />
								<form:option value="no" label="NIE" />
							</form:select>
							<form:errors path="vegan"></form:errors>
						</spring:bind>

						<spring:bind path="vegetarian">
							<label for="newvegetarian" class="control-label">Wegetariańki:</label>
							<form:select path="vegetarian" class="form-control"
								id="newvegetarian">
								<form:option value="yes" label="TAK" />
								<form:option value="maybe" label="PRAWDOPODOBNIE TAK" />
								<form:option value="no" label="NIE" />
							</form:select>
							<form:errors path="vegetarian"></form:errors>
						</spring:bind>

						<spring:bind path="palmOil">
							<label for="newpalmOil" class="control-label">Olej
								plamowy:</label>
							<form:select path="palmOil" class="form-control" id="newpalmOil">
								<form:option value="yes" label="TAK" />
								<form:option value="maybe" label="PRAWDOPODOBNIE TAK" />
								<form:option value="no" label="NIE" />
							</form:select>
							<form:errors path="palmOil"></form:errors>
						</spring:bind>

						<spring:bind path="kindId">
							<label for="newkindId" class="control-label">Rodzaj:</label>
							<form:select path="kindId" class="form-control" id="newkindId">
								<c:forEach items="${kindList}" var="k" varStatus="status">
									<form:option value="${k.id}" label="${k.name}" />
								</c:forEach>
							</form:select>
							<form:errors path="kindId"></form:errors>
						</spring:bind>

						<spring:bind path="mealId">
							<label for="newmealId" class="control-label">Danie:</label>
							<form:select path="mealId" class="form-control" id="newmealId">
								<c:forEach items="${mealList}" var="m" varStatus="status">
									<form:option value="${m.id}" label="${m.name}" />
								</c:forEach>
							</form:select>
							<form:errors path="mealId"></form:errors>
						</spring:bind>

						<button type="submit" class="btn btn-primary">Edytuj</button>
					</div>
				</form:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Zamknij</button>
			</div>
		</div>
	</div>
</div>