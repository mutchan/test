package login;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import entity.Item;

/**
 * 
 * @author M
 *
 */
public class ItemManager {
	EntityManager em;

	/**
	 * コンストラクタ
	 * 
	 * @param em
	 *            エンティティマネージャ
	 */
	public ItemManager(EntityManager em) {
		this.em = em;
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
	 * @param searchText 検索文字列
	 * @return 検索文字列を含む商品のリスト
	 */
	public List<Item> getItemListContains(String searchText){
		try {
			return em.createNamedQuery(Item.BY_NAME, Item.class).setParameter("name", "%" + searchText + "%").getResultList();
		} catch (NoResultException e) {
			return new ArrayList<Item>();
		}
	}
}
