package entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Account
 *
 */
@Entity
@Table(name = "SELLER_ACCOUNT")
@NamedQuery(name = SellerAccount.BY_ID, query = "select sa from SellerAccount sa where sa.id = :id")
public class SellerAccount implements Serializable {

	public static final String BY_ID = "SellerAccount.byId";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2986792687054254909L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int number;
    @Column(length = 30)
	private String id;
    @Column(length = 20)
	private String password;
    @Column(length = 30)
	private String mail;

	/**
	 * 
	 * @return 管理番号の取得
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * 
	 * @return ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 *            ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return パスワード
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @param password
	 *            パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail セットする mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

}
