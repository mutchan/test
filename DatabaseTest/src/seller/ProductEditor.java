package seller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.primefaces.model.UploadedFile;

/**
 * @author Mu
 *
 */
@Named
@RequestScoped
public class ProductEditor implements Serializable {

	private String name;
	private String desc;
	private UploadedFile uploadedFile;
    private Part file;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 253066333200638480L;

	/**
	 * 
	 * @return 増分の取得
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param  name 増分
	 */
	public void setName(String name) {
		this.name = name;
		setDesc(name);
		System.out.println(this.name);
	}
	
	/**
	 * 
	 * @return null
	 */
	public String regist(){
		System.out.println("Regist");
		setName("テスト");
		return null;
	}

	/**
	 * @return desc 
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc セットする desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return uploadedFile
	 */
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	/**
	 * @param uploadedFile セットする uploadedFile
	 */
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	/**
	 * @return file
	 */
	public Part getFile() {
		return file;
	}

	/**
	 * @param file セットする file
	 */
	public void setFile(Part file) {
		this.file = file;
	}
}
