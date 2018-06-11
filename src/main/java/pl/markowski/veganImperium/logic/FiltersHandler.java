package pl.markowski.veganImperium.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import pl.markowski.veganImperium.storage.Product;
import pl.markowski.veganImperium.storage.State;

@Component
public class FiltersHandler {

	public List<Product> filterProductsList(Map<String, String> filtersMap, List<Product> products) {
		List<Product> filteredProducts = products;
		List<Integer> shopFilterList = new ArrayList<>();
		for (Map.Entry<String, String> entry : filtersMap.entrySet()) {
			if (entry.getKey().contains("shop") && entry.getValue().equals("on")) {
				shopFilterList.add(Integer.parseInt(entry.getKey().substring(5)));
			} else if (!entry.getKey().contains("shop"))
				filteredProducts = filter(entry.getKey(), entry.getValue(), filteredProducts);
		}
		return filteredProducts;
	}

	private List<Product> filter(String filterName, String filterVal, List<Product> products) {
		List<Product> filteredProducts = new ArrayList<>();
		switch (filterName) {
		case "name":
			products.forEach(p -> {
				if (p.getName().toLowerCase().contains(filterVal.toLowerCase())
						|| String.valueOf(p.getBarcode()).equals(filterVal))
					filteredProducts.add(p);
			});
			break;
		case "vegan":
			if (filterVal.equals(State.yes.toString()))
				products.forEach(p -> {
					if (p.getVegan().equals(State.yes))
						filteredProducts.add(p);
				});
			break;
		case "vegetarian":
			if (filterVal.equals(State.yes.toString()))
				products.forEach(p -> {
					if (p.getVegetarian().equals(State.yes))
						filteredProducts.add(p);
				});
			break;
		case "palmOil":
			if (filterVal.equals(State.yes.toString()))
				products.forEach(p -> {
					if (p.getPalmOil().equals(State.yes))
						filteredProducts.add(p);
				});
			break;
		case "shop":
			System.out.println(filterName);
			System.out.println(filterVal);
			return products;
			
		default:
			return products;
		}
		return filteredProducts;
	}
}
