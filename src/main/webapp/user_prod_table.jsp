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
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="product" varStatus="status">
			<tr>
				<td>${product.name}</td>
				<td>${product.vegan}</td>
				<td>${product.vegetarian}</td>
				<td>${product.palmOil}</td>
				<td>${product.kind}</td>
				<td>${product.meal}</td>
				<td>${product.shops}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>