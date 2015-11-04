package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import register.DiscountManager;

public class DiscountManagerTest {

	@Test
	public void testSingletonPattern() {
		DiscountManager dm1 = DiscountManager.getInstance();
		assertEquals(1, dm1.getInstanceCount(), 0);
		DiscountManager dm2 = DiscountManager.getInstance();
		//Count should still be 1 since its a singleton.
		assertEquals(1, dm1.getInstanceCount(), 0);
		assertEquals(1, dm2.getInstanceCount(), 0);
	}
	
	@Test
	public void testLoadingDiscounts(){
		//Pre-existing discounts get loaded from resources/discounts.json
		DiscountManager dm1 = DiscountManager.getInstance();
		assertTrue(dm1.getDiscounts().contains("Mustard"));
		assertTrue(dm1.getDiscounts().contains("Tzay bites"));
	}
}
