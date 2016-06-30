package entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cart
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = Cart.FIND_ALL, query = "Select c from Cart c"),
		@NamedQuery(name = Cart.BY_ID, query = "SELECT c FROM Cart c WHERE c.id = :id"),
		@NamedQuery(name = Cart.BY_GLOCOMM_ID, query = "SELECT c FROM Cart c WHERE c.accountId = :glocommId") })
public class Cart implements Serializable {

	public static final String FIND_ALL = "Cart.findAll";
	public static final String BY_ID = "Cart.findById";
	public static final String BY_GLOCOMM_ID = "Cart.findByGlocommId";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "ACCOUNT_ID", length = 30)
	private String accountId;

	@ManyToOne(targetEntity = Avatar.class)
	@JoinColumn(name = "ITEM_ID", referencedColumnName = "id")
	private Item item;
	private int count;

	private static final long serialVersionUID = 1L;

	public Cart() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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

}
