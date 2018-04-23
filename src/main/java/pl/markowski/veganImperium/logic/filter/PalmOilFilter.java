package pl.markowski.veganImperium.logic.filter;

import pl.markowski.veganImperium.storage.Product;
import pl.markowski.veganImperium.storage.State;

public class PalmOilFilter extends FilterAbstract<State>{

	public PalmOilFilter(State v) {
		super(v);
	}

	@Override
	public boolean check(Product p) {
		if(p.getPalmOil().equals(value))
			return true;
		return false;
	}
}
