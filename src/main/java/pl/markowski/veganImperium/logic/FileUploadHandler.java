package pl.markowski.veganImperium.logic;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import pl.markowski.veganImperium.storage.Product;
import pl.markowski.veganImperium.storage.ProductRepository;

@Component
public class FileUploadHandler {
	
	@Autowired
	ProductRepository productRepository;
	
	public void updateDB(MultipartFile file) throws IOException {		
		List<Product> temp = new ListBuilder().withFile(file).build();
		productRepository.deleteAll();
		productRepository.save(temp);
	}
	
	
}
