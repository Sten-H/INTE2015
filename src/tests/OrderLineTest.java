package tests;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import register.OrderLine;
import register.Product;


public class OrderLineTest {

	@Rule
	//This is sort of a placeholder exception that I can reuse.
	public ExpectedException expectedException = ExpectedException.none(); 
	
	@Test
	public void testExceptionsInConstructor() {
		expectedException.expect(IllegalArgumentException.class);
	    expectedException.expectMessage("productAmount can not be less than 1.");
		Product mustard = new Product("Mustard", 25);
		OrderLine ol = new OrderLine(mustard, 0);
		expectedException.expect(NullPointerException.class);
	    expectedException.expectMessage("Product can not be null.");
	    ol = new OrderLine(null, 10);
	}
	@Test
	public void testGetTotalPrice(){
		Product mustard = new Product("Mustard", 25);
		OrderLine ol = new OrderLine(mustard, 1);
		assertEquals(ol.getTotalPrice(), 25, 0);
		ol = new OrderLine(mustard, 3);
		assertEquals(ol.getTotalPrice(), 75, 0);
	}
}
