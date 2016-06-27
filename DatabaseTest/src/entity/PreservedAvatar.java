package entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: PreservedAvatar
 *
 */
@Entity

public class PreservedAvatar implements Serializable {

	   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "ACCOUNT_ID", length = 30)
	private String accountId;
	@Column(name = "AVATAR_ID")
	private int avatarId;
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private static final long serialVersionUID = 1L;

	public PreservedAvatar() {
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
	public int getAvatarId() {
		return this.avatarId;
	}

	public void setAvatarId(int avatarId) {
		this.avatarId = avatarId;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
   
}
