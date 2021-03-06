package tests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import register.CustomerInformation;
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
	Product p2 = new Product("Godis", 22);
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
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    start = cal.getTime();
	    cal.set(Calendar.YEAR, 2015);
	    cal.set(Calendar.MONTH, 11);
	    cal.set(Calendar.DATE, 11);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    end = cal.getTime();
	    
	    
	    ArrayList<DiscountPair> discountPairList = new ArrayList<>();
		DiscountPair discountPair = new DiscountPair(p1, 2);
		discountPairList.add(discountPair);
		Discount d = new Discount(start, end, 10.0, discountPairList);
		validDList.add(d);
	     
		return validDList;
	}
	
	private ArrayList<Discount> createDiscountListMultiple(){
		Calendar cal;
	    Date start;
	    Date end;
	    ArrayList<Discount> validDList = new ArrayList<>();
	    cal = new GregorianCalendar();
	    cal.set(Calendar.YEAR, 2015);
	    cal.set(Calendar.MONTH, 10);
	    cal.set(Calendar.DATE, 10);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    start = cal.getTime();
	    cal.set(Calendar.YEAR, 2015);
	    cal.set(Calendar.MONTH, 11);
	    cal.set(Calendar.DATE, 11);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    end = cal.getTime();
	    
	    
	    ArrayList<DiscountPair> discountPairList1 = new ArrayList<>();
	    ArrayList<DiscountPair> discountPairList2 = new ArrayList<>();
		DiscountPair discountPair1 = new DiscountPair(p1, 2);
		DiscountPair discountPair2 = new DiscountPair(p2, 3);
		DiscountPair discountPair3 = new DiscountPair(p3, 1);
		discountPairList1.add(discountPair1);
		discountPairList2.add(discountPair2);
		discountPairList2.add(discountPair3);
		Discount d1 = new Discount(start, end, 10.0, discountPairList1);
		Discount d2 = new Discount(start, end, 5.0, discountPairList2);
		validDList.add(d1);
		validDList.add(d2);
	     
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
	
	@Test
	public void testOrderLineListNullCustomerInfoConstructor(){
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("OrderLineList can not be null.");
		CustomerInformation ci = new CustomerInformation("000111222333",  0123456);
		Receipt r = new Receipt(null, createDiscountList(), ci);
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
	
	@Test
	public void testGetDiscountAmountOneDiscount(){
		orderLineList.add(ol1);
		orderLineList.add(ol2);
		orderLineList.add(ol3);
		Receipt r = new Receipt(orderLineList, createDiscountList());
		assertEquals(10, r.getDiscountAmount(), 0);
	}
	
	@Test
	public void testGetDiscountAmountNoDiscount(){
		Receipt r = new Receipt(orderLineList, new ArrayList<Discount>());
		assertEquals(0, r.getDiscountAmount(), 0);
	}
	//Test toString
	@Test
	public void testToString(){
		orderLineList.add(ol1);
		orderLineList.add(ol2);
		orderLineList.add(ol3);
		Receipt r = new Receipt(orderLineList, createDiscountList());
		assertEquals("\n###Receipt###\n"
				+ "Customer number: ???\n"
				+ "---Products---\n"
				+ "Fejkon : 33.0\t2 st\t66.0\n"
				+ "Godis : 22.0\t5 st\t110.0\n"
				+ "Avokado : 11.0\t1 st\t11.0\n"
				+ "---Discounts---\n"
				+ "[-10.0 kr, [2x Fejkon]]\n"
				+ "---Sum---\n"
				+ "Total price: 187.0\n"
				+ "Discounted price: 177.0\n"
				+ "---Thanks!---", r.toString());
	}
	
	//test with customerInformation
	@Test
	public void testToStringCustomerInfo(){
		CustomerInformation c = new CustomerInformation("111222333444",123123);
		orderLineList.add(ol1);
		orderLineList.add(ol2);
		orderLineList.add(ol3);
		Receipt r = new Receipt(orderLineList, createDiscountList(), c);
		assertEquals("\n###Receipt###\n"
				+ "Customer number: 123123\n"
				+ "---Products---\n"
				+ "Fejkon : 33.0\t2 st\t66.0\n"
				+ "Godis : 22.0\t5 st\t110.0\n"
				+ "Avokado : 11.0\t1 st\t11.0\n"
				+ "---Discounts---\n"
				+ "[-10.0 kr, [2x Fejkon]]\n"
				+ "---Sum---\n"
				+ "Total price: 187.0\n"
				+ "Discounted price: 177.0\n"
				+ "---Thanks!---", r.toString());
	}
	
	@Test
	public void testToStringMultipleDiscounts(){
		orderLineList.add(ol1);
		orderLineList.add(ol2);
		orderLineList.add(ol3);
		Receipt r = new Receipt(orderLineList, createDiscountListMultiple());
		assertEquals("\n###Receipt###\n"
				+ "Customer number: ???\n"
				+ "---Products---\n"
				+ "Fejkon : 33.0\t2 st\t66.0\n"
				+ "Godis : 22.0\t5 st\t110.0\n"
				+ "Avokado : 11.0\t1 st\t11.0\n"
				+ "---Discounts---\n"
				+ "[-10.0 kr, [2x Fejkon]]\n"
				+ "[-5.0 kr, [3x Godis, 1x Avokado]]\n"
				+ "---Sum---\n"
				+ "Total price: 187.0\n"
				+ "Discounted price: 172.0\n"
				+ "---Thanks!---", r.toString());
	}
}









	