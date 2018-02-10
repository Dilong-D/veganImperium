package pl.markowski.veganImperium.filter;

import pl.markowski.veganImperium.storage.Product;
import pl.markowski.veganImperium.storage.State;

public class VeganFilter extends FilterAbstract<State>{

	public VeganFilter(State v) {
		super(v);
	}

	@Override
	public boolean check(Product p) {
		if(p.getVegan().equals(value))
			return true;
		return false;
	}

}
