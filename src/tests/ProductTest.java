package tests;
import static org.junit.Assert.*;
import register.OrderLine;
import register.Product;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ProductTest {

	//Test constructors
	@Test
	public void testFirstConstructor(){
		Product p = new Product("fejkon", 45);
		assertEquals("fejkon", p.getName());		
		assertEquals(45, p.getPrice(), 0);
	}
	
		
	@Test
	public void testFirstConstructor2(){
		Product p2 = new Product("soygurt", 123);
		assertEquals("soygurt", p2.getName());		
		assertEquals(123, p2.getPrice(), 0);	
	}
		
	@Rule
	public ExpectedException expectedException = ExpectedException.none(); 
	
	@Test
	public void testExceptionNullName(){
		expectedException.expect(IllegalArgumentException.class);
	    expectedException.expectMessage("Name can not be null.");
	    Product p = new Product(null, 10);
	}		
		
	//Test exceptions name
	@Test
	public void testExceptionTooShortName(){
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Name must contain at least one letter.");
		Product p = new Product("", 34);
	}
		
	@Test
	public void testExceptionTooLongName(){
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Name contains too many letters.");
		Product p = new Product("abcdefghijklmnopqrst", 34);
	}
		
	//Test exceptions price
	@Test
	public void testExceptionTooLowPrice(){
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("Price can not be negative");
		Product p = new Product("fejkon", -1);
	}
	@Test
	public void testCompareSameProduct(){
		Product p1 = new Product("Kiwi", 10.0);
		Product p2 = new Product("Kiwi", 10.0);
		
		assertTrue(p1.equals(p2));
		assertTrue(p1.equals(p1));
	}
	
	@Test
	public void testCompareDifferentProduct(){
		Product p1 = new Product("Banan", 12.0);
		Product p2 = new Product("Kiwi", 10.0);
		
		assertFalse(p1.equals(p2));
	}
	
	@Test
	public void testCompareNotProduct(){
		Product p1 = new Product("Banan", 12.0);
		String s = "test";
		
		assertFalse(p1.equals(s));
	}

}
