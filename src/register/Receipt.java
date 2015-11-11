package register;
import register.OrderLine;
import java.util.ArrayList;

/**
 * The Receipt class main focus is to take an orderLineList 
 * and (if available) a validDiscountList and transform them into
 * a print with suitable layout. 
 * 
 * (((((The Receipt class also calculates the total end price of the order)))) 
 * Should the method getTotalPrice() really be in this class at all? 
 * 
 *
 */
public class Receipt {
	private ArrayList<OrderLine> orderLineList;
	private ArrayList<Discount> validDiscountList;
	private CustomerInformation customer;
	
	public Receipt(ArrayList<OrderLine> orderLineList, ArrayList<Discount> validDiscountList){
		if(orderLineList == null)
			throw new IllegalArgumentException("OrderLineList can not be null.");
		this.orderLineList = orderLineList;
		this.validDiscountList = validDiscountList;
	}
	
	//second constructor in case order has a non-null customerInformation
	public Receipt(ArrayList<OrderLine> orderLineList, ArrayList<Discount> validDiscountList, CustomerInformation customer){
		if(orderLineList == null)
			throw new IllegalArgumentException("OrderLineList can not be null.");
		this.orderLineList = orderLineList;
		this.validDiscountList = validDiscountList;
		this.customer = customer;
	}

	public ArrayList<OrderLine> getOrderLineList(){
		return orderLineList;
	}

	/** 
	 * Calculates the total price of the order by summing 
	 * total price (getTotalPrice()) from every OrderLine in the orderLineList
	 * and returns it as a double.
	 * @return the total price of the order
	 */
	public double getTotalPrice(){
		double totalPrice = 0;
		for (OrderLine o : orderLineList){ 
			totalPrice += o.getTotalPrice();
		}
		return totalPrice;
	}
	
	public CustomerInformation getCustomerInformation(){
		return customer;
	}
	
	public String toString(){
		String str = "";
		for (OrderLine o : orderLineList){
			str += o.toString() +"\t"+ o.getAmount() + " st\t" + o.getTotalPrice()+"\n";
		}
		
		str+="\nDiscounts: ";
		for (Discount d : validDiscountList){
			str += d.toString() + "\n";
		}
		
		str+="\nSum: " + getTotalPrice();
		
		return str;
	}	
}



