package pl.markowski.veganImperium.storage;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import pl.markowski.veganImperium.filter.FilterAbstract;
import pl.markowski.veganImperium.filter.FilterChecker;
import pl.markowski.veganImperium.filter.FilterFactory;
import pl.markowski.veganImperium.logic.ListBuilder;

@Component
public class ProductProvider {

	@Autowired
	ProductRepository productRepository;
	
	public void updateAll(MultipartFile file) throws IOException {
		List<Product> temp = new ListBuilder().withFile(file).build();
		productRepository.deleteAll();
		productRepository.save(temp);
	}
	public List<Product> getProductsList(){
		return (List<Product>) productRepository.findAll();
	}
	
	public List<Product> getProductsList(Map<String, String> filtersMap){
		return getProductsList(filtersMap, getProductsList());
	}
	
	public List<Product> getProductsList(FilterAbstract<?> filter){
		return getProductsList(filter, getProductsList());
	}
	
	private List<Product> getProductsList(Map<String, String> filtersMap, List<Product> products) {
		List<Product> filteredProducts = null;
		List<FilterAbstract<?>> filtersList = new FilterFactory().getFiltersList(filtersMap);
		filteredProducts = products.stream().filter(p -> {
			return new FilterChecker().checkProduct(p, filtersList);
		}).collect(Collectors.toList());

		return filteredProducts;
	}
	
	private List<Product> getProductsList(FilterAbstract<?> filter, List<Product> products) {
		List<Product> filteredProducts = null;
		filteredProducts = products.stream().filter(p -> {
			return filter.check(p);
		}).collect(Collectors.toList());
		return filteredProducts;
	}
	
}
