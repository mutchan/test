package login;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import entity.Item;
import entity.PreservedAvatar;
import entity.SellerAccount;
import entity.UserAccount;
import jdk.nashorn.internal.objects.annotations.Constructor;
import manager.AccountManager;
import manager.ItemManager;
import seller.Category;
import seller.PublicStat;

/**
 * @author Mu
 *
 */
@SuppressWarnings("serial")
@Named
@RequestScoped
public class Login implements Serializable {

	@Inject
	private HeaderBean headerBean;

	String id;
	String pass;

	@PersistenceContext(unitName = "DatabaseTest")
	private EntityManager em;

	@Resource
	UserTransaction utx;

	ItemManager im;
	List<Item> itemList;

	@Constructor
	public void init() {
		im = new ItemManager(em, utx);
		itemList = im.getAllItemList();
		System.out.println(itemList);
	}

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
		ItemManager im = new ItemManager(em, utx);
		for (Item item : im.getItemListContains(id)) {
			item.getReviewList().stream().forEach(s -> System.out.println(s.getComment()));
		}

		im.registItem("aaa", "", 100, "", Category.C, PublicStat.PUBLIC, new Date(), new Date());
		im.updateItem(4, "bbb", "ccc", 1200, "ddd.png", Category.B, PublicStat.PRIVATE, new Date());

		AccountManager am = new AccountManager(em, utx);

		// am.updatUserName("健次郎");
		am.updateSellerPassword("pass");

		for (PreservedAvatar avatar : am.getPreservedAvatar(id)) {
			System.out.println(avatar.getAccountId() + "|" + avatar.getDate() + "|" + avatar.getAvatar().getName() + "|"
					+ avatar.getAvatar().getPoint());
		}

		UserAccount user = am.userLogin(id, pass);
		if (user != null) {
			return "seller/seller?faces-redirect=true";
		}

		SellerAccount seller = am.sellerLogin(id, pass);
		if (seller != null) {
			return "seller/seller?faces-redirect=true";
		}

		System.out.println("NG");
		return null;

	}

	/**
	 * @return headerBean
	 */
	public HeaderBean getHeaderBean() {
		return headerBean;
	}

	/**
	 * @param headerBean
	 *            セットする headerBean
	 */
	public void setHeaderBean(HeaderBean headerBean) {
		this.headerBean = headerBean;
	}

	public List<Item> getItemList() {
		return itemList;
	}
}
