package pl.markowski.veganImperium.logic.filter;

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
					System.out.println(entry.getValue());
					try {
						if (entry.getValue().equals("on"))
							filtersList.add(new VeganFilter(State.yes));
					}
					catch(IllegalArgumentException e){
						//TODO logger
					}
				}	
				break;
			case "vegetarian":
				if (entry.getValue() != null) {
					try {
						if (entry.getValue().equals("on"))
							filtersList.add(new VegetarianFilter(State.yes));
					}
					catch(IllegalArgumentException e){
						//TODO logger
					}
				}
				break;
			case "palmOil":
				if (entry.getValue() != null) {
					try {
						if (entry.getValue().equals("on"))
							filtersList.add(new PalmOilFilter(State.yes));
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
