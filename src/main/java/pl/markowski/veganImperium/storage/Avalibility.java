package pl.markowski.veganImperium.storage;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
	
@Entity
@Table(name = "avalibility")
public class Avalibility{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int shopId;
	private int productId;
		
	public long getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
}
