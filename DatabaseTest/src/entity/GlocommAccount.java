package entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: GlocommAccount
 *
 */
@Entity
@Table(name = "GLOCOMM_ACCOUNT")
@NamedQueries({ @NamedQuery(name = GlocommAccount.FIND_ALL, query = "select a from GlocommAccount a") })
public class GlocommAccount implements Serializable {
	public static final String FIND_ALL = "GlocommAccount.findAll";

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

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
}
