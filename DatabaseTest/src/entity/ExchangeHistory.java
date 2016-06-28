package entity;

import entity.Avatar;
import entity.PreservedAvatar;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ExchangeHistory
 *
 */
@NamedQuery(name = "ExchangeHistory.byGlocommID", query = "select new entity.ExchangeHistory(pa, a) from PreservedAvatar pa join Avatar a on pa.avatarId = a.id")
public class ExchangeHistory implements Serializable {

	private PreservedAvatar preservedAvatar;
	private Avatar avatar;
	private static final long serialVersionUID = 1L;

	public ExchangeHistory(PreservedAvatar preservedAvatar, Avatar avatar) {
		super();
		this.avatar = avatar;
		this.preservedAvatar = preservedAvatar;
	}

	public PreservedAvatar getPreservedAvatar() {
		return this.preservedAvatar;
	}

	public void setPreservedAvatar(PreservedAvatar preservedAvatar) {
		this.preservedAvatar = preservedAvatar;
	}

	public Avatar getAvatar() {
		return this.avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

}
