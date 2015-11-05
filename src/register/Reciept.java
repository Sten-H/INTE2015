package register;
import register.OrderLine;
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

	public double getTotalPrice(){
		double totalPrice = 0;
		for (OrderLine o : orderLineList) totalPrice += o.getTotalPrice();
		
		return totalPrice;
	}
	
	
}