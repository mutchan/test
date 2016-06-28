package entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;
import seller.Category;
import seller.OpenInfo;

/**
 * Entity implementation class for Entity: Item
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Item.FIND_ALL, query = "SELECT i FROM Item i"),
    @NamedQuery(name = Item.BY_ID, query = "SELECT i FROM Item i WHERE i.id = :id"),
    @NamedQuery(name = Item.BY_CATEGORY, query = "SELECT i FROM Item i WHERE i.category = :category"),
    @NamedQuery(name = Item.BY_NAME, query = "SELECT i FROM Item i WHERE i.name LIKE :name")})
public class Item implements Serializable {

	public static final String FIND_ALL = "Item.findAll";
	public static final String BY_ID = "Item.findById";
	public static final String BY_CATEGORY = "Item.findByCategoryId";
	public static final String BY_NAME = "Item.findByName";
	   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@Enumerated(EnumType.ORDINAL)
	private Category category;
	private int stock;
	private String description;
	@Column(name = "PUBLIC_STAT")
	private OpenInfo publicStat;
	@Column(name = "IMAGE_PATH")
	private String imagePath;
	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_EDIT")
	private Date lastEdit;
	@Temporal(TemporalType.DATE)
	@Column(name = "REGIST_DATE")
	private Date registDate;	
	
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
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}   
	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}   
	public String getDesc() {
		return this.description;
	}

	public void setDesc(String description) {
		this.description = description;
	}   
	public OpenInfo getPublicStat() {
		return this.publicStat;
	}

	public void setPublicStat(OpenInfo publicStat) {
		this.publicStat = publicStat;
	}   
	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
   
}
