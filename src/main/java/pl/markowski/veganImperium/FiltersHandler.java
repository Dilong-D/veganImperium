package pl.markowski.veganImperium;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import pl.markowski.veganImperium.filter.FilterAbstract;
import pl.markowski.veganImperium.filter.FilterChecker;
import pl.markowski.veganImperium.filter.FilterFactory;
import pl.markowski.veganImperium.storage.Product;

@Component
public class FiltersHandler {

	
	public List<Product> filterProductsList(Map<String, String> filtersMap, List<Product> products) {
		List<Product> filteredProducts = null;
		List<FilterAbstract<?>> filtersList = new FilterFactory().getFiltersList(filtersMap);
		filteredProducts = products.stream().filter(p -> {
			return new FilterChecker().checkProduct(p, filtersList);
		}).collect(Collectors.toList());

		return filteredProducts;
	}
}
