package pl.markowski.veganImperium.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pl.markowski.veganImperium.storage.State;

public class FilterFactory {
	private List<FilterAbstract<?>> filtersList;

	public List<FilterAbstract<?>> getFiltersList(Map<String, String> filterMap) {
		filtersList = new ArrayList<FilterAbstract<?>>();
		filterMap.entrySet().forEach(entry -> addFilter(entry));
		return filtersList;
	}

	private void addFilter(Map.Entry<String, String> entry) {
		if (entry != null) {
			switch (entry.getKey()) {
			case "name":
				if (entry.getValue() != null)
					filtersList.add(new NameFilter(entry.getValue()));
				break;
			case "vegan":
				if (entry.getValue() != null) {
					try {
						State value = State.valueOf(entry.getValue());	
						filtersList.add(new VeganFilter(value));
					}
					catch(IllegalArgumentException e){
						//TODO logger
					}
				}	
				break;
			case "vegetarian":
				if (entry.getValue() != null) {
					try {
						State value = State.valueOf(entry.getValue());	
						filtersList.add(new VegetarianFilter(value));
					}
					catch(IllegalArgumentException e){
						//TODO logger
					}
				}
				break;
			case "palmOil":
				if (entry.getValue() != null) {
					try {
						State value = State.valueOf(entry.getValue());	
						filtersList.add(new PalmOilFilter(value));
					}
					catch(IllegalArgumentException e){
						//TODO logger
					}
				}
				break;
			default:
				break;
			}
		}
	}
}
