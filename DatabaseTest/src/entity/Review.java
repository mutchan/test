package entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Review
 *
 */
@Entity

public class Review implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "ACCOUNT_ID", length = 30)
	private String accountId;
	
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name = "ITEM_ID")
	private int itemId;
	private String comment;
	private int star;

	@Column(name = "GOOD_COUNT")
	private int goodCount;

	@Column(name = "IMG_ID")
	private int imageId;

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public Review() {
		super();
	}

	/**
	 * IDの取得
	 * 
	 * @return ID
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * IDの設定
	 * 
	 * @param id
	 *            設定するID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * レビューしたアカウントのIDの取得
	 * 
	 * @return レビューしたアカウントのID
	 */
	public String getAccountId() {
		return this.accountId;
	}

	/**
	 * レビューしたアカウントのIDの設定
	 * @param accountId レビューしたアカウントのID
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getStar() {
		return this.star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getGoodCount() {
		return this.goodCount;
	}

	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}

	public int getImageId() {
		return this.imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

}
