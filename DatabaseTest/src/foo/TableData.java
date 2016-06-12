package foo;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class TableData {

	// 新規行データ
	private String name;
	private int price;
	private int stock;

	// テーブルデータ
	private List<TableRowData> rowData;

	// コンストラクタ
	public TableData() {
		rowData = new ArrayList<TableRowData>();
		rowData.add(new TableRowData("のり弁", 340, 1));
		rowData.add(new TableRowData("しゃけ弁", 290, 1));
		rowData.add(new TableRowData("幕の内", 180, 1));
	}

	public List<TableRowData> getRowData() {
		// ここにDBからの読み込みを追加
		return rowData;
	}

	public void add() {
		// ここにDBへの書き込みを追加
		rowData.add(new TableRowData(name, price, stock));
		System.out.println(rowData.size());
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            セットする name
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
	 * @param price
	 *            セットする price
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
	 * @param stock
	 *            セットする stock
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}
}
