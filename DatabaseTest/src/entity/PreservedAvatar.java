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
@Table(name = "PRESERVED_AVATAR")
@NamedQueries({ @NamedQuery(name = PreservedAvatar.FIND_ALL, query = "Select r from PreservedAvatar r"),
		@NamedQuery(name = PreservedAvatar.BY_GLOCOMM_ID, query = "Select p from PreservedAvatar p where p.accountId = ?1") })
public class PreservedAvatar implements Serializable {

	public static final String FIND_ALL = "PreservedAvatar.findAll";
	public static final String BY_GLOCOMM_ID = "PreservedAvatar.byGlocommId";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "ACCOUNT_ID", length = 30)
	private String accountId;
	@ManyToOne(targetEntity = Avatar.class)
	@JoinColumn(name = "AVATAR_ID", referencedColumnName = "id")
	private Avatar avatar;
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

	public Avatar getAvatar() {
		return this.avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
