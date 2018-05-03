package pl.markowski.veganImperium.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.markowski.veganImperium.storage.Shop;
import pl.markowski.veganImperium.storage.ShopRepository;

@Service
public class ShopService {

	@Autowired
	ShopRepository shopRepository;

	public List<String> getAllShopsNames() {
		List<String> shopNames = new ArrayList<>();
		shopRepository.findAll().forEach(p -> {
			shopNames.add(p.getName());
		});
		return shopNames;
	}

	public List<Shop> getAllShops() {
		return (List<Shop>) shopRepository.findAll();
	}

}
