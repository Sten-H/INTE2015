package register;
import register.OrderLine;
import java.util.ArrayList;

public class Receipt {
	private ArrayList<OrderLine> orderLineList;
	private ArrayList<Discount> validDiscountList;
	
	public Receipt(ArrayList<OrderLine> orderLineList, ArrayList<Discount> validDiscountList){
		if(orderLineList == null)
			throw new IllegalArgumentException("OrderLineList can not be null.");
		this.orderLineList = orderLineList;
		this.validDiscountList = validDiscountList;
	}

	public ArrayList<OrderLine> getOrderLineList(){
		return orderLineList;
	}

	public double getTotalPrice(){
		double totalPrice = 0;
		for (OrderLine o : orderLineList){ 
			totalPrice += o.getTotalPrice();
		}
		return totalPrice;
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


