package register;

import java.util.ArrayList;

public class Reciept {
	ArrayList<OrderLine> orderLineList;
	
	public Reciept(ArrayList<OrderLine> orderLineList){
		if(orderLineList == null)
			throw new NullPointerException("OrderLineList can not be null.");
		this.orderLineList = orderLineList;
	}

	
	public ArrayList<OrderLine> getOrderLineList(){
		return orderLineList;
	}
}