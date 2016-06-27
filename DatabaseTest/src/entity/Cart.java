package entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cart
 *
 */
@Entity

public class Cart implements Serializable {

	   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "ACCOUNT_ID", length = 30)
	private String accountId;

	@Column(name = "ITEM_ID")
	private int itemId;
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
	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}   
	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}
   
}
