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

/**
 * カートの中身のDBにアクセスするクラス
 * 
 * @author Mu
 *
 */
public class CartManager {
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
	public CartManager(EntityManager em, UserTransaction utx){
		this.em = em;
		this.utx = utx;
	}
	
	/**
	 * 指定したIDのカートの中身を取得
	 * @param glocommId ユーザーのID
	 * @return カートの中身のリスト
	 */
	public List<Cart> getCartList(String glocommId) {
		try {
			return em.createNamedQuery(Cart.BY_GLOCOMM_ID, Cart.class).setParameter("glocommId", glocommId).getResultList();
		} catch (NoResultException e) {
			return new ArrayList<Cart>();
		}
	}
	
	/**
	 * 指定したIDのカートの中身を削除
	 * @param glocommId ユーザーのID
	 */
	public void removeCartList(String glocommId){
		try {
			utx.begin();
			List<Cart> cart = getCartList(glocommId);
			cart.stream().forEach(em::remove);
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
	 * カートの追加
	 * @param cart 追加するカート
	 */
	public void addCart(Cart cart){
		try {
			utx.begin();
			em.persist(cart);
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
	 * カートの追加
	 * @param cart 追加するカート
	 */
	public void removeCart(Cart cart){
		try {
			utx.begin();
			em.remove(cart);
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
	 * カート内の商品の個数の更新
	 * @param cart 更新するカート
	 * @param count 更新後のカート内の商品の個数
	 */
	public void updateCartCount(int cartId, int count){
		try {
			utx.begin();
			Cart cart = getCart(cartId);
			cart.setCount(count);
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
	 * カートIDからカートの中身を取得
	 * @param cartId
	 * @return
	 */
	private Cart getCart(int cartId) {
		try {
			return em.createNamedQuery(Cart.BY_ID, Cart.class).setParameter("id", cartId).getSingleResult();
		} catch (NoResultException e) {
			throw new IllegalArgumentException("存在しないカートID \"" + cartId + "\" が指定されました");
		}
	}
}
