package pl.markowski.veganImperium.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.markowski.veganImperium.model.ProductView;
import pl.markowski.veganImperium.storage.AvalibilityRepository;
import pl.markowski.veganImperium.storage.Kind;
import pl.markowski.veganImperium.storage.KindRepository;
import pl.markowski.veganImperium.storage.Meal;
import pl.markowski.veganImperium.storage.MealRepository;
import pl.markowski.veganImperium.storage.Product;
import pl.markowski.veganImperium.storage.ProductRepository;
import pl.markowski.veganImperium.storage.ShopRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	FiltersHandler filtersHandler;

	@Autowired
	KindRepository kindRepository;

	@Autowired
	MealRepository mealRepository;

	@Autowired
	AvalibilityRepository avalibilityRepository;

	@Autowired
	ShopRepository shopRepository;

	
	public void deleteByBarcode(int barcode) {
		productRepository.deleteByBarcode(barcode);
	}
	
	public Product getProductByBarcode(int barcode) {
		return productRepository.findByBarcode(barcode);
	}

	public List<Product> getProductList(Map<String, String> filtersMap) {
		List<Product> listProduct = (List<Product>) productRepository.findAll();
		return filtersHandler.filterProductsList(filtersMap, listProduct);
	}

	public List<ProductView> getProductViewList(Map<String, String> filtersMap) {

		List<Product> listProduct = getProductList(filtersMap);
		List<ProductView> listProductView = new ArrayList<>();

		for (Product p : listProduct) {
			Kind kind = kindRepository.findById(p.getKindId());
			Meal meal = mealRepository.findById(p.getMealId());
			List<String> shopList = getListOfShopsNames(p);
			listProductView.add(new ProductView(p, kind, meal, shopList));
		}
		return listProductView;
	}

	private List<String> getListOfShopsNames(Product product) {
		List<Integer> listShodId = avalibilityRepository.findByProductIdReturnListWithShopId(product.getBarcode());
		return listShodId.stream().map(i -> {
			return shopRepository.findByIdReturnName(i);
		}).collect(Collectors.toList());
	}

	public List<Product> getProductsList() {
		return (List<Product>) productRepository.findAll();
	}

	public List<Product> getProductsListByName(String query) {
		Map<String, String> hm = new HashMap<String, String>();
		hm.put("name", query);
		return getProductList(hm);
	}

	public void save(Product prod) {
		productRepository.save(prod);
	}

}
