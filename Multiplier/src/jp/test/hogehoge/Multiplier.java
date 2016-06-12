package jp.test.hogehoge;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * 消費税を考慮した値段計算を行うクラス
 * @author 
 *
 */
@SuppressWarnings("serial")
@Named
@RequestScoped
public class Multiplier implements Serializable {

	/**
	 * 値段と個数を記録し、小計（税抜）の計算を行うクラス
	 * 
	 * @author
	 *
	 */
	class LunchBox {
		private int price;
		private int count;

		public LunchBox(int price) {
			this.price = price;
			count = 0;
		}

		public int subtotal() {
			return this.price * this.count;
		}
	}

	// 弁当の情報
	private LunchBox noriben = new LunchBox(290);
	private LunchBox syakeben = new LunchBox(340);
	private LunchBox makunouchi = new LunchBox(180);

	// 消費税の情報
	private double tax;
	private static final double TAX_05 = 0.05;
	private static final double TAX_08 = 0.08;
	private static final double TAX_10 = 0.10;
	

	/**
	 * のり弁の個数の取得
	 * 
	 * @return のり弁の個数
	 */
	public int getNoribenCount() {
		return this.noriben.count;
	}

	/**
	 * のり弁の個数を設定
	 * 
	 * @param count
	 *            のり弁の個数
	 */
	public void setNoribenCount(int count) {
		if( count < 0 || count > getNoribenLimitCount()){
			throw new IllegalArgumentException();
		}
		this.noriben.count = count;
	}

	/**
	 * しゃけ弁の個数の取得
	 * 
	 * @return しゃけ弁の個数
	 */
	public int getSyakebenCount() {
		return this.syakeben.count;
	}

	/**
	 * しゃけ弁の個数を設定
	 * 
	 * @param count
	 *            しゃけ弁の個数
	 */
	public void setSyakebenCount(int count) {
		if( count < 0 || count > getSyakebenLimitCount()){
			throw new IllegalArgumentException();
		}
		this.syakeben.count = count;
	}

	/**
	 * 幕の内の個数の取得
	 * 
	 * @return 幕の内の個数
	 */
	public int getMakunouchiCount() {
		return this.makunouchi.count;
	}

	/**
	 * 幕の内弁の個数を設定
	 * 
	 * @param count
	 *            幕の内弁の個数
	 */
	public void setMakunouchiCount(int count) {
		if( count < 0 || count > getMakunouchiLimitCount()){
			throw new IllegalArgumentException();
		}
		this.makunouchi.count = count;
	}

	/**
	 * のり弁の値段を取得
	 * 
	 * @return のり弁の値段
	 */
	public int getNoribenPrice() {
		return noriben.price;
	}

	/**
	 * しゃけ弁の値段を取得
	 * 
	 * @return しゃけ弁の値段
	 */
	public int getSyakebenPrice() {
		return syakeben.price;
	}

	/**
	 * 幕の内弁の値段を取得
	 * 
	 * @return 幕の内弁の値段
	 */
	public int getMakunouchiPrice() {
		return makunouchi.price;
	}

	/**
	 * のり弁の小計を計算
	 * 
	 * @return のり弁の小計
	 */
	public int getNoribenSubtotal() {
		return (int) (this.noriben.subtotal() * (1 + tax));
	}

	/**
	 * しゃけ弁の小計を計算
	 * 
	 * @return しゃけ弁の小計
	 */
	public int getSyakebenSubtotal() {
		return (int) (this.syakeben.subtotal() * (1 + tax));
	}

	/**
	 * 幕の内弁の小計を計算
	 * 
	 * @return 幕の内弁の小計
	 */
	public int getMakunouchiSubtotal() {
		return (int) (this.makunouchi.subtotal() * (1 + tax));
	}
	
	/**
	 * のり弁の個数の入力値の最大値を計算
	 */
	public int getNoribenLimitCount(){
		return (int) (Integer.MAX_VALUE / TAX_10 / this.noriben.price);
	}
	
	/**
	 * しゃけ弁の個数の入力値の最大値を計算
	 */
	public int getSyakebenLimitCount(){
		return (int) (Integer.MAX_VALUE / TAX_10 / this.syakeben.price);
	}
	
	/**
	 * 幕の内の個数の入力値の最大値を計算
	 */
	public int getMakunouchiLimitCount(){
		return (int) (Integer.MAX_VALUE / TAX_10 / this.makunouchi.price);
	}

	/**
	 * 消費税を5%に設定
	 */
	public String setTax05() {
		this.tax = TAX_05;
		return null;
	}

	/**
	 * 消費税を8%に設定
	 */
	public String setTax08() {
		this.tax = TAX_08;
		return null;
	}

	/**
	 * 消費税を10%に設定
	 */
	public String setTax10() {
		this.tax = TAX_10;
		return null;
	}

}
