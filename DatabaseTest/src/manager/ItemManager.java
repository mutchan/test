package manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import entity.Item;
import seller.Category;
import seller.PublicStat;

/**
 * 
 * @author M
 *
 */
public class ItemManager {
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
	public ItemManager(EntityManager em, UserTransaction utx) {
		this.em = em;
		this.utx = utx;
	}

	/**
	 * 商品IDから商品を取得
	 * 
	 * @param itemId
	 *            商品ID
	 * @return 商品
	 */
	public Item getItem(int itemId) {
		try {
			return em.createNamedQuery(Item.BY_ID, Item.class).setParameter("id", itemId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * 全商品の取得
	 * 
	 * @return 全商品のリスト
	 */
	public List<Item> getAllItemList() {
		try {
			return em.createNamedQuery(Item.FIND_ALL, Item.class).getResultList();
		} catch (NoResultException e) {
			return new ArrayList<Item>();
		}
	}

	/**
	 * 商品名による商品の検索
	 * 
	 * @param searchText
	 *            検索文字列
	 * @return 検索文字列を含む商品のリスト
	 */
	public List<Item> getItemListContains(String searchText) {
		try {
			return em.createNamedQuery(Item.BY_NAME, Item.class).setParameter("name", "%" + searchText + "%")
					.getResultList();
		} catch (NoResultException e) {
			return new ArrayList<Item>();
		}
	}

	/**
	 * 
	 * @param name
	 * @param desc
	 * @param stock
	 * @param imagePath
	 * @param category
	 * @param publicStat
	 * @param lastEditDate
	 * @param registDate
	 */
	public void registItem(String name, String desc, int stock, String imagePath, Category category,
			PublicStat publicStat, Date lastEditDate, Date registDate) {
		try {
			utx.begin();
			Item item = new Item();
			item.setName(name);
			item.setDesc(desc);
			item.setStock(stock);
			item.setImagePath(imagePath);
			item.setCategory(category);
			item.setPublicStat(publicStat);
			item.setLastEdit(lastEditDate);
			item.setRegistDate(registDate);

			em.persist(item);
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
	 * 
	 * @param name
	 * @param desc
	 * @param stock
	 * @param imagePath
	 * @param category
	 * @param publicStat
	 * @param lastEditDate
	 * @param registDate
	 */
	public void updateItem(int itemId, String name, String desc, int stock, String imagePath, Category category,
			PublicStat publicStat, Date lastEditDate) {
		try {
			utx.begin();
			Item item = getItem(itemId);
			item.setName(name);
			item.setDesc(desc);
			item.setStock(stock);
			item.setImagePath(imagePath);
			item.setCategory(category);
			item.setPublicStat(publicStat);
			item.setLastEdit(lastEditDate);
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
	 * 商品の削除
	 * 
	 * @param itemId
	 *            商品ID
	 */
	public void removeItem(int itemId) {
		try {
			utx.begin();
			Item item = getItem(itemId);
			em.remove(item);
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
