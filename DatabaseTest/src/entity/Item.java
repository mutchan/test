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

public class Item implements Serializable {

	   
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
