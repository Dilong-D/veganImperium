package pl.markowski.veganImperium.model;

import java.util.List;

import pl.markowski.veganImperium.storage.Avalibility;
import pl.markowski.veganImperium.storage.Product;

public class AjaxProductResponseBody {

	private String msg;
	private Product result;
	private List<Avalibility> avalibilityResult;

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

	public List<Avalibility> getAvalibilityResult() {
		return avalibilityResult;
	}

	public void setAvalibilityResult(List<Avalibility> avalibilityResult) {
		this.avalibilityResult = avalibilityResult;
	}
}
