package tests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import register.Discount;
import register.DiscountPair;
import register.OrderLine;
import register.Product;
import register.Receipt;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class ReceiptTest {
	Product p1 = new Product("Fejkon", 33);
	Product p2 = new Product("Gooooodis", 22);
	Product p3 = new Product("Avokado", 11);
	OrderLine ol1 = new OrderLine(p1, 2);
	OrderLine ol2 = new OrderLine(p2, 5);
	OrderLine ol3 = new OrderLine(p3, 1);
	ArrayList<OrderLine> orderLineList = new ArrayList<>();
	ArrayList<Discount> validDiscountList = new ArrayList<>();
	
	private ArrayList<Discount> createDiscountList(){
		Calendar cal;
	    Date start;
	    Date end;
	    ArrayList<Discount> validDList = new ArrayList<>();
	    cal = new GregorianCalendar();
	    cal.set(Calendar.YEAR, 2015);
	    cal.set(Calendar.MONTH, 10);
	    cal.set(Calendar.DATE, 10);
	    start = cal.getTime();
	    cal.set(Calendar.YEAR, 2015);
	    cal.set(Calendar.MONTH, 11);
	    cal.set(Calendar.DATE, 11);
	    end = cal.getTime();
	    
	    
	    ArrayList<DiscountPair> discountPairList = new ArrayList<>();
		DiscountPair discountPair = new DiscountPair(p3, 2);
		discountPairList.add(discountPair);
		Discount d = new Discount(start, end, 2.0, discountPairList);
		validDList.add(d);
	     
		return validDList;
	}
	//Test constructors
	@Test
	public void testSimpleConstructor1(){
		
		orderLineList.add(ol1);
		orderLineList.add(ol2);
		orderLineList.add(ol3);
		Receipt r = new Receipt(orderLineList, createDiscountList());
		assertNotNull(r.getOrderLineList());
	}
	
	//Test exceptions
	@Rule
	public ExpectedException expectedException = ExpectedException.none(); 
				
	@Test
	public void testExceptionNullOrder(){
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("OrderLineList can not be null.");
		Receipt r = new Receipt(null, createDiscountList());
	}
	
	//Test totalPrice
	@Test
	public void testGetTotalPrice(){
		orderLineList.add(ol1);
		orderLineList.add(ol2);
		orderLineList.add(ol3);
		Receipt r = new Receipt(orderLineList, createDiscountList());
		assertEquals(187, r.getTotalPrice(), 0);	
	}
	
	//Test toString
	@Test
	public void testToString(){
		orderLineList.add(ol1);
		orderLineList.add(ol2);
		orderLineList.add(ol3);
		Receipt r = new Receipt(orderLineList, createDiscountList());
		assertEquals("Fejkon : 33.0\t2 st\t66.0\nGooooodis : 22.0\t5 st\t110.0\nAvokado : 11.0\t1 st\t11.0\nSum: 187.0", r.toString());
	}
}









	