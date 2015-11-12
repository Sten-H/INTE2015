package tests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import register.CustomerInformation;
import register.Discount;
import register.DiscountManager;
import register.DiscountPair;
import register.Order;
import register.OrderLine;
import register.Product;


public class OrderTest {
	@Rule
	//This is sort of a placeholder exception that I can reuse.
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testNullOrderLineListInConstructor() {
            expectedException.expect(IllegalArgumentException.class);
	    expectedException.expectMessage("Order cannot be null.");
            Order o = new Order(null);
	}
	
	@Test
	public void testZeroLengthOrderLineListInConstructor() {
            expectedException.expect(IllegalArgumentException.class);
	    expectedException.expectMessage("Order must contain atleast 1 product.");
            ArrayList<OrderLine> orderLineList = new ArrayList<>();
            Order o = new Order(orderLineList);
	}
	
	@Test
	public void testCustomerInfoConstructor(){
            CustomerInformation ci = new CustomerInformation("000111222333",  0123456);
            ArrayList<OrderLine> orderLineList = new ArrayList<>();
            Product p1 = new Product("Catsup", 33);
            OrderLine ol1 = new OrderLine(p1, 2);
            orderLineList.add(ol1);
            Order o = new Order(orderLineList, ci);
	}
	
        @Test
        public void testNullOrderLineListInTwoParameterConstructor() {
            CustomerInformation ci = new CustomerInformation("000111222333",  0123456);
            expectedException.expect(IllegalArgumentException.class);
	    expectedException.expectMessage("Order cannot be null.");
            Order o = new Order(null, ci);
        }
	@Test
	public void testCustomerInfoConstructorWithZeroLengthOrderLineList() {
            expectedException.expect(IllegalArgumentException.class);
	    expectedException.expectMessage("Order must contain atleast 1 product.");
            ArrayList<OrderLine> orderLineList = new ArrayList<>();
            CustomerInformation ci = new CustomerInformation("000111222333",  0123456);
            Order o = new Order(orderLineList, ci);
	}
	
	@Test
	public void testCreateReceipt(){
            //This should probably be moved to a setupd method or something.
            Product p1 = new Product("Catsup", 33);
            OrderLine ol1 = new OrderLine(p1, 2);
            ArrayList<OrderLine> orderLineList = new ArrayList<>();
            orderLineList.add(ol1);
            Order order = new Order(orderLineList);
            order.createReceipt();
	}

	@Test
	public void testCreateReceiptWithCustomerInformation(){
            Product p1 = new Product("Catsup", 33);
            OrderLine ol1 = new OrderLine(p1, 2);
            ArrayList<OrderLine> orderLineList = new ArrayList<>();
            orderLineList.add(ol1);
            CustomerInformation ci = new CustomerInformation("000111222333",  0123456);
            Order order = new Order(orderLineList, ci);
            assertEquals(order.createReceipt().getCustomerInformation(), ci);
	}
	
	@Test
	public void testNoValidDiscounts(){
            Product p1 = new Product("Milk", 10);
            Product p2 = new Product("Bread", 12);
            OrderLine ol1 = new OrderLine(p1, 2);
            OrderLine ol2 = new OrderLine(p2, 1);
            ArrayList<OrderLine> orderLineList= new ArrayList<OrderLine>();
            orderLineList.add(ol1);
            orderLineList.add(ol2);
            Order order = new Order(orderLineList);
            assertEquals(order.getValidDiscounts(orderLineList).size(), 0);
	}
	
	@Test
	public void testValidDiscounts(){
            Product p1 = new Product("Milk", 10);
            Product p2 = new Product("Bread", 12);
            OrderLine ol1 = new OrderLine(p1, 2);
            OrderLine ol2 = new OrderLine(p2, 1);
            ArrayList<OrderLine> orderLineList= new ArrayList<OrderLine>();
            orderLineList.add(ol1);
            orderLineList.add(ol2);
            Order order = new Order(orderLineList);

            Calendar cal = new GregorianCalendar();
            Date date = cal.getTime();
            DiscountPair dp = new DiscountPair(p1, 2);
            ArrayList<DiscountPair> dplist = new ArrayList<DiscountPair>();
            dplist.add(dp);
            Discount discount = new Discount(date, date, 5.0, dplist);

            DiscountManager dm = DiscountManager.getInstance();
            ArrayList<Discount> dlist = new ArrayList<Discount>();
            dlist.add(discount);
            dm.setDiscounts(dlist);
            assertEquals(order.getValidDiscounts(orderLineList), dlist);
	}
}
