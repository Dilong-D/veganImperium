package pl.markowski.veganImperium.filter;

import pl.markowski.veganImperium.storage.Product;

public abstract class FilterAbstract<T> {
	protected T value;
	
	public abstract boolean check(Product p);

	public FilterAbstract(T v){
		this.value=v;
	}
}
