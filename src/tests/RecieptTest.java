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
	OrderLine ol1 = new OrderLine(p1, 2);
	ArrayList<OrderLine> orderLineList = new ArrayList<>();

	//Test constructors
		@Test
		public void testSimpleConstructor1(){
			orderLineList.add(ol1);
			Order order = new Order(orderLineList);
			Reciept r = new Reciept(order);
			assertNotNull(r.getOrder());
		}
		
		
		//Test exceptions
		@Rule
		public ExpectedException expectedException = ExpectedException.none(); 
		
		@Test
		public void testExceptionNullOrder(){
			expectedException.expect(NullPointerException.class);
		    expectedException.expectMessage("Order can not be null.");
			Reciept r = new Reciept(null);
		}		
}









	