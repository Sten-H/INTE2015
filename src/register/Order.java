package register;

import java.util.ArrayList;
/**
 * Order contains all the products to be baught. 
 * It will create a receipt with the orderlines
 * and the applicable discounts.
 */
public class Order {
	private ArrayList<OrderLine> orderLineList = new ArrayList<>();
	
	public Order(ArrayList<OrderLine> orderLineList){
		if(orderLineList.size() < 1)
			throw new IllegalArgumentException("Order must contain atleast 1 product.");
		this.orderLineList = orderLineList;
	}
	private int getValidDiscounts(){
		//ska vara private Discount
		return 0;
	}
	/**
	 * Gets applicable discounts and creates a receipt
	 * from discounts and orders.
	 * @return receipt, returns a Receipt class
	 */
	public int createReceipt(){
		//ska vara public Receipt
		return 0; 
	}
}