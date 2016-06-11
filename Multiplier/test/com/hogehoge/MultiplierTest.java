package com.hogehoge;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

/**
 * のテスト
 * 
 * @author
 *
 */
public class MultiplierTest {

	private static final int NORIBEN_PRICE = 290;
	private static final int SYAKEBEN_PRICE = 340;
	private static final int MAKUNOUCHI_PRICE = 180;

	/**
	 * 
	 */
	@Test
	public void testPrice() {
		Multiplier taxCalculator = new Multiplier();

		assertThat(taxCalculator.getNoribenPrice(), is(NORIBEN_PRICE));
		assertThat(taxCalculator.getSyakebenPrice(), is(SYAKEBEN_PRICE));
		assertThat(taxCalculator.getMakunouchiPrice(), is(MAKUNOUCHI_PRICE));
	}

	/**
	 * 
	 */
	@Test
	public void testTax05() {
		Multiplier taxCalculator = new Multiplier();

		taxCalculator.setNoribenCount("1");
		taxCalculator.setSyakebenCount("1");
		taxCalculator.setMakunouchiCount("1");
		taxCalculator.setTax05();
		assertThat(taxCalculator.getNoribenSubtotal(), is((int) (NORIBEN_PRICE * 1.05)));
		assertThat(taxCalculator.getSyakebenSubtotal(), is((int) (SYAKEBEN_PRICE * 1.05)));
		assertThat(taxCalculator.getMakunouchiSubtotal(), is((int) (MAKUNOUCHI_PRICE * 1.05)));
	}

	/**
	 * 
	 */
	@Test
	public void testTax08() {
		Multiplier taxCalculator = new Multiplier();

		taxCalculator.setNoribenCount("1");
		taxCalculator.setSyakebenCount("1");
		taxCalculator.setMakunouchiCount("1");
		taxCalculator.setTax08();
		assertThat(taxCalculator.getNoribenSubtotal(), is((int) (NORIBEN_PRICE * 1.08)));
		assertThat(taxCalculator.getSyakebenSubtotal(), is((int) (SYAKEBEN_PRICE * 1.08)));
		assertThat(taxCalculator.getMakunouchiSubtotal(), is((int) (MAKUNOUCHI_PRICE * 1.08)));
	}

	/**
	 * 
	 */
	@Test
	public void testTax10() {
		Multiplier taxCalculator = new Multiplier();

		taxCalculator.setNoribenCount("1");
		taxCalculator.setSyakebenCount("1");
		taxCalculator.setMakunouchiCount("1");
		taxCalculator.setTax10();
		assertThat(taxCalculator.getNoribenSubtotal(), is((int) (NORIBEN_PRICE * 1.1)));
		assertThat(taxCalculator.getSyakebenSubtotal(), is((int) (SYAKEBEN_PRICE * 1.1)));
		assertThat(taxCalculator.getMakunouchiSubtotal(), is((int) (MAKUNOUCHI_PRICE * 1.1)));
	}

	/**
	 * 
	 */
	@Test
	public void testSetNoribenCountZero() {
		Multiplier taxCalculator = new Multiplier();
		taxCalculator.setNoribenCount("0");
		assertThat(taxCalculator.getNoribenSubtotal(), is(0));
	}

	/**
	 * 
	 */
	@Test
	public void testSetSyakebenCountZero() {
		Multiplier taxCalculator = new Multiplier();
		taxCalculator.setSyakebenCount("0");
		assertThat(taxCalculator.getSyakebenSubtotal(), is(0));
	}

	/**
	 * 
	 */
	@Test
	public void testSetMakunouchiCountZero() {
		Multiplier taxCalculator = new Multiplier();
		taxCalculator.setMakunouchiCount("0");
		assertThat(taxCalculator.getMakunouchiSubtotal(), is(0));
	}

	/**
	 * 
	 */
	@Test
	public void testSetNoribenCountMinus() {
		Multiplier taxCalculator = new Multiplier();
		try {
			taxCalculator.setNoribenCount("-1");
		} catch (IllegalArgumentException e) {
		}
		fail("IllegalArgumentException was not thrown.");
	}

	/**
	 * 
	 */
	@Test
	public void testSetSyakebenCountMinus() {
		Multiplier taxCalculator = new Multiplier();
		try {
			taxCalculator.setSyakebenCount("-1");
		} catch (IllegalArgumentException e) {
		}
		fail("IllegalArgumentException was not thrown.");
	}

	/**
	 * 
	 */
	@Test
	public void testSetMakunouchiCountMinus() {
		Multiplier taxCalculator = new Multiplier();
		try {
			taxCalculator.setMakunouchiCount("-1");
		} catch (IllegalArgumentException e) {
		}
		fail("IllegalArgumentException was not thrown.");
	}

	/**
	 * 
	 */
	@Test
	public void testSetNoribenCountOver() {
		Multiplier taxCalculator = new Multiplier();
		try {
			taxCalculator.setNoribenCount("1000000000");
		} catch (IllegalArgumentException e) {
		}
		fail("IllegalArgumentException was not thrown.");
	}

	/**
	 * 
	 */
	@Test
	public void testSetSyakebenCountOver() {
		Multiplier taxCalculator = new Multiplier();
		try {
			taxCalculator.setSyakebenCount("1000000000");
		} catch (IllegalArgumentException e) {
		}
		fail("IllegalArgumentException was not thrown.");
	}

	/**
	 * 
	 */
	@Test
	public void testSetMakunouchiCountOver() {
		Multiplier taxCalculator = new Multiplier();
		try {
			taxCalculator.setMakunouchiCount("1000000000");
		} catch (IllegalArgumentException e) {
		}
		fail("IllegalArgumentException was not thrown.");
	}
}
