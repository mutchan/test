package login;

import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import entity.SellerAccount;

/**
 * @author Mu
 *
 */
@Named
@RequestScoped
public class Login {

	String id;
	String pass;

	@PersistenceContext(unitName = "DatabaseTest")
	private EntityManager em;

	@Resource
	UserTransaction utx;

	/**
	 * 
	 * @param id
	 *            IDテキストボックスに入力された文字列
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * IDテキストボックスに入力する文字列
	 * 
	 * @return IDテキストボックスに入力する文字列
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * PASSテキストボックスに入力する文字列
	 * 
	 * @param pass
	 *            PASSテキストボックスに入力された文字列
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * PASSテキストボックスに入力する文字列
	 * 
	 * @return PASSテキストボックスに入力する文字列
	 */
	public String getPass() {
		return this.pass;
	}

	/**
	 * 
	 * @return 成功時はトップページへのリンク
	 */
	public String CheckAccount() {

		List<SellerAccount> account = em.createQuery("select l from Account l", SellerAccount.class).getResultList();

		for (SellerAccount acc : account) {
			if( id.equals(acc.getId()) && pass.equals(acc.getPassword())){
				System.out.println("OK");
				return "seller/sale?faces-redirect=true";
			}
		}

		System.out.println("NG");
		return null;
	}
}
