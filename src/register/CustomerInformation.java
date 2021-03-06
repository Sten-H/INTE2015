package register;

/**
 * Class that handles the customer. Stores a number and
 * their credit card number. 
 *
 */
public class CustomerInformation {
	private String creditCardNumber;
	private int customerNumber;

	public CustomerInformation(String creditCardNumber, int customerNumber){
		if(creditCardNumber == null)
			throw new IllegalArgumentException("CreditCardNumber can not be null.");
		
		if (creditCardNumber.trim().length() < 11) 
			throw new IllegalArgumentException("CreditCardNumber must contain at least 11 numbers.");
		
		if (creditCardNumber.trim().length() > 16) 
			throw new IllegalArgumentException("CreditCardNumber can contain a maximum of 16 numbers.");
		
		if (!creditCardNumber.trim().matches("[0-9]+")) 
			throw new IllegalArgumentException("CreditCardNumber must contain at least 11 numbers.");
		
		if (customerNumber < 1) 
			throw new IllegalArgumentException("CustomerNumber can not be negative or zero.");
		
		this.creditCardNumber = creditCardNumber;
		this.customerNumber = customerNumber;
	}
	
	public String getCreditCardNumber(){
		return creditCardNumber;
	}
	
	public int getCustomerNumber(){
		return customerNumber;
	}
	
	
}