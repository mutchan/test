package manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import entity.Cart;
import entity.PreservedAvatar;
import entity.SellerAccount;
import entity.UserAccount;

/**
 * @author Mu
 *
 */
public class AccountManager {
	EntityManager em;
	UserTransaction utx;

	/**
	 * コンストラクタ
	 * 
	 * @param em
	 *            エンティティマネージャ
	 * @param utx
	 *            ユーザートランザクション
	 */
	public AccountManager(EntityManager em, UserTransaction utx) {
		this.em = em;
		this.utx = utx;
	}

	/**
	 * 買い手側のログイン
	 * 
	 * @param glocommId
	 *            グロコミID
	 * @param pass
	 *            パスワード
	 * @return IDとパスワードが一致するか
	 */
	public UserAccount userLogin(String glocommId, String pass) {
		try {
			UserAccount account = em.createNamedQuery(UserAccount.BY_GLOCOMM_ID, UserAccount.class)
					.setParameter("glocommId", glocommId).getSingleResult();
			if (pass.equals(account.getGlocommAccount().getPass())) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("UserAccount", account);
				return account;
			}
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * 
	 * ログアウト処理
	 */
	public void userLogout() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("UserAccount", null);
	}

	/**
	 * ログイン中の買い手アカウントを取得
	 * 
	 * @return ログイン中の買い手アカウント。ログインしていないときにはnullを返す。
	 */
	public UserAccount getCurrentUserAccount() {
		Object tmp = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserAccount");

		if (tmp instanceof UserAccount) {
			return (UserAccount) tmp;
		}
		return null;
	}

	/**
	 * 買い手側の名前の変更
	 * 
	 * @param name
	 *            新規名前
	 */
	public void updatUserName(String name) {
		try {
			utx.begin();
			UserAccount account = em.createNamedQuery(UserAccount.BY_GLOCOMM_ID, UserAccount.class)
					.setParameter("glocommId", getCurrentUserAccount().getGlocommId()).getSingleResult();
			account.setName(name);
			utx.commit();
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();

			try {
				utx.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * グロコミIDから、ユーザーが所持する画像の検索を行う
	 * 
	 * @param glocommId
	 *            グロコミID
	 * @return ユーザーが所持する画像のリスト
	 */
	public List<PreservedAvatar> getPreservedAvatar(String glocommId) {
		try {
			return em.createNamedQuery(PreservedAvatar.BY_GLOCOMM_ID, PreservedAvatar.class).setParameter(1, glocommId)
					.getResultList();
		} catch (NoResultException e) {
			return new ArrayList<PreservedAvatar>();
		}
	}

	/**
	 * 売り手側のログイン
	 * 
	 * @param id
	 *            ID
	 * @param pass
	 *            パスワード
	 * @return IDとパスワードが一致するか
	 */
	public SellerAccount sellerLogin(String id, String pass) {
		try {
			List<SellerAccount> accountList = em.createNamedQuery(SellerAccount.BY_ID, SellerAccount.class)
					.setParameter("id", id).getResultList();

			for (SellerAccount account : accountList) {
				if (pass.equals(account.getPassword())) {
					System.out.println("OK");
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("SellerAccount",
							account);
					return account;
				}
			}
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * 
	 * ログアウト処理
	 */
	public void sellerLogout() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("SellerAccount", null);
	}

	/**
	 * ログイン中の売り手アカウントを取得
	 * 
	 * @return ログイン中の売り手アカウント。ログインしていないときにはnullを返す。
	 */
	public SellerAccount getCurrentSellerAccount() {
		Object tmp = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("SellerAccount");

		if (tmp instanceof SellerAccount) {
			return (SellerAccount) tmp;
		}
		return null;
	}

	/**
	 * 売り手側のパスワードの変更
	 * 
	 * @param pass
	 *            新規パスワード
	 */
	public void updateSellerPassword(String pass) {
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			SellerAccount account = em.createNamedQuery(SellerAccount.BY_NUMBER, SellerAccount.class)
					.setParameter("number", getCurrentSellerAccount().getNumber()).getSingleResult();
			account.setPassword(pass);
			tx.commit();
		} catch (SecurityException | IllegalStateException e) {
			e.printStackTrace();

			try {
				utx.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
		}
	}
}
