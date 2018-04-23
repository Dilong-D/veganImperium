package pl.markowski.veganImperium.model;

import java.util.List;

import pl.markowski.veganImperium.storage.Product;

public class AjaxProductResponseBody {
	private String msg;
    private List<Product> result;
    
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Product> getResult() {
		return result;
	}
	public void setResult(List<Product> result) {
		this.result = result;
	}
}
