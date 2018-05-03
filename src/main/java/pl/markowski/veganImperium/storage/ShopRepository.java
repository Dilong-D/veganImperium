package pl.markowski.veganImperium.storage;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ShopRepository extends CrudRepository<Shop, Integer>{

	Shop findById(int id);
	
	@Query("select s.name from Shop s where s.id = :id")
	String findByIdReturnName(@Param("id") int id);
	
}
