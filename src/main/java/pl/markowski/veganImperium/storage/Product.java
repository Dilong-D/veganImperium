package pl.markowski.veganImperium.storage;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	private int barcode;
	private String name;
	private State vegan;
	private State vegetarian;
	private State palmOil;
	private String meal;
	private String subMeal;
	private String kind;
	private String subKind;
	private State tesco;
	private State kaufland;
	private State carrefour;
	private State lidl;
	private State biedronka;
	private State fresh;
	private State zabka;
	private State netto;
	private State mila;
	private State piotrPawel;
	private State lewiatan;
	private State malpkaExpress;
	private State spolem;
	private State leclerc;
	
	public Product(String[] dataList) {
		barcode = Integer.parseInt(dataList[4]);
		name = dataList[0];
		vegan = string2State(dataList[1]);
		vegetarian = string2State(dataList[2]); 
		palmOil = string2State(dataList[3]);
		meal = dataList[5];
		subMeal = dataList[6];
		kind = dataList[7];
		subKind = dataList[8];
		tesco = string2State(dataList[9]);
		kaufland = string2State(dataList[10]);
		carrefour = string2State(dataList[11]);
		lidl = string2State(dataList[12]);
		biedronka = string2State(dataList[13]);
		fresh = string2State(dataList[14]);
		zabka = string2State(dataList[15]);
		netto = string2State(dataList[16]);
		mila = string2State(dataList[17]);
		piotrPawel = string2State(dataList[18]);
		lewiatan = string2State(dataList[19]);
		malpkaExpress = string2State(dataList[20]);
		spolem = string2State(dataList[21]);
		leclerc = string2State(dataList[22]);
	}
	
	public Product() {
		barcode = 0;
		name = "";
		vegan = State.maybe;
		vegetarian = State.maybe; 
		palmOil = State.maybe;
		meal = "";
		subMeal = "";
		kind = "";
		subKind = "";
		tesco = State.maybe;
		kaufland = State.maybe;
		carrefour = State.maybe;
		lidl = State.maybe;
		biedronka = State.maybe;
		fresh = State.maybe;
		zabka = State.maybe;
		netto = State.maybe;
		mila = State.maybe;
		piotrPawel = State.maybe;
		lewiatan = State.maybe;
		malpkaExpress = State.maybe;
		spolem = State.maybe;
		leclerc = State.maybe;
	}
	
	private State string2State(String arg) {
		if(arg.isEmpty())
			return State.maybe;
		else if(arg.equals("1"))
			return State.yes;
		else
			return State.no;
	}
	
	@Override
	public String toString() {
		return "Product [barcode=" + barcode + ", name=" + name + ", vegan=" + vegan + ", vegetarian=" + vegetarian
				+ ", palmOil=" + palmOil + ", meal=" + meal + ", subMeal=" + subMeal + ", kind=" + kind + ", subKind="
				+ subKind + ", tesco=" + tesco + ", kaufland=" + kaufland + ", carrefour=" + carrefour + ", lidl="
				+ lidl + ", biedronka=" + biedronka + ", fresh=" + fresh + ", zabka=" + zabka + ", netto=" + netto
				+ ", mila=" + mila + ", piotrPawel=" + piotrPawel + ", lewiatan=" + lewiatan + ", malpkaExpress="
				+ malpkaExpress + ", spolem=" + spolem + ", leclerc=" + leclerc + "]";
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
	public String getMeal() {
		return meal;
	}
	public void setMeal(String meal) {
		this.meal = meal;
	}
	public String getSubMeal() {
		return subMeal;
	}
	public void setSubMeal(String subMeal) {
		this.subMeal = subMeal;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getSubKind() {
		return subKind;
	}
	public void setSubKind(String subKind) {
		this.subKind = subKind;
	}
	public State getTesco() {
		return tesco;
	}
	public void setTesco(State tesco) {
		this.tesco = tesco;
	}
	public State getKaufland() {
		return kaufland;
	}
	public void setKaufland(State kaufland) {
		this.kaufland = kaufland;
	}
	public State getCarrefour() {
		return carrefour;
	}
	public void setCarrefour(State carrefour) {
		this.carrefour = carrefour;
	}
	public State getLidl() {
		return lidl;
	}
	public void setLidl(State lidl) {
		this.lidl = lidl;
	}
	public State getBiedronka() {
		return biedronka;
	}
	public void setBiedronka(State biedronka) {
		this.biedronka = biedronka;
	}
	public State getFresh() {
		return fresh;
	}
	public void setFresh(State fresh) {
		this.fresh = fresh;
	}
	public State getZabka() {
		return zabka;
	}
	public void setZabka(State zabka) {
		this.zabka = zabka;
	}
	public State getNetto() {
		return netto;
	}
	public void setNetto(State netto) {
		this.netto = netto;
	}
	public State getMila() {
		return mila;
	}
	public void setMila(State mila) {
		this.mila = mila;
	}
	public State getPiotrPawel() {
		return piotrPawel;
	}
	public void setPiotrPawel(State piotrPawel) {
		this.piotrPawel = piotrPawel;
	}
	public State getLewiatan() {
		return lewiatan;
	}
	public void setLewiatan(State lewiatan) {
		this.lewiatan = lewiatan;
	}
	public State getMalpkaExpress() {
		return malpkaExpress;
	}
	public void setMalpkaExpress(State malpkaExpress) {
		this.malpkaExpress = malpkaExpress;
	}

	public State getSpolem() {
		return spolem;
	}

	public void setSpolem(State spolem) {
		this.spolem = spolem;
	}

	public State getLeclerc() {
		return leclerc;
	}

	public void setLeclerc(State leclerc) {
		this.leclerc = leclerc;
	}


	
}
