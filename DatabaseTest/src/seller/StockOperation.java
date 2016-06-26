package seller;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.sun.xml.ws.util.StringUtils;

/**
 * 在庫管理を行うクラス
 * 
 * @author Mu
 *
 */
public class StockOperation {

	private EntityManager em;
	private UserTransaction utx;

	/**
	 * 
	 * @param em
	 *            エンティティマネージャ
	 * @param utx
	 *            ユーザートランザクション
	 */
	public StockOperation(EntityManager em, UserTransaction utx) {
		this.em = em;
		this.utx = utx;
	}

	private Date getCurrentDate() {
		java.util.Date date = new java.util.Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Date(cal.getTimeInMillis());
	}

	/**
	 * 
	 * @param description
	 *            商品説明
	 * @param count
	 *            在庫
	 * @param file
	 *            画像のパス
	 * @param name
	 *            名前
	 * @param openInfo
	 *            公開フラグ
	 * @param category
	 *            商品カテゴリ
	 * @throws IOException
	 *             ファイルのアップロードに失敗したときに発生
	 */
	public void regist(String description, int count, Part file, String name, Category category, OpenInfo openInfo)
			throws IOException {
		// 画像ディレクトリの取得
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String filePath = ctx.getRealPath("product_img/");

		// ファイル名の設定
		File[] listFiles = new File(filePath).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.matches(".*\\.(jpg|gif|png)$");
			}
		});
		String fileName = String.format("/%06d.", listFiles.length) + getExtension(file.getSubmittedFileName());

		System.out.println(filePath);
		File imageFile = new File(filePath + fileName);
		Files.copy(file.getInputStream(), imageFile.toPath());

		try {
			utx.begin();
			Stock stock = new Stock();
			stock.setCount(count);
			stock.setDescription(description);
			stock.setImgPath(imageFile.toPath().toString());
			stock.setLastEdit(getCurrentDate());
			stock.setName(name);
			stock.setOpenInfo(openInfo);
			stock.setCategory(category);

			em.persist(stock);
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

	private String getExtension(String fileName) {
		if (fileName == null) {
			return null;
		}

		// 最後の『 . 』の位置を取得します。
		int lastDotPosition = fileName.lastIndexOf(".");

		// 『 . 』が存在する場合は、『 . 』以降を返します。
		if (lastDotPosition != -1) {
			return fileName.substring(lastDotPosition + 1);
		}
		return null;
	}

	/**
	 * 
	 * @return 在庫リスト
	 */
	public LazyDataModel<Stock> getStockList() {
		List<Stock> stockList = em.createQuery("select l from Stock l", Stock.class).getResultList();

		@SuppressWarnings("serial")
		class LazyStockDataModel extends LazyDataModel<Stock> {
			private List<Stock> stockList;

			public LazyStockDataModel(List<Stock> stockList) {
				this.stockList = stockList;
			}

			public java.util.List<Stock> load(int first, int pageSize, String sortField,
					org.primefaces.model.SortOrder sortOrder, java.util.Map<String, Object> filters) {
				// sort
				if (sortField != null) {
					Collections.sort(stockList, new LazySorter(sortField, sortOrder));
				}

				// rowCount
				int dataSize = stockList.size();
				this.setRowCount(dataSize);

				// paginate
				if (dataSize > pageSize) {
					try {
						return stockList.subList(first, first + pageSize);
					} catch (IndexOutOfBoundsException e) {
						// 最終ページの表示
						return stockList.subList(first, first + (dataSize % pageSize));
					}
				} else {
					return stockList;
				}
			}

			@Override
			public Object getRowKey(Stock stock) {
				return stock.getId();
			}

			@Override
			public Stock getRowData(String stockId) {
				Integer id = Integer.valueOf(stockId);

				for (Stock stock : stockList) {
					if (id.equals(stock.getId())) {
						return stock;
					}
				}

				return null;
			}

			class LazySorter implements Comparator<Stock> {

				private String sortField;

				private SortOrder sortOrder;

				public LazySorter(String sortField, SortOrder sortOrder) {
					this.sortField = sortField;
					this.sortOrder = sortOrder;
				}

				public int compare(Stock stock1, Stock stock2) {
					try {
						Method method = Stock.class.getMethod("get" + StringUtils.capitalize(this.sortField));

						try {
							Object value1 = method.invoke(stock1);
							Object value2 = method.invoke(stock2);
							value2 = method.invoke(stock2);

							@SuppressWarnings("unchecked")
							int value = ((Comparable<Object>) value1).compareTo(value2);
							return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
						} catch (IllegalAccessException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}
					} catch (NoSuchMethodException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}

					return 0;
				}
			}
		}

		return new LazyStockDataModel(stockList);
	}
}
