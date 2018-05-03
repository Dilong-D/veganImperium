package pl.markowski.veganImperium.model;

import java.util.List;

public class ProductAvalibility {
	private Integer productId;
	private List<Integer> productAvalibilityList;

	public ProductAvalibility() {
		this.productId=null;
		this.productAvalibilityList=null;
	}
	
	public ProductAvalibility(Integer productId, List<Integer> productAvalibilityList) {
		this.productId=productId;
		this.productAvalibilityList=productAvalibilityList;
	}
	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public List<Integer> getProductAvalibilityList() {
		return productAvalibilityList;
	}

	public void setProductAvalibilityList(List<Integer> productAvalibilityList) {
		this.productAvalibilityList = productAvalibilityList;
	}
}
