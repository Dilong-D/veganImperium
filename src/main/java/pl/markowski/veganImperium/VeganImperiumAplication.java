package pl.markowski.veganImperium;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import pl.markowski.veganImperium.model.ProductView;
import pl.markowski.veganImperium.storage.Product;
import pl.markowski.veganImperium.storage.ProductProvider;

@SpringBootApplication
@Controller
@RequestMapping("/")
public class VeganImperiumAplication {

	@Autowired
	ProductProvider productProvider; 

	@GetMapping
	String home(Model model) {
		return "index";
	}

	@GetMapping("/products")
	String uploadDataFilter(Model model, @RequestParam Map<String, String> queryMap) {
		
		List<Product> filteredList = productProvider.getProductsList(queryMap);
		
		List<ProductView> productViewList = filteredList.stream().map(p ->{return new ProductView(p);}).collect(Collectors.toList());
		model.addAttribute("list",productViewList);
		model.addAllAttributes(queryMap);
		return "index";
	}

	@GetMapping("/uploadData")
	String uploadData() {
		return "uploadForm";
	}

	@PostMapping("/uploadData")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws IOException {
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		productProvider.updateAll(file);

		return "redirect:/uploadData";
	}

	public static void main(String[] args) {
		SpringApplication.run(VeganImperiumAplication.class, args);
	}
}
