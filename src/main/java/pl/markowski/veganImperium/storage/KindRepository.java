package pl.markowski.veganImperium.storage;

import org.springframework.data.repository.CrudRepository;

public interface KindRepository extends CrudRepository<Kind, Integer>{

	Kind findById(int id);
}
