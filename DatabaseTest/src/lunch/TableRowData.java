package lunch;

/**
 * @author Mutsuki Hiradate
 *
 */
public class TableRowData {

	// 行データ
	private String name;
	private int price;
	private int stock;

	/**
	 * コンストラクタ
	 * @param name 名前
	 * @param price 値段
	 * @param stock 在庫
	 */
	public TableRowData(String name, int price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	/**
	 * @return 名前の取得
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return 値段の取得
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @return 在庫の取得
	 */
	public int getStock() {
		return stock;
	}
}