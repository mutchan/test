package entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserAccount
 *
 */
@Entity
@Table(name = "USER_ACCOUNT")
@NamedQueries({ @NamedQuery(name = UserAccount.FIND_ALL, query = "select a from UserAccount a"),
		@NamedQuery(name = UserAccount.BY_GLOCOMM_ID, query = "select a from UserAccount a where a.glocommId = :glocommId") })
public class UserAccount implements Serializable {
	public static final String FIND_ALL = "UserAccount.findAll";
	public static final String BY_GLOCOMM_ID = "UserAccount.byGlocommId";

	@Id
	@Column(name = "GLOCOMM_ID", length = 30)
	private String glocommId;
	private String name;
	@Column(name = "ROOM_NUMBER")
	private int roomNumber;
	private int point;
	private int money;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "userAccount", fetch = FetchType.LAZY)
	@JoinColumn(name = "GLOCOMM_ID", referencedColumnName = "ID")
	GlocommAccount glocommAccount;

	private static final long serialVersionUID = 1L;

	public UserAccount() {
		super();
	}

	public String getGlocommId() {
		return this.glocommId;
	}

	public void setGlocommId(String glocommId) {
		this.glocommId = glocommId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoomNumber() {
		return this.roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getPoint() {
		return this.point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setGlocommAccount(GlocommAccount glocommAccount) {
		this.glocommAccount = glocommAccount;
	}

	public GlocommAccount getGlocommAccount() {
		return this.glocommAccount;
	}
}
