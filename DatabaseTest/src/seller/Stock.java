package seller;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the STOCK database table.
 * 
 */
@Entity
@NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s")
public class Stock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Enumerated(EnumType.ORDINAL)
	private Category category;

	@Column(name = "\"COUNT\"")
	private int count;

	private String description;

	@Column(name = "IMG_PATH")
	private String imgPath;

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_EDIT")
	private Date lastEdit;

	private String name;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "OPEN_INFO")
	private OpenInfo openInfo;

	public Stock() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgPath() {
		return this.imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Date getLastEdit() {
		return this.lastEdit;
	}

	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OpenInfo getOpenInfo() {
		return this.openInfo;
	}

	public void setOpenInfo(OpenInfo openInfo2) {
		this.openInfo = openInfo2;
	}

	public int getPrice() {
		return category.getInitPrice();
	}

	public String getOpenString() {
		return openInfo.toString();
	}
}