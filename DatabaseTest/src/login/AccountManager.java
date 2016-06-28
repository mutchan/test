package login;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import entity.PreservedAvatar;
import entity.SellerAccount;
import entity.UserAccount;

/**
 * @author Mu
 *
 */
public class AccountManager {
	EntityManager em;

	/**
	 * コンストラクタ
	 * 
	 * @param em
	 *            エンティティマネージャ
	 */
	public AccountManager(EntityManager em) {
		this.em = em;
	}

	/**
	 * @param id
	 * @param pass
	 * @return IDとパスワードが一致するか
	 */
	public UserAccount checkUser(String id, String pass) {
		try {
			UserAccount account = em.createNamedQuery(UserAccount.BY_GLOCOMM_ID, UserAccount.class)
					.setParameter("glocommId", id).getSingleResult();
			if (pass.equals(account.getGlocommAccount().getPass())) {
				return account;
			}
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * @param id
	 * @param pass
	 * @return IDとパスワードが一致するか
	 */
	public SellerAccount checkSeller(String id, String pass) {
		try {
			List<SellerAccount> account = em.createNamedQuery(SellerAccount.BY_ID, SellerAccount.class)
					.setParameter("id", id).getResultList();

			for (SellerAccount acc : account) {
				if (pass.equals(acc.getPassword())) {
					System.out.println("OK");
					return acc;
				}
			}
		} catch (NoResultException e) {
			return null;
		}
		return null;
	}

	/**
	 * 
	 * @param glocommId
	 * @return
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
	 * ログイン処理
	 * 
	 * @param account
	 *            アカウント
	 */
	public void userLogin(UserAccount account) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("UserAccount", account);
	}

	/**
	 * 
	 * ログアウト処理
	 * @return ログアウト画面への遷移 
	 */
	public void userLogout() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("UserAccount", null);
	}
	
	/**
	 * 
	 * @return
	 */
	public UserAccount getCurrentUserAccount(){
		Object tmp = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserAccount");

		if (tmp instanceof UserAccount) {
			return (UserAccount)tmp;
		}
		return null;
	}

	/**
	 * ログイン処理
	 * 
	 * @param account
	 *            アカウント
	 */
	public void sellerLogin(SellerAccount account) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("SellerAccount", account);
	}

	/**
	 * 
	 * ログアウト処理
	 * @return ログアウト画面への遷移 
	 */
	public void sellerLogout() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("SellerAccount", null);
	}
	
	
	/**
	 * 
	 * @return
	 */
	public SellerAccount getCurrentSellerAccount(){
		Object tmp = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("SellerAccount");

		if (tmp instanceof SellerAccount) {
			return (SellerAccount)tmp;
		}
		return null;
	}
}
