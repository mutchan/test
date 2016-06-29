package entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;
import seller.Category;
import seller.PublicStat;

/**
 * Entity implementation class for Entity: Item
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = Item.FIND_ALL, query = "SELECT i FROM Item i"),
		@NamedQuery(name = Item.BY_ID, query = "SELECT i FROM Item i WHERE i.id = :id"),
		@NamedQuery(name = Item.BY_CATEGORY, query = "SELECT i FROM Item i WHERE i.category = :category"),
		@NamedQuery(name = Item.BY_NAME, query = "SELECT i FROM Item i WHERE i.name LIKE :name") })
public class Item implements Serializable {

	public static final String FIND_ALL = "Item.findAll";
	public static final String BY_ID = "Item.findById";
	public static final String BY_CATEGORY = "Item.findByCategoryId";
	public static final String BY_NAME = "Item.findByName";

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String description;
	private int stock;
	@Column(name = "IMAGE_PATH")
	private String imagePath;
	@Enumerated(EnumType.ORDINAL)
	private Category category;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "PUBLIC_STAT")
	private PublicStat publicStat;
	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_EDIT")
	private Date lastEdit;
	@Temporal(TemporalType.DATE)
	@Column(name = "REGIST_DATE")
	private Date registDate;
	@OneToMany(targetEntity = Review.class)
	@JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
	private List<Review> reviewList;

	private static final long serialVersionUID = 1L;

	public Item() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return this.description;
	}

	public void setDesc(String description) {
		this.description = description;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public PublicStat getPublicStat() {
		return this.publicStat;
	}

	public void setPublicStat(PublicStat publicStat) {
		this.publicStat = publicStat;
	}

	public Date getLastEdit() {
		return lastEdit;
	}

	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;

	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;

	}

	public List<Review> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;

	}
	
	public double getReview(){
		int total = reviewList.stream().collect(Collectors.summingInt(review -> review.getStar()));
		return total / reviewList.size();
	}
}
