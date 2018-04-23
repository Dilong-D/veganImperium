package pl.markowski.veganImperium;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.markowski.veganImperium.logic.FileUploadHandler;
import pl.markowski.veganImperium.logic.FiltersHandler;
import pl.markowski.veganImperium.storage.ProductRepository;

@SpringBootApplication
@Controller
@RequestMapping("/")
public class VeganImperiumAplication {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	FileUploadHandler fileUploadHandler;

	@Autowired
	FiltersHandler filtersHandler;

	@GetMapping
	String home(Model model) {
		return "index";
	}

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

	@GetMapping("/uploadData")
	String uploadData() {
		return "uploadForm";
	}

	@PostMapping("/uploadData")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws IOException {
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		fileUploadHandler.updateDB(file);

		return "redirect:/uploadData";
	}

	public static void main(String[] args) {
		SpringApplication.run(VeganImperiumAplication.class, args);
	}
}
