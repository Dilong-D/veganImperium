package pl.markowski.veganImperium;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import pl.markowski.veganImperium.logic.ListBuilder;
import pl.markowski.veganImperium.storage.Product;
import pl.markowski.veganImperium.storage.ProductRepository;

@Component
public class FileUploadHandler {
	
	@Autowired
	ProductRepository productRepository;
	
	public void updateDB(MultipartFile file) throws IOException {
		CSVReader reader = multipartFile2CSVReader(file);
		
		List<Product> temp = new ListBuilder().withDataStream(createDataStream(reader)).build();
		productRepository.deleteAll();
		productRepository.save(temp);
	}

	private CSVReader multipartFile2CSVReader(MultipartFile file) throws IOException {
		CSVReader reader = null;
		if (!file.isEmpty()) {
			InputStream inputStream = file.getInputStream();
			reader = new CSVReader(new InputStreamReader(inputStream, "UTF-8"));
		}
		return reader;
	}
	
	private Stream<String[]> createDataStream(CSVReader reader) throws IOException {
		
		List<String[]> stringList = new ArrayList<String[]>();
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			stringList.add(nextLine);
		}
		return stringList.stream();
	}
}
