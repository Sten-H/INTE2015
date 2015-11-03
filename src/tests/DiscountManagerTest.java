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
		assertEquals(1, dm1.getInstanceCount(), 0);
	}
	@Test
	public void testLoadingDiscounts(){
		DiscountManager dm1 = DiscountManager.getInstance();
		assertEquals(0, dm1.getDiscounts().size(), 0);
	}
}
