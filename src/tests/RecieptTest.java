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
	Product p2 = new Product("Gooooodis", 22);
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
	
	//Test exceptions
	@Rule
	public ExpectedException expectedException = ExpectedException.none(); 
				
	@Test
	public void testExceptionNullOrder(){
		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("OrderLineList can not be null.");
		Reciept r = new Reciept(null);
	}
	
	
	
	//Test totalPrice
	@Test
	public void testGetTotalPrice(){
		orderLineList.add(ol1);
		orderLineList.add(ol2);
		orderLineList.add(ol3);
		Reciept r = new Reciept(orderLineList);
		assertEquals(187, r.getTotalPrice(), 0);	
	}
	
	
}









	