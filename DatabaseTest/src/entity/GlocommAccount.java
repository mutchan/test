package entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: GlocommAccount
 *
 */
@Entity

public class GlocommAccount implements Serializable {

	   
	@Id
	private String id;
	private String pass;
	@OneToOne(fetch = FetchType.LAZY)
	UserAccount userAccount;
	
	private static final long serialVersionUID = 1L;

	public GlocommAccount() {
		super();
	}   
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}   
	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
   
}
