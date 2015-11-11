package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import register.CustomerInformation;



public class CustomerInformationTest {

	
	//Test constructors
	@Test
	public void testFirstConstructor(){
		CustomerInformation cI = new CustomerInformation("12345678900", 3);
		assertEquals("12345678900", cI.getCreditCardNumber());		
		assertEquals(3, cI.getCustomerNumber(), 0);
	}
	
	@Test
	public void testFirstConstructor2(){
		CustomerInformation cI = new CustomerInformation("09876543211", 98);
		assertEquals("09876543211", cI.getCreditCardNumber());
		assertEquals(98, cI.getCustomerNumber(), 0);
	}
	
	
	//Test exceptions CreditCardNumber
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none(); 
	
	
	
	@Test
	public void testExceptionNullCreditCardNumber(){
		expectedException.expect(IllegalArgumentException.class);
	    expectedException.expectMessage("CreditCardNumber can not be null.");
	    CustomerInformation cI = new CustomerInformation(null, 98);
	}		
	
	/*there are maaaaany things to be tested if we wanna do this as real as possible.. 
	 * but that would be impossible so for now I'm just testing that there are at least
	 * 11 letters and maximum 16.. (the smallest amount if you use this testtabel 
	 * https://www.paypalobjects.com/en_US/vhelp/paypalmanager_help/credit_card_numbers.htm) 

*/
	@Test
	public void testExceptionTooShortCreditCardNumber(){
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("CreditCardNumber must contain at least 11 numbers.");
		CustomerInformation cI = new CustomerInformation("3", 98);
	}
	
	@Test
	public void testExceptionTooLongCreditCardNumber(){
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("CreditCardNumber can contain a maximum of 16 numbers.");
		CustomerInformation cI = new CustomerInformation("1234567890987654321", 98);
	}
	
	@Test
	public void testExceptionTrimCreditCardNumber(){
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("CreditCardNumber must contain at least 11 numbers.");
		CustomerInformation cI = new CustomerInformation("1234567890       ", 98);
	}
	
	@Test
	public void testExceptionOnlyNumbersCreditCardNumber(){
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("CreditCardNumber must contain at least 11 numbers.");
		CustomerInformation cI = new CustomerInformation("ab123456789c0", 98);
		
	}
	
	//Test exceptions CustomerNumber 
	@Test
	public void testExceptionTooLowCustomerNumber(){
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("CustomerNumber can not be negative or zero.");
		CustomerInformation cI = new CustomerInformation("1234567890000", -98);
	}
		

	
	
	
	
}
