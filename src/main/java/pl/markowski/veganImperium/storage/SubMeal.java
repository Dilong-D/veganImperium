package pl.markowski.veganImperium.storage;

public enum SubMeal {
	bread ("Pieczywi"),
	spreads ("Smarowidła do kanapek"),
	replacements ("Zastępniki"),
	cereals ("Płatki śniadaniowe"),
	sauces ("Sosy"),
	drinks ("Soki i napoje"),
	readySoups ("Zupy gotowe"),
	frozenSoups ("Zupy mrożonki"),
	chineseSoups ("Zupki chińskie"),
	dairySubstitutes ("Zastępniki nabiału"),
	salads ("Sałatki"),
	salads2 ("Surówki"),
	pasta ("Loose products and pasta"),
	dressings ("Dressingi i fixy"),
	spices ("Przyprawy"),
	worldsCuisines("Kuchnie świata"),
	readyMeals ("Dania gotowe"),
	frozenSemiProducts ("Mrożonki półprodukty"),
	frozenVegetables ("Mrożonki warzywa"),
	soyProducts ("Produkty sojowe"),
	puddings ("Kisiele i budynie"),
	bakings ("Rzeczy do pieczenia"),
	sweets ("Słodycze"),
	saltySnacks ("Słone przekąstki"),
	iceCream ("Lody");
	
	
	private final String value;
	
	SubMeal(String arg){
		this.value = arg;
	}

	public String getValue() {
		return value;
	}
	
}
