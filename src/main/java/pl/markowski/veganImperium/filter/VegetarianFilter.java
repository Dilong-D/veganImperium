package pl.markowski.veganImperium.filter;

import pl.markowski.veganImperium.storage.Product;
import pl.markowski.veganImperium.storage.State;

public class VegetarianFilter extends FilterAbstract<State>{

	public VegetarianFilter(State v) {
		super(v);
	}

	@Override
	public boolean check(Product p) {
		if(p.getVegetarian().equals(value))
			return true;
		return false;
	}

}
