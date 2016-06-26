package seller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Part;
import javax.transaction.UserTransaction;

/**
 * @author Mu
 *
 */
@Named
@RequestScoped
public class ProductCreater implements Serializable {

	@PersistenceContext(unitName = "DatabaseTest")
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1978891714396809992L;

	private OpenInfo openInfo;
	private String name;
	private String desc;
	private Category category;
	private int count;
	private Part file;
	
    /**
     * 
     */
    @PostConstruct
    public void init() {
		count = 0;
		category = Category.A;
    }

	/**
	 * @return openInfo
	 */
	public OpenInfo getOpenInfo() {
		return openInfo;
	}

	/**
	 * @param openInfo セットする openInfo
	 */
	public void setOpenInfo(OpenInfo openInfo) {
		this.openInfo = openInfo;
	}

	/**
	 * 公開のリストの取得
	 * 
	 * @return 公開のリスト
	 */
	public OpenInfo[] getOpenInfoList() {
		return OpenInfo.values();
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
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            セットする category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * 商品カテゴリのリストの取得
	 * 
	 * @return 商品カテゴリのリスト
	 */
	public Category[] getCategoryList() {
		return Category.values();
	}

	/**
	 * @return count
	 */
	public int getCount() {
		return category.getInitStock();
	}

	/**
	 * @param count
	 *            セットする count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 
	 * @return null
	 */
	public String regist() {
		StockOperation stockManager = new StockOperation(em, utx);
		try {
			stockManager.regist(desc, count, file, name, category, openInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
