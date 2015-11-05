package register;

import java.util.ArrayList;

public class Reciept {
	ArrayList<OrderLine> orderLineList;
	
	public Reciept(ArrayList<OrderLine> orderLineList){
		this.orderLineList = orderLineList;
	}

	
	public ArrayList<OrderLine> getOrderLineList(){
		return orderLineList;
	}
}