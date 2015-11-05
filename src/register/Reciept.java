package register;

public class Reciept {
	private Order order;
	
	public Reciept(Order order){
		if(order == null)
			throw new NullPointerException("Order can not be null.");
		this.order = order;
	}

	
	public Order getOrder(){
		return order;
	}
}
