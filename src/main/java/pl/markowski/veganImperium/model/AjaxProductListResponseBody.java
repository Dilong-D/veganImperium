package pl.markowski.veganImperium.model;

import java.util.List;

public class AjaxProductListResponseBody {
	private String msg;
    private List<ProductView> result;
    
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<ProductView> getResult() {
		return result;
	}
	public void setResult(List<ProductView> result) {
		this.result = result;
	}
}
