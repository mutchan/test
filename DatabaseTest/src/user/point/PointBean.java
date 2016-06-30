package user.point;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import entity.Item;
import manager.ItemManager;

@ManagedBean
@ViewScoped
public class PointBean {

	@PersistenceContext(unitName = "DatabaseTest")
	private EntityManager em;

	@Resource
	UserTransaction utx;

	ItemManager im;

	// 一覧データ
	private AvatarDataModel avatarList;

	// チェックで選択されたBookリスト
	private List<Item> selectedAvatars;

	/**
	 * コンストラクタ
	 */
	@PostConstruct
	public void init() {
		// 表示データ取得
		im = new ItemManager(em, utx);
		setAvatarList(new AvatarDataModel(im.getAllItemList()));
	}

	/**
	 * @return avatarList
	 */
	public AvatarDataModel getAvatarList() {
		return avatarList;
	}

	/**
	 * @param avatarList
	 *            セットする avatarList
	 */
	public void setAvatarList(AvatarDataModel avatarList) {
		this.avatarList = avatarList;
	}

	/**
	 * @return selectedAvatars
	 */
	public List<Item> getSelectedAvatars() {
		return selectedAvatars;
	}

	/**
	 * @param selectedAvatars
	 *            セットする selectedAvatars
	 */
	public void setSelectedAvatars(List<Item> selectedAvatars) {
		this.selectedAvatars = selectedAvatars;
	}

	public int getLastPoint() {
		int total = 0;
		if (selectedAvatars != null) {
			for (Item item : selectedAvatars) {
				total += item.getId();
			}
		}

		return total;
	}

	public void exchange() {
		;
	}
}
