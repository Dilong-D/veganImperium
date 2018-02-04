package pl.markowski.veganImperium.storage;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductProvider {

	@Autowired
	ProductRepository productRepository;
	
	
	
	public void updateAll(InputStream all) {
		
	}
	
}
