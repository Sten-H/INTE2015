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
		
		
		//Test exceptions name
		@Test
		public void testExceptionTooShortName(){
			expectedException.expect(IllegalArgumentException.class);
		    expectedException.expectMessage("name must contain at least one letter.");
			Product p = new Product("", 34);
		}
	

}
