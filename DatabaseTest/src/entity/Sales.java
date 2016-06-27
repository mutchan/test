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

public class Sales implements Serializable {

	   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name = "ITEM_ID")
	private int itemId;
	private int count;

	@Column(name = "ACCOUNT_ID", length = 30)
	private String accountId;
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
	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}   
	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
   
}
