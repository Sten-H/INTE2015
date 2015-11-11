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
		assertEquals(pair.getProductAmount(), i);
	}
	
	@Test
	public void testMatchesProductsBought(){
		Product p1 = new Product("Champinjon", 25);
		OrderLine ol = new OrderLine(p1, 3);
		DiscountPair dp = new DiscountPair(p1, 2);
		
		assertTrue(dp.matchesOrderLine(ol));
	}
	
	@Test
	public void testDoesNotMatchProductsBought(){
		Product p1 = new Product("Champinjon", 25);
		Product p2 = new Product("Kantarell", 45);
		OrderLine ol = new OrderLine(p1, 3);
		DiscountPair dp = new DiscountPair(p2, 3);
		
		assertFalse(dp.matchesOrderLine(ol));
	}
	
	@Test
	public void testDoesMatchProductBoughtButTooFew(){
		Product p1 = new Product("Champinjon", 25);
		OrderLine ol = new OrderLine(p1, 1);
		DiscountPair dp = new DiscountPair(p1, 2);
		
		assertFalse(dp.matchesOrderLine(ol));	
	}
}
