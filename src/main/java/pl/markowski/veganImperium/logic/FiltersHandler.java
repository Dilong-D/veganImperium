package pl.markowski.veganImperium.logic;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import pl.markowski.veganImperium.logic.filter.FilterAbstract;
import pl.markowski.veganImperium.logic.filter.FilterChecker;
import pl.markowski.veganImperium.logic.filter.FilterFactory;
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
