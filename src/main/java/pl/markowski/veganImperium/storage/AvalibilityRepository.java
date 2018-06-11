package pl.markowski.veganImperium.storage;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AvalibilityRepository extends CrudRepository<Avalibility, Integer> {

	Avalibility findById(int id);
	
	List<Avalibility> findByProductId(int productId);

	@Transactional
	void deleteById(int id);
	
	@Query("select a from Avalibility a where a.productId = :barcode and a.shopId = :shopId")
	Avalibility findByProductIdAndShopId(@Param("barcode") int barcode, @Param("shopId") int shopId);
	
	@Query("select a.shopId from Avalibility a where a.productId = :barcode")
	List<Integer> findByProductIdReturnListWithShopId(@Param("barcode") int barcode);
}
