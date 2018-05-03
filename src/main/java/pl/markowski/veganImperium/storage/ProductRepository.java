package pl.markowski.veganImperium.storage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends CrudRepository<Product, Long>{

	Product findByVegan(State vegan);
	Product findByVegetarian(State vegetarian);
	Product findByPalmOil(State palmOil);
	Product findByBarcode(int barcode);

	@Transactional
	void deleteByBarcode(int barcode);

}
