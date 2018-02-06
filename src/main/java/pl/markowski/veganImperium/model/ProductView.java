package pl.markowski.veganImperium.model;

import pl.markowski.veganImperium.storage.Product;

public class ProductView {
	private String name;
	private String vegan;
	private String vegetarian;
	private String palmOil;
	
	public ProductView(Product product) {
		name = product.getName();
		vegan = product.getVegan().toString();
		vegetarian = product.getVegetarian().toString();
		palmOil = product.getPalmOil().toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVegan() {
		return vegan;
	}

	public void setVegan(String vegan) {
		this.vegan = vegan;
	}

	public String getVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(String vegetarian) {
		this.vegetarian = vegetarian;
	}

	public String getPalmOil() {
		return palmOil;
	}

	public void setPalmOil(String palmOil) {
		this.palmOil = palmOil;
	}

	@Override
	public String toString() {
		return "ProductView [name=" + name + ", vegan=" + vegan + ", vegetarian=" + vegetarian + ", palmOil=" + palmOil
				+ "]";
	}
}
