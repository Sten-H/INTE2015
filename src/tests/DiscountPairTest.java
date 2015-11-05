package tests;
import static org.junit.Assert.*;
import register.DiscountPair;
import register.OrderLine;
import register.Product;

import java.util.*;

import org.junit.Test;

public class DiscountPairTest {
	
	@Test
	public void testSimpleConstructor(){
		Product p = new Product("A", 10);
		int i = 1;
		DiscountPair pair = new DiscountPair(p, i);
		assertEquals(pair.getDiscountProduct(), p);
		assertEquals(pair.getDiscountAmount(), i);
	}
	
	@Test
	public void testContainsProductsBought(){
		Product p1 = new Product("Champinjon", 25);
		OrderLine ol = new OrderLine(p1, 3);
		DiscountPair dp = new DiscountPair(p1, 2);
		
		assertTrue(dp.isApplicable(ol));
	}
	
	@Test
	public void testDoesNotContainProductsBought(){
		Product p1 = new Product("Champinjon", 25);
		Product p2 = new Product("Kantarell", 45);
		OrderLine ol = new OrderLine(p1, 3);
		DiscountPair dp = new DiscountPair(p2, 3);
		
		assertFalse(dp.isApplicable(ol));
	}
	
	@Test
	public void testDoesContainProductBoughtButTooFew(){
		Product p1 = new Product("Champinjon", 25);
		OrderLine ol = new OrderLine(p1, 1);
		DiscountPair dp = new DiscountPair(p1, 2);
		
		assertFalse(dp.isApplicable(ol));	
	}
}
