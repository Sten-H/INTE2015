package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import register.CustomerInformation;

public class CustomerInformationTest {

	
	//Test constructors
	@Test
	public void testFirstConstructor(){
		CustomerInformation cI = new CustomerInformation("1234567890", 3);
		assertEquals("1234567890", cI.getCreditCardNumber());		
		assertEquals(3, cI.getCustomerNumber(), 0);
	}
	
}
