package register;

import java.util.ArrayList;
/**
 * Order contains all the products to be bought. 
 * It will create a receipt with the orderlines
 * and the applicable discounts.
 */
public class Order {
	private ArrayList<OrderLine> orderLineList = new ArrayList<>();
	private CustomerInformation customer;

	public Order(ArrayList<OrderLine> orderLineList){
            if(orderLineList == null)
                throw new IllegalArgumentException("Order cannot be null.");
            if(orderLineList.size() < 1)
                    throw new IllegalArgumentException("Order must contain atleast 1 product.");
            this.orderLineList = orderLineList;
	}

	 //customer information is not always needed, and Order therefore has 2 constructors
	public Order(ArrayList<OrderLine> orderLineList, CustomerInformation customer){
            if(orderLineList == null)
                    throw new IllegalArgumentException("Order cannot be null.");	
            if(orderLineList.size() < 1)
			throw new IllegalArgumentException("Order must contain atleast 1 product.");
		this.orderLineList = orderLineList;
		this.customer = customer;
	}

	//NOTE: this method should probably be private, but is public for testing purposes
	/**
	 * gets all valid discounts for order
	 * @param orderLineList, list of all products to be bought
	 * @return a list of all eligible discounts
	 */
	public ArrayList<Discount> getValidDiscounts(ArrayList<OrderLine> orderLineList){
		DiscountManager dm = DiscountManager.getInstance();
		return dm.getValidDiscounts(orderLineList);
	}
        
	/**
	 * Gets applicable discounts and creates a receipt
	 * from discounts and orders. Will include the customers
	 * information if present.
	 * @return receipt, returns a Receipt class
	 */
	public Receipt createReceipt(){
		Receipt receipt;
		if (customer != null)
			receipt = new Receipt(orderLineList, getValidDiscounts(orderLineList), customer);
		else
			receipt = new Receipt(orderLineList, getValidDiscounts(orderLineList));
		return receipt; 
	}
}
