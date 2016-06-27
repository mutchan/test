package entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Avatar
 *
 */
@Entity

public class Avatar implements Serializable {


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int imageId;
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
	public int getImageId() {
		return this.imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
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
