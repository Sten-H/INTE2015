package register;

public class Reciept {
	private Order order;
	
	public Reciept(Order order){
		this.order = order;
	}

	
	public Order getOrder(){
		return order;
	}
}
