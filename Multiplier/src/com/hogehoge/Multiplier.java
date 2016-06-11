package com.hogehoge;

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

	private LunchBox noriben = new LunchBox(290);
	private LunchBox syakeben = new LunchBox(340);
	private LunchBox makunouchi = new LunchBox(180);

	private double tax;

	/**
	 * のり弁の個数の取得
	 * 
	 * @return のり弁の個数
	 */
	public String getNoribenCount() {
		return Integer.toString(this.noriben.count);
	}

	/**
	 * のり弁の個数を設定
	 * 
	 * @param count
	 *            のり弁の個数
	 */
	public void setNoribenCount(String count) {
		this.noriben.count = Integer.parseInt(count);
	}

	/**
	 * しゃけ弁の個数の取得
	 * 
	 * @return しゃけ弁の個数
	 */
	public String getSyakebenCount() {
		return Integer.toString(this.syakeben.count);
	}

	/**
	 * しゃけ弁の個数を設定
	 * 
	 * @param count
	 *            しゃけ弁の個数
	 */
	public void setSyakebenCount(String count) {
		this.syakeben.count = Integer.parseInt(count);
	}

	/**
	 * 幕の内の個数の取得
	 * 
	 * @return 幕の内の個数
	 */
	public String getMakunouchiCount() {
		return Integer.toString(this.makunouchi.count);
	}

	/**
	 * 幕の内弁の個数を設定
	 * 
	 * @param count
	 *            幕の内弁の個数
	 */
	public void setMakunouchiCount(String count) {
		this.makunouchi.count = Integer.parseInt(count);
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
	 * 消費税を5%に設定
	 */
	public void setTax05() {
		this.tax = 0.05;
	}

	/**
	 * 消費税を8%に設定
	 */
	public void setTax08() {
		this.tax = 0.08;
	}

	/**
	 * 消費税を10%に設定
	 */
	public void setTax10() {
		this.tax = 0.10;
	}

}
