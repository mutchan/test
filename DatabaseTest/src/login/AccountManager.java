package login;

import java.util.ArrayList;
import java.util.List;

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
	boolean checkUser(String id, String pass) {
		try {
			UserAccount account = em.createNamedQuery(UserAccount.BY_GLOCOMM_ID, UserAccount.class)
					.setParameter("glocommId", id).getSingleResult();

			return pass.equals(account.getGlocommAccount().getPass());
		} catch (NoResultException e) {
			return false;
		}
	}

	/**
	 * @param id
	 * @param pass
	 * @return IDとパスワードが一致するか
	 */
	boolean checkSeller(String id, String pass) {
		try {
			List<SellerAccount> account = em.createNamedQuery(SellerAccount.BY_ID, SellerAccount.class)
					.setParameter("id", id).getResultList();

			for (SellerAccount acc : account) {
				if (pass.equals(acc.getPassword())) {
					System.out.println("OK");
					return true;
				}
			}
		} catch (NoResultException e) {
			return false;
		}
		return false;
	}

	/**
	 * 
	 * @param glocommId
	 * @return
	 */
	public List<PreservedAvatar> getPreservedAvatar(String glocommId) {
		try {
			return em.createNamedQuery(PreservedAvatar.BY_GLOCOMM_ID, PreservedAvatar.class)
					.setParameter(1, glocommId).getResultList();
		} catch (NoResultException e) {
			return new ArrayList<PreservedAvatar>();
		}
	}
}
