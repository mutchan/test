package seller;

/**
 * 
 * @author Mu
 *
 */
public enum OpenInfo {
	/**
	 * 商品を公開
	 */
	PUBLIC("公開"), 
	/**
	 * 商品を非公開
	 */
	PRIVATE("非公開");
	
	String label;
	OpenInfo(String label){
		this.label = label;
	}
	
	@Override
	public String toString() {
		return label;
	}
}
