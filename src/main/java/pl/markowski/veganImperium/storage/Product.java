package pl.markowski.veganImperium.storage;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product{

	@Id
	private int barcode;
	private String name;
	private State vegan;
	private State vegetarian;
	private State palmOil;
	private int mealId;
	private int kindId;
	
	public Product() {
		barcode = 0;
		name = "";
		vegan = State.maybe;
		vegetarian = State.maybe; 
		palmOil = State.maybe;
		mealId = 0;
		kindId = 0;
	}
	
	@Override
	public String toString() {
		return "Product [barcode=" + barcode + ", name=" + name + ", vegan=" + vegan + ", vegetarian=" + vegetarian
				+ ", palmOil=" + palmOil + ", mealId=" + mealId + ", kindId=" + kindId + "]";
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
	public int getBarcode() {
		return barcode;
	}
	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}
	public int getMealId() {
		return mealId;
	}
	public void setMealId(int mealId) {
		this.mealId = mealId;
	}
	
	public int getKindId() {
		return kindId;
	}
	public void setKindId(int kindId) {
		this.kindId = kindId;
	}
}
