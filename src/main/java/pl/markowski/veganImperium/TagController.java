package pl.markowski.veganImperium;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.markowski.veganImperium.logic.filter.NameFilter;
import pl.markowski.veganImperium.storage.Product;
import pl.markowski.veganImperium.storage.ProductProvider;
import pl.markowski.veganImperium.storage.ProductRepository;

@Controller
public class TagController {
	@Autowired
	ProductProvider productProvider;
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/autocomplete")
    public @ResponseBody Set<String> getTagList(@RequestParam("term") String query) {
         
        List<Product> productList= productProvider.getProductsList(new NameFilter(query));
        Set<String> tagList = productList.stream().map(p->{return p.getName();}).collect(Collectors.toSet());
        
        return tagList;
    }
}
