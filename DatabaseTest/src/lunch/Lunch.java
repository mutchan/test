package lunch;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the LUNCH database table.
 * 
 */
@Entity
@NamedQuery(name = "Lunch.findAll", query = "SELECT l FROM Lunch l")
public class Lunch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8237933899799365211L;

	@Id
	private String name;

	private int price;

	private int stock;

	/**
	 * 
	 */
	public Lunch() {
	}

	/**
	 * @param name a
	 * @param price a
	 * @param stock a
	 */
	public Lunch(String name, int price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	/**
	 * 
	 * @return 名前
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 *            名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return 値段
	 */
	public int getPrice() {
		return this.price;
	}

	/**
	 * 
	 * @param price
	 *            設定する値段
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * 
	 * @return 在庫
	 */
	public int getStock() {
		return this.stock;
	}

	/**
	 * 
	 * @param stock
	 *            設定する在庫
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

}