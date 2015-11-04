package register;

public class CustomerInformation {
	String creditCardNumber;
	int customerNumber;

	public CustomerInformation(String creditCardNumber, int customerNumber){
		if(creditCardNumber == null)
			throw new NullPointerException("CreditCardNumber can not be null.");
		
		if (creditCardNumber.length() < 11) 
			throw new IllegalArgumentException("CreditCardNumber must contain at least 11 letters.");
		
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