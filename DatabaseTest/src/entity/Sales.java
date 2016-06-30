package entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Sales
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = Sales.FIND_ALL, query = "Select s from Sales s"),
		@NamedQuery(name = Sales.BY_ID, query = "Select s from Sales s where s.id = :id"),
		@NamedQuery(name = Sales.BY_ACCOUNT, query = "Select s from Sales s where s.account = :account")  })
public class Sales implements Serializable {

	public static final String FIND_ALL = "Sales.findAll";
	public static final String BY_ID = "Sales.findById";
	public static final String BY_ACCOUNT = "Sales.findByAccount";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne(targetEntity = Item.class)
	@JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
	private Item item;
	private int count;

	@ManyToOne(targetEntity = UserAccount.class)
	@JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "GLOCOMM_ID")
	private UserAccount account;
	private int price;

	private static final long serialVersionUID = 1L;

	public Sales() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public UserAccount getAccount() {
		return this.account;
	}

	public void setAccount(UserAccount accountId) {
		this.account = account;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
