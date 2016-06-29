package login;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.junit.Test;

import entity.UserAccount;

public class AccountManagerTest {

	@Test
	public void test() {

		// EntityManagerを作成する際の引数に
		// eclipselink.persistencexml={persistence.xmlのクラスパス}を渡す
		final Map<String, String> map = new HashMap<>();
		map.put(PersistenceUnitProperties.ECLIPSELINK_PERSISTENCE_XML, "META-INF/test-persistence.xml");

		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("DatabaseTest", map);
			em = emf.createEntityManager();

			insertMillionRows(em);
		} finally {
			em.close();
			emf.close();
		}
	}

	private void insertMillionRows(EntityManager em) {
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			for (int i = 1; i <= 10; i++) {
				UserAccount user = new UserAccount();
				user.setGlocommId(String.valueOf((int) (Math.random() * 10000)));
				em.persist(user);
			}
		} finally {
			tx.commit();
		}
	}

}
