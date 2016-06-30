package entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Avatar
 *
 */
@Entity
@NamedQuery(name = Avatar.FIND_ALL, query = "Select c from Avatar c")
public class Avatar implements Serializable {
	public static final String FIND_ALL = "Avatar.findAll";

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="IMAGE_PATH")
	private String imagePath;
	private int point;
	private String name;
	private static final long serialVersionUID = 1L;

	public Avatar() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}   
	public int getPoint() {
		return this.point;
	}

	public void setPoint(int point) {
		this.point = point;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
   
}
