package pl.markowski.veganImperium.model;

import java.util.List;

import pl.markowski.veganImperium.storage.Kind;
import pl.markowski.veganImperium.storage.Meal;
import pl.markowski.veganImperium.storage.Product;
import pl.markowski.veganImperium.storage.State;

public class ProductView {
	private int barcode;
	private String name;
	private State vegan;
	private State vegetarian;
	private State palmOil;
	private String kind;
	private String meal;
	private String shops;
	
	public ProductView(Product product, Kind kind, Meal meal, List<String> shopList) {
		this.setBarcode(product.getBarcode());
		this.name = product.getName();
		this.vegan = product.getVegan();
		this.vegetarian = product.getVegetarian();
		this.palmOil = product.getPalmOil();
		this.kind = kind.getName();
		this.meal = meal.getName();
		this.setShops(String.join(", ", shopList));
	}

	public ProductView() {
		this.barcode = 0;
		this.name = "";
		this.vegan = null;
		this.vegetarian = null;
		this.palmOil = null;
		this.kind = "";
		this.meal = "";
		this.setShops("");
	}

	public int getBarcode() {
		return barcode;
	}

	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getVegan() {
		return vegan;
	}

	public void setVegan(State vegan) {
		this.vegan = vegan;
	}

	public State getVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(State vegetarian) {
		this.vegetarian = vegetarian;
	}

	public State getPalmOil() {
		return palmOil;
	}

	public void setPalmOil(State palmOil) {
		this.palmOil = palmOil;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public String getShops() {
		return shops;
	}

	public void setShops(String shops) {
		this.shops = shops;
	}
}
