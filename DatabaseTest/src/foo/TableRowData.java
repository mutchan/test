package foo;

public class TableRowData {

	// 行データ
	private String name;
	private int price;
	private int stock;

	// コンストラクタ
	public TableRowData(String name, int price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}
}