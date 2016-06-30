package test;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import entity.Avatar;
import entity.Bbs;
import entity.Cart;
import entity.GlocommAccount;
import entity.Item;
import entity.PreservedAvatar;
import entity.Review;
import entity.Sales;
import entity.SellerAccount;
import entity.UserAccount;
import manager.AccountManager;
import manager.ItemManager;
import seller.Category;
import seller.PublicStat;
import user.point.AvatarDataModel;

/**
 * テスト用のデータベースの作成
 * 
 * @author Mutsuki Hiradate
 *
 */
@Named
@RequestScoped
public class TestData implements Serializable {

	@PersistenceContext(unitName = "DatabaseTest")
	private EntityManager em;

	@Resource
	private UserTransaction utx;

	AccountManager am;
	ItemManager im;

	static final int USER_ACCOUNT_NUM = 10;
	static final int AVATAR_NUM = 10;
	static final int PRESERVED_AVATAR_NUM = 10;
	static final int REVIEW_NUM = 10;
	static final int SALES_NUM = 10;
	static final int CART_NUM = 10;

	List<UserAccount> userAccount = new ArrayList<UserAccount>();
	List<Avatar> avatarList = new ArrayList<Avatar>();
	List<Item> itemList = new ArrayList<Item>();

	/**
	 * コンストラクタ
	 */
	@PostConstruct
	public void init() {
		am = new AccountManager(em, utx);
		im = new ItemManager(em, utx);
	}

	/**
	 * 
	 */
	public void createData() {
		// データベースのクリア
		clearDatabase();

		// 買い手アカウントのデータ作成
		createUserAccount();

		// 売り手アカウントのデータ作成
		createSellerAccount();

		// アバターのデータ作成
		createAvatar();

		// 商品のデータ作成
		createItem();

		// 商品レビューのデータ作成
		createReview();

		// 売上のデータ作成
		createSales();

		// カートのデータ作成
		createCart();
	}

	private void clearDatabase() {
		try {
			try {
				utx.begin();
				List<SellerAccount> list = em.createNamedQuery(SellerAccount.FIND_ALL, SellerAccount.class)
						.getResultList();
				list.stream().forEach(s -> em.remove(s));
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		} catch (NoResultException e) {
		}

		try {
			try {
				utx.begin();
				List<PreservedAvatar> list = em.createNamedQuery(PreservedAvatar.FIND_ALL, PreservedAvatar.class)
						.getResultList();
				list.stream().forEach(s -> em.remove(s));
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		} catch (NoResultException e) {
		}

		try {
			try {
				utx.begin();
				List<Avatar> list = em.createNamedQuery(Avatar.FIND_ALL, Avatar.class).getResultList();
				list.stream().forEach(s -> em.remove(s));
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		} catch (NoResultException e) {
		}

		try {
			try {
				utx.begin();
				List<Review> list = em.createNamedQuery(Review.FIND_ALL, Review.class).getResultList();
				list.stream().forEach(s -> em.remove(s));
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		} catch (NoResultException e) {
		}

		try {
			try {
				utx.begin();
				List<Sales> list = em.createNamedQuery(Sales.FIND_ALL, Sales.class).getResultList();
				list.stream().forEach(s -> em.remove(s));
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		} catch (NoResultException e) {
		}

		try {
			try {
				utx.begin();
				List<Cart> list = em.createNamedQuery(Cart.FIND_ALL, Cart.class).getResultList();
				list.stream().forEach(s -> em.remove(s));
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		} catch (NoResultException e) {
		}

		try {
			try {
				utx.begin();
				List<Item> list = em.createNamedQuery(Item.FIND_ALL, Item.class).getResultList();
				list.stream().forEach(s -> em.remove(s));
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		} catch (NoResultException e) {
		}

		try {
			try {
				utx.begin();
				List<Bbs> list = em.createNamedQuery(Bbs.FIND_ALL, Bbs.class).getResultList();
				list.stream().forEach(s -> em.remove(s));
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		} catch (NoResultException e) {
		}
		
		try {
			try {
				utx.begin();
				List<UserAccount> list = em.createNamedQuery(UserAccount.FIND_ALL, UserAccount.class).getResultList();
				list.stream().forEach(s -> em.remove(s));
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		} catch (NoResultException e) {
		}

		try {
			try {
				utx.begin();
				List<GlocommAccount> list = em.createNamedQuery(GlocommAccount.FIND_ALL, GlocommAccount.class)
						.getResultList();
				list.stream().forEach(s -> em.remove(s));
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		} catch (NoResultException e) {
		}
	}

	private void createUserAccount() {

		try {
			utx.begin();
			UserAccount account = new UserAccount();
			account.setGlocommId("user");
			account.setMoney(100000);
			account.setName("user");
			account.setPoint(100);
			account.setRoomNumber(296);

			GlocommAccount ga = new GlocommAccount();
			ga.setId("user");
			ga.setPass("pass");

			ga.setUserAccount(account);
			account.setGlocommAccount(ga);

			em.persist(ga);
			em.persist(account);
			userAccount.add(account);
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
		}

		for (int i = 0; i < USER_ACCOUNT_NUM; i++) {
			try {
				utx.begin();
				UserAccount account = new UserAccount();
				account.setGlocommId("user" + i);
				account.setMoney(100000);
				account.setName("user" + i);
				account.setPoint(i * 100);
				account.setRoomNumber(100 + i * 30);

				GlocommAccount ga = new GlocommAccount();
				ga.setId("user" + i);
				ga.setPass("pass");

				ga.setUserAccount(account);
				account.setGlocommAccount(ga);

				em.persist(ga);
				em.persist(account);
				utx.commit();
				userAccount.add(account);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private void createSellerAccount() {

		try {
			utx.begin();
			SellerAccount account = new SellerAccount();
			account.setId("seller");
			account.setPassword("pass");
			account.setMail("test@hoge.com");
			em.persist(account);
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void createAvatar() {
		for (int i = 0; i < AVATAR_NUM; i++) {
			try {
				utx.begin();
				Avatar avatar = new Avatar();
				avatar.setName("景品" + i);
				avatar.setPoint((int) (Math.random() * 500));
				avatar.setImagePath("/avatar_img/" + i + ".png");
				em.persist(avatar);
				utx.commit();
				avatarList.add(avatar);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		}

		for (int i = 0; i < PRESERVED_AVATAR_NUM; i++) {
			try {
				utx.begin();
				PreservedAvatar pa = new PreservedAvatar();
				pa.setAvatar(avatarList.get((int) (Math.random() * AVATAR_NUM)));
				pa.setAccountId("user" + (int) (Math.random() * USER_ACCOUNT_NUM));
				DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
				int year = (int) (Math.random() * 16);
				int month = (int) (Math.random() * 12);
				int day = (int) (Math.random() * 20);
				pa.setDate(format.parse("20" + year + "-" + month + "-" + day));
				em.persist(pa);
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private void createItem() {
		// List<Item> itemList = new ItemManagerStub().getItemList();

		for (int i = 0; i < 100; i++) {
			try {
				utx.begin();
				Item item = new Item();
				item.setName("商品" + i);
				item.setDesc(getRandomString(255));
				item.setCategory(Category.values()[(int) (Math.random() * 3)]);
				item.setStock((int) (item.getCategory().getInitStock() * Math.random() * 2));
				item.setImagePath("/product_img/" + i + ".jpg");
				item.setPublicStat(PublicStat.values()[(int) (Math.random() * 2)]);

				DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
				int year = (int) (Math.random() * 16);
				int month = (int) (Math.random() * 12);
				int day = (int) (Math.random() * 20);
				item.setLastEdit(format.parse("20" + year + "-" + month + "-" + day));
				item.setRegistDate(format.parse("20" + year + "-" + month + "-" + (day + 2)));
				// item.setReviewList(new ArrayList<Review>());

				em.persist(item);
				utx.commit();
				itemList.add(item);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private void createReview() {		
		for (int i = 0; i < REVIEW_NUM; i++) {
			try {
				utx.begin();
				Review review = new Review();
				review.setItemId(itemList.get((int) (Math.random() * 100)).getId());
				review.setImageId((int) (Math.random() * AVATAR_NUM));
				review.setComment((getRandomString(255)));
				review.setAccount(userAccount.get((int) (Math.random() * USER_ACCOUNT_NUM)));
				review.setStar((int) (Math.random() * 5));
				review.setGoodCount((int) (Math.random() * 100));

				DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
				int year = (int) (Math.random() * 16);
				int month = (int) (Math.random() * 12);
				int day = (int) (Math.random() * 20);
				review.setDate(format.parse("20" + year + "-" + month + "-" + (day + 2)));

				em.persist(review);
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private void createSales() {
		List<UserAccount> userAccount = em.createNamedQuery(UserAccount.FIND_ALL, UserAccount.class).getResultList();
		for (int i = 0; i < SALES_NUM; i++) {
			try {
				utx.begin();
				Sales sales = new Sales();
				sales.setItem(itemList.get((int) (Math.random() * 100)));
				sales.setPrice((int) (Math.random() * 1000) * 100);
				sales.setCount((int) (Math.random() * 100));
				sales.setAccount(userAccount.get((int) (Math.random() * userAccount.size())));

				DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
				int year = (int) (Math.random() * 16);
				int month = (int) (Math.random() * 12);
				int day = (int) (Math.random() * 20);
				sales.setDate(format.parse("20" + year + "-" + month + "-" + (day + 2)));

				em.persist(sales);
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

	private void createCart() {
		List<Item> itemList = em.createNamedQuery(Item.FIND_ALL, Item.class).getResultList();
		for (int i = 0; i < SALES_NUM; i++) {
			try {
				Item item = itemList.get((int) (Math.random() * itemList.size()));
				utx.begin();
				Cart cart = new Cart();
				cart.setItem(item);
				cart.setCount((int) (Math.random() * 100));
				cart.setAccountId("user" + (int) (Math.random() * USER_ACCOUNT_NUM));

				em.persist(cart);
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					utx.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * ランダムな文字列の取得
	 * 
	 * @param maxLength
	 *            文字列の長さ
	 * @return
	 */
	private String getRandomString(int maxLength) {
		String ret = "";
		int length = (int) (Math.random() * maxLength);
		for (int i = 0; i < length; i++) {
			char c = (char) (Math.random() * 255);
			ret += c;
		}

		return ret;
	}
}
