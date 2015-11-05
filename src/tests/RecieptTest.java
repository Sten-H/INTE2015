package tests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import register.Order;
import register.OrderLine;
import register.Product;
import register.Reciept;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RecieptTest {
	Product p1 = new Product("Fejkon", 33);
	Product p2 = new Product("Potatis", 22);
	Product p3 = new Product("Avokado", 11);
	OrderLine ol1 = new OrderLine(p1, 2);
	OrderLine ol2 = new OrderLine(p2, 5);
	OrderLine ol3 = new OrderLine(p3, 1);
	ArrayList<OrderLine> orderLineList = new ArrayList<>();

	//Test constructors
		@Test
		public void testSimpleConstructor1(){
			orderLineList.add(ol1);
			orderLineList.add(ol2);
			orderLineList.add(ol3);
			Reciept r = new Reciept(orderLineList);
			assertNotNull(r.getOrderLineList());
		}
		
		
	
}









	