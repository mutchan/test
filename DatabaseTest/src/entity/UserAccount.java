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

public class UserAccount implements Serializable {

	@Id
	@Column(name = "GLOCOMM_ID", length = 30)
	private int glocommId;
	private String name;
	@Column(name = "ROOM_NUMBER")
	private int roomNumber;
	private int point;
	private int money;
	private static final long serialVersionUID = 1L;

	public UserAccount() {
		super();
	}   
	public int getGlocommId() {
		return this.glocommId;
	}

	public void setGlocommId(int glocommId) {
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
   
}
