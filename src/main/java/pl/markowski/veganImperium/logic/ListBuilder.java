//package pl.markowski.veganImperium.logic;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.springframework.web.multipart.MultipartFile;
//
//import com.opencsv.CSVReader;
//
//import pl.markowski.veganImperium.storage.Product;
//
//public class ListBuilder {
//	private Stream<String[]> dataStream;
//
//	public ListBuilder withFile(MultipartFile file) throws IOException {
//		CSVReader reader = multipartFile2CSVReader(file);
//		this.dataStream = createDataStream(reader);
//		return this;
//	}
//
//	public List<Product> build() {
//		return dataStream.filter(p -> !p[0].isEmpty() && !p[0].equals("Produkt")).map(p -> {
//			return new Product(p);
//		}).collect(Collectors.toList());
//	}
//
//	private Stream<String[]> createDataStream(CSVReader reader) throws IOException {
//		List<String[]> stringList = new ArrayList<String[]>();
//		String[] nextLine;
//		while ((nextLine = reader.readNext()) != null) {
//			stringList.add(nextLine);
//		}
//		return stringList.stream();
//	}
//
//	private CSVReader multipartFile2CSVReader(MultipartFile file) throws IOException {
//		CSVReader reader = null;
//		if (!file.isEmpty()) {
//			InputStream inputStream = file.getInputStream();
//			reader = new CSVReader(new InputStreamReader(inputStream, "UTF-8"));
//		}
//		return reader;
//	}
//
//}
