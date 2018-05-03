package pl.markowski.veganImperium.storage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long>{
	User findById(int id);
	User findByUsername(String name);
	@Transactional
	void deleteById(int id);
}
