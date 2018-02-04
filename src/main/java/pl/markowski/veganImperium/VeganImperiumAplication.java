package pl.markowski.veganImperium;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SpringBootApplication
@Controller
@RequestMapping("/")
public class VeganImperiumAplication {
	
	@Autowired
	FileUploadHandler fileUploadHandler;
	
	@GetMapping
	String home() {
		return "index";
	}
	
	@GetMapping("/uploadData")
	String uploadData() {
		return "uploadForm";
	}
	
	@PostMapping("/uploadData")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) throws IOException {
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        fileUploadHandler.updateDB(file);
        
        return "redirect:/uploadData";
    }
	
	public static void main(String[] args) {
		SpringApplication.run(VeganImperiumAplication.class, args);
	}
}
