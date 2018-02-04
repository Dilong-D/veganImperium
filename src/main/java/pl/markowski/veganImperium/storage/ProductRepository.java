package pl.markowski.veganImperium.storage;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>{

	Product findByVegan(boolean vegan);
	Product findByVegetarian(boolean vegetarian);
	Product findByPalmOil(boolean palmOil);
	Product findByBarcode(int barcode);
}
