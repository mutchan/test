package lunch;

import javax.persistence.EntityManager;

/**
 * @author Mutsuki Hiradate
 *
 */
public class Main {
	private EntityManager em;

	/**
	 * @param arg
	 *            未使用
	 */
	public static void main(String[] arg) {
		Main mainInstance = new Main();
		
		mainInstance.apply();
	}

	private void apply() {		
		try {
			em.getTransaction().begin();

			LunchEntity foo = new LunchEntity();
			foo.setName("food");
			foo.setPrice(314);

			em.persist(foo);

			em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			em.close();
		}
	}
}
