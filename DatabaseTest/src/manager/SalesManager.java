package manager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import entity.Cart;
import entity.Sales;
import entity.UserAccount;

/**
 * 売上のDBにアクセスするクラス
 * 
 * @author Mu
 *
 */
public class SalesManager {
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
	public SalesManager(EntityManager em, UserTransaction utx) {
		this.em = em;
		this.utx = utx;
	}

	/**
	 * すべての売上データを取得
	 * 
	 * @return すべての売上データのリスト
	 */
	public List<Sales> getSalesList() {
		try {
			return em.createNamedQuery(Sales.FIND_ALL, Sales.class).getResultList();
		} catch (NoResultException e) {
			return new ArrayList<Sales>();
		}
	}

	/**
	 * 売上IDから売上データを取得
	 * 
	 * @param salesId
	 *            売上ID
	 * @return 指定された売上データ
	 */
	public Sales getSales(int salesId) {
		try {
			return em.createNamedQuery(Sales.BY_ID, Sales.class).setParameter("id", salesId).getSingleResult();
		} catch (NoResultException e) {
			throw new IllegalArgumentException("存在しない売上ID \"" + salesId + "\" が指定されました");
		}
	}

	/**
	 * ユーザーアカウントから売上データのリストを取得
	 * 
	 * @param userAccount
	 *            ユーザーアカウント
	 * @return 売上データのリスト
	 */
	public List<Sales> getSales(UserAccount userAccount) {
		try {
			return em.createNamedQuery(Sales.BY_ACCOUNT, Sales.class).setParameter("account", userAccount).getResultList();
		} catch (NoResultException e) {
			throw new IllegalArgumentException("アカウント \"" + userAccount.getGlocommId() + "\" の商品購入はありません");
		}
	}

	/**
	 * 売上データの追加
	 * 
	 * @param sales
	 *            追加する売上データ
	 */
	public void addSales(Sales sales) {
		try {
			utx.begin();
			em.persist(sales);
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
	 * 売上データの削除
	 * 
	 * @param sales
	 *            削除する売上データ
	 */
	public void removeCart(Sales sales) {
		try {
			utx.begin();
			em.remove(sales);
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

}
