package entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Bbs
 *
 */
@Entity

public class Bbs implements Serializable {

	   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "ACCOUNT_ID")
	private String accountId;
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name = "IMAGE_ID")
	private int imageId;
	private String comment;
	private int parent;
	private static final long serialVersionUID = 1L;

	public Bbs() {
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
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}   
	public int getImageId() {
		return this.imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}   
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}   
	public int getParent() {
		return this.parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}
   
}
