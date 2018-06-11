package pl.markowski.veganImperium.controler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.markowski.veganImperium.logic.AvalibilityService;
import pl.markowski.veganImperium.logic.AvalibilityValidator;
import pl.markowski.veganImperium.logic.EditProductValidator;
import pl.markowski.veganImperium.logic.ProductService;
import pl.markowski.veganImperium.logic.ProductValidator;
import pl.markowski.veganImperium.logic.ShopService;
import pl.markowski.veganImperium.model.AjaxProductResponseBody;
import pl.markowski.veganImperium.model.ProductView;
import pl.markowski.veganImperium.security.SecurityService;
import pl.markowski.veganImperium.storage.Avalibility;
import pl.markowski.veganImperium.storage.AvalibilityRepository;
import pl.markowski.veganImperium.storage.KindRepository;
import pl.markowski.veganImperium.storage.MealRepository;
import pl.markowski.veganImperium.storage.Product;
import pl.markowski.veganImperium.storage.ShopRepository;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	ShopService shopService;

	@Autowired
	SecurityService securityService;

	@Autowired
	KindRepository kindRepository;

	@Autowired
	MealRepository mealRepository;

	@Autowired
	ProductValidator productValidator;

	@Autowired
	EditProductValidator editProductValidator;

	@Autowired
	AvalibilityRepository avalibilityRepository;

	@Autowired
	AvalibilityService avalibilityService;
	
	@Autowired
	AvalibilityValidator avalibilityValidator; 
	
	@Autowired
	ShopRepository shopRepository;
	
	@GetMapping("/")
	public String home(Model model, @RequestParam Map<String, String> queryMap) {
		return getProducts(model, queryMap);
	}

	/*
	 * @PostMapping("/products") public ResponseEntity<?>
	 * getProductsAjaxRequest(@RequestParam Map<String, String> queryMap) {
	 * AjaxProductListResponseBody result = new AjaxProductListResponseBody();
	 * List<ProductView> listProduct = productService.getProductViewList(queryMap);
	 * result.setResult(listProduct); result.setMsg("Founded " + listProduct.size()
	 * + " products."); return ResponseEntity.ok(result); }
	 */

	@PostMapping("/product/{barcode}")
	public ResponseEntity<?> getProductAjaxRequest(@PathVariable("barcode") int barcode) {
		AjaxProductResponseBody result = new AjaxProductResponseBody();
		Product product = productService.getProductByBarcode(barcode);
		result.setResult(product);
		result.setAvalibilityViewResults(avalibilityService.getAvalibilityView(barcode));
		result.setMsg("Founded product " + product.getName() + ".");
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/product/{barcode}/delete")
	public String deleteProduct(@PathVariable("barcode") int barcode, RedirectAttributes redir) {
		String product = productService.getProductByBarcode(barcode).getName();
    	productService.deleteByBarcode(barcode);
    	redir.addFlashAttribute("message","Product "+ product +" został usunięty.");
        return "redirect:/products";
	}

	@GetMapping("/products")
	public String getProducts(Model model, @RequestParam Map<String, String> queryMap) {
		model.addAttribute("currentUser", securityService.findLoggedInUsername());
		model.addAttribute("shopsList", shopService.getAllShops());
		List<ProductView> filteredList = productService.getProductViewList(queryMap);
		model.addAttribute("list", filteredList);
		model.addAttribute("editProdForm", new Product());
		model.addAllAttributes(queryMap);
		model.addAttribute("shopList", shopService.getAllShops());
		model.addAttribute("kindList", kindRepository.findAll());
		model.addAttribute("mealList", mealRepository.findAll());
		model.addAttribute("avalProdForm", new Avalibility());
		model.addAttribute("newProdForm", new Product());
		
		return "products";
	}

	@PostMapping("/editProduct")
	public String editProduct(Model model, @ModelAttribute("editProdForm") Product prod, BindingResult bindingResult,
			RedirectAttributes redir) {
		editProductValidator.validate(prod, bindingResult);

		if (bindingResult.hasErrors()) {
			String errorMsq = bindingResult.getAllErrors().stream().map(n -> n.getDefaultMessage())
					.collect(Collectors.joining(","));
			redir.addFlashAttribute("errorMessage", errorMsq);
			return "redirect:/products";
		}
		productService.save(prod);
		redir.addFlashAttribute("message", "Produkt " + prod.getName() + " zmodyfikowany.");

		return "redirect:/products";
	}

	@PostMapping("/newProduct")
	public String nweProduct(Model model, @ModelAttribute("newProdForm") Product prod, BindingResult bindingResult,
			RedirectAttributes redir) {
		productValidator.validate(prod, bindingResult);

		if (bindingResult.hasErrors()) {
			String errorMsq = bindingResult.getAllErrors().stream().map(n -> n.getDefaultMessage())
					.collect(Collectors.joining(","));
			redir.addFlashAttribute("errorMessage", errorMsq);
			return "redirect:/products";
		}
		productService.save(prod);
		redir.addFlashAttribute("message", "Produkt " + prod.getName() + " dodany.");

		return "redirect:/products";
	}

	@PostMapping("/newAvalibility")
	public String newAvalibility(Model model, @ModelAttribute("avalProdForm") Avalibility aval, BindingResult bindingResult,RedirectAttributes redir){
		avalibilityValidator.validate(aval, bindingResult);

		if (bindingResult.hasErrors()) {
			String errorMsq = bindingResult.getAllErrors().stream().map(n -> n.getDefaultMessage())
					.collect(Collectors.joining(","));
			redir.addFlashAttribute("errorMessage", errorMsq);
			return "redirect:/products";
		}
		String prodName = productService.getProductByBarcode(aval.getProductId()).getName();
		String shopName = shopRepository.findByIdReturnName(aval.getShopId());
		avalibilityRepository.save(aval);
		redir.addFlashAttribute("message", "Dostępność produktu "+ prodName +" w sklepie "+shopName+ " została dodana.");

		return "redirect:/products";
	}
	
	@PostMapping("/avalibility/{id}/delete")
	public String deleteAvalibility(@PathVariable("id") int id, RedirectAttributes redir) {
		Avalibility aval = avalibilityRepository.findById(id);
		String prodName = productService.getProductByBarcode(aval.getProductId()).getName();
		String shopName = shopRepository.findByIdReturnName(aval.getShopId());
		avalibilityRepository.deleteById(id);
    	redir.addFlashAttribute("message","Dostępność produktu "+ prodName +" w sklepie "+shopName+ " została usunięta.");
        return "redirect:/products";
	}
	
}
