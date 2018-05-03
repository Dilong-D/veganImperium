package pl.markowski.veganImperium;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.markowski.veganImperium.logic.ProductService;
import pl.markowski.veganImperium.storage.Product;

@Controller
public class TagController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/autocomplete")
    public @ResponseBody Set<String> getTagList(@RequestParam("term") String query) {
         
        List<Product> productList= productService.getProductsListByName(query);
        Set<String> tagList = productList.stream().map(p->{return p.getName();}).collect(Collectors.toSet());
        return tagList;
    }
}
