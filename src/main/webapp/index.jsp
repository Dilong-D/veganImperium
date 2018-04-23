<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>Vegankie imperium</title>
    <script src="js/jquery-3.3.1.min.js"></script>
</head>
<body>
<h1>
    Vegankie imperium
</h1>

<form action="products" method="post" id="my_form">
    <label>Nazwa</label>
    <input type="text" name="name" />
    <label>Vegan</label>
    <input type="checkbox" name="vegan" />
    <label>Vege</label>
    <input type="checkbox" name="vegetarian" />
    <label>Olej palmowy</label>
    <input type="checkbox" name="palmOil" />
    
    <input type="submit" name="submit" value="Wyszukaj" />
</form>
<div id="find-results-msq"><!-- For server results --></div>
<div id="find-results-table"><!-- For server results --></div>

<script type="text/javascript">
	$("#my_form").submit(function(event){
    	event.preventDefault(); //prevent default action 
    	var post_url = $(this).attr("action"); //get form action url
    	var request_method = $(this).attr("method"); //get form GET/POST method
    	var form_data = $(this).serialize(); //Encode form elements for submission
    
    	$.ajax({
        	url : post_url,
        	type: request_method,
        	data : form_data
    	}).done(function(response){ //
        $("#find-results-msg").html(response.msg);
        $("#find-results-table table tr").remove(); 
        $("#find-results-table").append(populateTable(response.result));
    	
    	});
	});
	
	function populateTable(data) {
	    table = document.createElement('table');
	    for (i=0; i<data.length;i++){
	    	var row = document.createElement('tr');
			row.appendChild(document.createElement('td'));
			row.appendChild(document.createElement('td'));
			row.appendChild(document.createElement('td'));
			row.appendChild(document.createElement('td'));
			row.cells[0].appendChild(document.createTextNode(data[i].name));
			row.cells[1].appendChild(document.createTextNode(data[i].vegan));
			row.cells[2].appendChild(document.createTextNode(data[i].vegetarian));
			row.cells[3].appendChild(document.createTextNode(data[i].palmOil));
			table.appendChild(row);
	    }
	    return table;
	}
	
</script>

</body>
</html>