package pl.markowski.veganImperium.filter;

import pl.markowski.veganImperium.storage.Product;

public class NameFilter extends FilterAbstract<String>{

	public NameFilter(String v) {
		super(v);
	}

	@Override
	public boolean check(Product p) {
		if(p.getName().contains(value))
			return true;
		return false;
	}

}
