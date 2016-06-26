package lunch;

import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * @author Mutsuki Hiradate
 *
 */
@Named
@RequestScoped
public class TableData {

	@PersistenceContext(unitName = "DatabaseTest")
	private EntityManager em;

	// 新規行データ
	private String name;
	private int price;
	private int stock;

	@Resource
	UserTransaction utx;

	/**
	 * @return rowData
	 */
	public List<Lunch> getRowData() {
		List<Lunch> lunch = em.createQuery("select l from Lunch l", Lunch.class).getResultList();
		return lunch;
	}

	/**
	 * @return 画面遷移はなし
	 */
	public String add() {
		try {
			utx.begin();

			Lunch lunch = new Lunch();
			lunch.setName(name);
			lunch.setPrice(price);
			lunch.setStock(stock);

			em.persist(lunch);
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

		return null;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            セットする price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock
	 *            セットする stock
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}
}
