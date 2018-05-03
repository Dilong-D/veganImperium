package pl.markowski.veganImperium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VeganImperiumAplication {

//	@GetMapping("/products")
//	String uploadDataFilter(Model model, @RequestParam Map<String, String> queryMap) {
//		List<Product> productList = (List<Product>) productRepository.findAll();
//		List<Product> filteredList = filtersHandler.filterProductsList(queryMap, productList);
//		
//		List<ProductView> productViewList = filteredList.stream().map(p ->{return new ProductView(p);}).collect(Collectors.toList());
//		model.addAttribute("list",productViewList);
//		model.addAllAttributes(queryMap);
//		return "index";
//	}
//	@GetMapping("/products")
//	String uploadDataFilter(Model model, @RequestParam Map<String, String> queryMap) {
//		
//		List<Product> filteredList = productProvider.getProductsList(queryMap);
//		
//		List<ProductView> productViewList = filteredList.stream().map(p ->{return new ProductView(p);}).collect(Collectors.toList());
//		model.addAttribute("list",productViewList);
//		model.addAllAttributes(queryMap);
//		return "index";
//	}

//	@GetMapping("/uploadData")
//	String uploadData() {
//		return "uploadForm";
//	}
//
//	@PostMapping("/uploadData")
//	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
//			throws IOException {
//		redirectAttributes.addFlashAttribute("message",
//				"You successfully uploaded " + file.getOriginalFilename() + "!");
//		productProvider.updateAll(file);
//
//		return "redirect:/uploadData";
//	}

	public static void main(String[] args) {
		SpringApplication.run(VeganImperiumAplication.class, args);
	}
}
