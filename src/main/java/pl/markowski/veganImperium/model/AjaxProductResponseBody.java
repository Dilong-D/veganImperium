package pl.markowski.veganImperium.model;

import java.util.List;

import pl.markowski.veganImperium.storage.Product;

public class AjaxProductResponseBody {
	
	private String msg;
	private Product result;
	private List<AvalibilityView> avalibilityViewResult;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Product getResult() {
		return result;
	}

	public void setResult(Product result) {
		this.result = result;
	}

	public List<AvalibilityView> getAvalibilityViewResult() {
		return avalibilityViewResult;
	}
	
	public void setAvalibilityViewResults (List<AvalibilityView> list) {
		this.avalibilityViewResult = list;
	}
}
