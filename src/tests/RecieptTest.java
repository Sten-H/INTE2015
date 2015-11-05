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


	//Test constructors
		@Test
		public void testSimpleConstructor1(){
			Product p1 = new Product("Fejkon", 33);
			OrderLine ol1 = new OrderLine(p1, 2);
			ArrayList<OrderLine> orderLineList = new ArrayList<>();
			orderLineList.add(ol1);
			Order order = new Order(orderLineList);
			Reciept r = new Reciept(order);
			assertNotNull(r);
		}
}









	