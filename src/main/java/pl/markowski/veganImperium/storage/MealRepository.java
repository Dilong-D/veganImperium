package pl.markowski.veganImperium.storage;

import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<Meal, Integer>{

	Meal findById(int id);
}
