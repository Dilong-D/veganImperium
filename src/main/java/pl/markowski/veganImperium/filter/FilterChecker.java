package pl.markowski.veganImperium.filter;

import java.util.List;
import java.util.stream.Stream;

import pl.markowski.veganImperium.storage.Product;

public class FilterChecker {
	private boolean flag = true;
	
	public boolean checkProduct(Product product, List<FilterAbstract<?>> filters) {
		
		Stream<FilterAbstract<?>> filterStream = filters.stream(); 
		
		filterStream.forEach(f -> {
			 if(!f.check(product))
				 flag = false;
				 filterStream.close();
		});
		return flag;
	}
}
