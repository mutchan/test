package user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
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
public class TopPage implements Serializable {

	String id;
	String pass;

	@PersistenceContext(unitName = "DatabaseTest")
	private EntityManager em;

	@Resource
	UserTransaction utx;

	ItemManager im;
	List<Item> itemList;

	public TopPage() {
	}
	
	@PostConstruct
	public void init(){
		im = new ItemManager(em, utx);
		itemList = im.getAllItemList();
		System.out.println(itemList);
		System.out.println(itemList.get(0).getName());
	}

	public List<Item> getItemList() {
		return itemList;
	}
}
