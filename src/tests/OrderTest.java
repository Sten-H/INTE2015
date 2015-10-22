package tests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Register.Product;
import Register.OrderLine;
import Register.Order;


public class OrderTest {
	@Rule
	//This is sort of a placeholder exception that I can reuse.
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testZeroLengthOrderLineListInConstructor() {
		//Is this necessarily wrong?
		expectedException.expect(IllegalArgumentException.class);
	    expectedException.expectMessage("Order must contain atleast 1 product.");
		ArrayList<OrderLine> orderLineList = new ArrayList<>();
		Order o = new Order(orderLineList);
	}
	
	@Test
	public void testCreateReceipt(){
		//This should probably be moved to a setupd class or something.
		Product p1 = new Prodcut("Catsup", 33);
		OrderLine ol1 = new OrderLine(p1, 2);
		ArrayList<OrderLine> orderLineList = new ArrayList<>();
		orderLineList.add(ol1);
		Order order = new Order(orderLineList);
		
		//Super placeholder
		assertEquals(order.createReceipt(), 0, 0);
	}
}
