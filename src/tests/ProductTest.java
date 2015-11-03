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
	
	

}
