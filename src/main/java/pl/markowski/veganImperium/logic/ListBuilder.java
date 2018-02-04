package pl.markowski.veganImperium.logic;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pl.markowski.veganImperium.storage.Product;

public class ListBuilder {
	private Stream<String[]> dataStream;

	public ListBuilder withDataStream(Stream<String[]> dataStream) {
		this.dataStream = dataStream;
		return this;
	}

	public List<Product> build() {
		return dataStream.filter(p -> !p[0].isEmpty() && !p[0].equals("Produkt")).map(p -> {
			return new Product(p);
		}).collect(Collectors.toList());

	}

}
