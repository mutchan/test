package user.point;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

import entity.Item;

public class AvatarDataModel extends ListDataModel<Item> implements SelectableDataModel<Item> {
	/**
	 * アバターのリストの設定
	 * @param data アバターのリスト
	 */
	public AvatarDataModel(List<Item> data) {
		super(data);
	}

	
	@Override
	public Object getRowKey(Item item) {
		return item.getId();
	}

	@Override
	public Item getRowData(String rowKey) {
		// アバターリストの取得
		List<Item> itemList = (List<Item>) getWrappedData();
		
		// キーに一致する要素を検索
		int key = Integer.parseInt(rowKey);
		return itemList.stream().filter(s -> s.getId() == key).findFirst().get();
	}
}
