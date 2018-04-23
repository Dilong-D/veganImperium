package pl.markowski.veganImperium.controler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.markowski.veganImperium.model.AjaxProductResponseBody;
import pl.markowski.veganImperium.storage.Product;
import pl.markowski.veganImperium.storage.ProductProvider;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductProvider productProvider;
	
	@PostMapping
	public ResponseEntity<?> getProductsAjaxRequest(@RequestParam Map<String, String> queryMap){
		AjaxProductResponseBody result = new AjaxProductResponseBody();
		List<Product> listProduct = (List<Product>)productProvider.getProductList(queryMap);
		result.setResult(listProduct);
		result.setMsg("Znaleziono " + listProduct.size() + " produkty");
		return ResponseEntity.ok(result);
	}
	
}
