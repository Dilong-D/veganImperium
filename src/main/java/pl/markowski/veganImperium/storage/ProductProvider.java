package pl.markowski.veganImperium.storage;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.markowski.veganImperium.logic.FiltersHandler;

@Component
public class ProductProvider {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	FiltersHandler filtersHandler;
	
	public List<Product> getProductList(Map<String, String> filtersMap){
		List<Product> listProduct = (List<Product>)productRepository.findAll();
		return filtersHandler.filterProductsList(filtersMap, listProduct);
	}
	
	public void updateAll(InputStream all) {
		
	}
	
}
