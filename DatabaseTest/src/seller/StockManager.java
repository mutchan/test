package seller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Part;
import javax.transaction.UserTransaction;

/**
 * @author Mu
 *
 */
@ManagedBean(name="stockManager")
@ViewScoped
public class StockManager implements Serializable {

	@PersistenceContext(unitName = "DatabaseTest")
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1978891714396809992L;

	private String name;
	private String desc;
	private String category;
	private String count;
	private Part file;

	private List<Stock> stockList;

	private Stock selectedStock;
	
    /**
     * 
     */
    @PostConstruct
    public void init() {
    	stockList = em.createQuery("select l from Stock l", Stock.class).getResultList();
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
	 * @return desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            セットする desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return file
	 */
	public Part getFile() {
		return file;
	}

	/**
	 * @param file
	 *            セットする file
	 */
	public void setFile(Part file) {
		this.file = file;
	}

	/**
	 * @return category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            セットする category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return count
	 */
	public String getCount() {
		return count;
	}

	/**
	 * @param count
	 *            セットする count
	 */
	public void setCount(String count) {
		this.count = count;
	}

	/**
	 * 在庫リストの取得
	 * 
	 * @return 在庫リスト
	 */
	public List<Stock> getStockList() {
		return stockList;
	} 
	
	/**
	 * 
	 * @return 選択された在庫
	 */
    public Stock getSelectedStock() {
        return selectedStock;
    }
 
    /**
     * 
     * @param selectedStock 選択された在庫
     */
    public void setSelectedStock(Stock selectedStock) {
        this.selectedStock = selectedStock;
    }

	/**
	 * 
	 * @return null
	 */
	public String add() {
		return null;
	}
}