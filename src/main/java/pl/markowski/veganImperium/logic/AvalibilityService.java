package pl.markowski.veganImperium.logic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.markowski.veganImperium.model.AvalibilityView;
import pl.markowski.veganImperium.storage.Avalibility;
import pl.markowski.veganImperium.storage.AvalibilityRepository;
import pl.markowski.veganImperium.storage.ShopRepository;
@Service
public class AvalibilityService {
	
	@Autowired 
	AvalibilityRepository avalibilityRepository;
	
	@Autowired
	ShopRepository shopRepository;
	
	public List<AvalibilityView> getAvalibilityView(int prodId) {
		List<AvalibilityView> avalView = avalibilityRepository.findByProductId(prodId).stream().map(p->createAvalibityView(p)).collect(Collectors.toList());
		return avalView;
	}
	
	private AvalibilityView createAvalibityView(Avalibility aval) {
		AvalibilityView avalView = new AvalibilityView();
		avalView.setId(aval.getId());
		avalView.setProductId(aval.getProductId());
		avalView.setShopId(aval.getShopId());
		avalView.setShopName(shopRepository.findByIdReturnName(aval.getShopId()));
		return avalView;
	}
}
