package seller;

/**
 * 
 * @author Mu
 *
 */
public enum Category {
	/**
	 * 商品カテゴリA
	 */
	A(100, 100000),

	/**
	 * 商品カテゴリB
	 */
	B(10000, 5000),

	/**
	 * 商品カテゴリC
	 */
	C(100000, 100);

	private int initStock;
	private int initPrice;

	Category(int initStock, int initPrice) {
		this.initStock = initStock;
		this.initPrice = initPrice;
	}

	/**
	 * 初期在庫の取得
	 * @return 初期在庫
	 */
	public int getInitStock() {
		return this.initStock;
	}

	/**
	 * 初期単価の取得
	 * @return 初期単価
	 */
	public int getInitPrice() {
		return this.initPrice;
	}
	
	@Override
	public String toString() {
		return "商品カテゴリ" + super.toString() + ":" + initPrice + "円";
	}
}
