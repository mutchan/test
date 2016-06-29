package login;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import entity.UserAccount;

public class AccountManagerTest {

	@Test
	public void test() {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("DatabaseTest");
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
			for (int i = 1; i <= 1_000_000; i++) {
				UserAccount user = new UserAccount();
				user.setGlocommId(String.valueOf((int) (Math.random() * 10000)));
				em.persist(user);
				if (i % 100 == 0) {
					em.flush();
					em.clear();
				}
			}
		} finally {
			tx.commit();
		}
	}

}
