package login;

/**
 * @author Mu
 *
 */
public interface AccountManager {
	/**
	 * @param id
	 * @param pass
	 * @return アカウントが存在するか
	 */
	boolean Check(String id, String pass);
}
