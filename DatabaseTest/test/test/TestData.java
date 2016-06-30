package test;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import entity.GlocommAccount;
import entity.SellerAccount;
import entity.UserAccount;
import manager.AccountManager;
import manager.ItemManager;
import user.point.AvatarDataModel;

/**
 * テスト用のデータベースの作成
 * 
 * @author Mutsuki Hiradate
 *
 */
@Named
@RequestScoped
public class TestData implements Serializable {

	@PersistenceContext(unitName = "DatabaseTest")
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	AccountManager am;
	ItemManager im;

	/**
	 * コンストラクタ
	 */
	@PostConstruct
	public void init() {
		am = new AccountManager(em, utx);
		im = new ItemManager(em, utx);
	}

	/**
	 * 
	 */
	public void createData() {
		// 買い手アカウントのデータ作成
		createUserAccount();

		// 売り手アカウントのデータ作成
		createSellerAccount();
	}

	private void createUserAccount() {
		try {
			try {
				utx.begin();
				List<UserAccount> list = em.createNamedQuery(UserAccount.FIND_ALL, UserAccount.class).getResultList();
				list.stream().forEach(s -> em.remove(s));
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		} catch (NoResultException e) {
		}

		try {
			try {
				utx.begin();
				List<GlocommAccount> list = em.createNamedQuery(GlocommAccount.FIND_ALL, GlocommAccount.class)
						.getResultList();
				list.stream().forEach(s -> em.remove(s));
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		} catch (NoResultException e) {
		}

		try {
			utx.begin();
			UserAccount account = new UserAccount();
			account.setGlocommId("user");
			account.setMoney(100000);
			account.setName("user");
			account.setPoint(100);
			account.setRoomNumber(296);

			GlocommAccount ga = new GlocommAccount();
			ga.setId("user");
			ga.setPass("pass");
			
			ga.setUserAccount(account);
			account.setGlocommAccount(ga);
			
			em.persist(ga);
			em.persist(account);
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
		}

		for (int i = 0; i < 10; i++) {
			try {
				utx.begin();
				UserAccount account = new UserAccount();
				account.setGlocommId("user" + i);
				account.setMoney(100000);
				account.setName("user" + i);
				account.setPoint(i * 100);
				account.setRoomNumber(100 + i * 30);

				GlocommAccount ga = new GlocommAccount();
				ga.setId("user" + i);
				ga.setPass("pass");
				
				ga.setUserAccount(account);
				account.setGlocommAccount(ga);
				
				em.persist(ga);
				em.persist(account);
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private void createSellerAccount() {
		try {
			try {
				utx.begin();
				List<SellerAccount> list = em.createNamedQuery(SellerAccount.FIND_ALL, SellerAccount.class)
						.getResultList();
				list.stream().forEach(s -> em.remove(s));
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		} catch (NoResultException e) {
		}

		try {
			utx.begin();
			SellerAccount account = new SellerAccount();
			account.setId("seller");
			account.setPassword("pass");
			account.setMail("test@hoge.com");
			em.persist(account);
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
		}
	}
}
