package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import register.CustomerInformation;
import register.Product;

public class CustomerInformationTest {

	
	//Test constructors
	@Test
	public void testFirstConstructor(){
		CustomerInformation cI = new CustomerInformation("1234567890", 3);
		assertEquals("1234567890", cI.getCreditCardNumber());		
		assertEquals(3, cI.getCustomerNumber(), 0);
	}
	
	@Test
	public void testFirstConstructor2(){
		CustomerInformation cI = new CustomerInformation("0987654321", 98);
		assertEquals("0987654321", cI.getCreditCardNumber());
		assertEquals(98, cI.getCustomerNumber(), 0);
	}
	
}
