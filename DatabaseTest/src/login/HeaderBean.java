package login;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import entity.SellerAccount;
import manager.AccountManager;

/**
 * @author M
 *
 */
@SuppressWarnings("serial")
@Named
@SessionScoped
public class HeaderBean implements Serializable {

	@PersistenceContext(unitName = "DatabaseTest")
	private EntityManager em;

	@Resource
	UserTransaction utx;

	private AccountManager am = new AccountManager(em, utx);
	
	/**
	 * 
	 * ログアウト処理
	 * @return ログアウト画面への遷移 
	 */
	public String logout() {
		am.sellerLogout();
		return "/login.xhtml?faces-redirect=true";
	}

	/**
	 * ログインしているかの確認
	 */
	public void isLoggedIn() {
		if (am.getCurrentSellerAccount() == null) {
			ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance()
					.getApplication().getNavigationHandler();
			handler.performNavigation("/login");
		}
	}

	/**
	 * @return account
	 */
	public SellerAccount getAccount() {
		return am.getCurrentSellerAccount();
	}
}
