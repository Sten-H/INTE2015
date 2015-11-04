package register;

public class CustomerInformation {
	String creditCardNumber;
	int customerNumber;

	public CustomerInformation(String creditCardNumber, int customerNumber){
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