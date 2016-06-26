package lunch;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Mutsuki Hiradate
 *
 */
@Entity
@Table(name = "LUNCH")
public class LunchEntity {

    @Id
    private String name;
	private int price;
	private int stock;
    /**
     * @return 名前
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 設定する名前
     */
    public void setName(String name) {
        this.name = name;
    }

	/**
	 * @return price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price セットする price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock セットする stock
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

}