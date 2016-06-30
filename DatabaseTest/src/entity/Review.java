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
@NamedQuery(name = Review.FIND_ALL, query = "Select r from Review r")
public class Review implements Serializable {

	public static final String FIND_ALL = "Review.findAll";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(targetEntity = UserAccount.class)
	@JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "GLOCOMM_ID")
	private UserAccount account;
	
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
	 * レビューしたアカウントの取得
	 * 
	 * @return レビューしたアカウント
	 */
	public UserAccount getAccount() {
		return this.account;
	}

	/**
	 * レビューしたアカウントの設定
	 * @param accountId レビューしたアカウント
	 */
	public void setAccount(UserAccount account) {
		this.account = account;
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
