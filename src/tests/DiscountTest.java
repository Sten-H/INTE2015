package tests;
import static org.junit.Assert.*;
import register.Discount;
import register.DiscountPair;
import register.OrderLine;
import register.Product;

import java.text.ParseException;
import java.util.*;

import org.junit.Test;

public class DiscountTest {

	private Calendar cal;
	private Double db;
	private ArrayList<DiscountPair> dlist = new ArrayList<DiscountPair>();
	private Date start;
	private Date end;
	private DiscountPair discp;
	
	private void setupSimpleConstructor(){
		cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, 2015);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 1);
		start = cal.getTime();
		end = cal.getTime();
		db = (double) 1;
		discp = new DiscountPair(new Product("A",10), 1);
		dlist.add(discp);
	}
	
	
	//below are the tests for the constructor arguments
	@Test
	public void testSimpleConstructor() throws ParseException{
		setupSimpleConstructor();	
		cal.set(Calendar.MONTH, Calendar.MARCH);
		end = cal.getTime();
		Discount d = new Discount(start, end, db, dlist);
		assertEquals(d.getStartDate(), start);
		assertEquals(d.getEndDate(), end);
		assertEquals(d.getDiscountAmount(), 1,0);
		assertEquals(d.getDiscountPairList(), dlist);
	}
	
	
	//tests if the enddate can be greater than the start date. should always pass.
	@Test
	public void testEndGreaterThanStart(){
		setupSimpleConstructor();
		cal.set(Calendar.DATE, 5);
		end = cal.getTime();
		Discount d = new Discount(start, end, db, dlist);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStartGreaterThanEnd(){
		setupSimpleConstructor();
		cal.set(Calendar.DATE, 5);
		start = cal.getTime();
		Discount d = new Discount(start, end, db, dlist);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStartIsNull(){
		setupSimpleConstructor();
		start = null;
		Discount d = new Discount(start, end, db, dlist);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEndIsNull(){
		setupSimpleConstructor();
		end = null;
		Discount d = new Discount(start, end, db, dlist);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAmountIsSmallerThanZero(){
		setupSimpleConstructor();
		db = (double) -2;
		Discount d = new Discount(start, end, db, dlist);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAmountIsZero(){
		setupSimpleConstructor();
		db = (double) 0;
		Discount d = new Discount(start, end, db, dlist);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDiscountPairListFewerThanZero(){
		setupSimpleConstructor();
		dlist = new ArrayList<DiscountPair>();
		Discount d = new Discount(start, end, db, dlist);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDiscountPairListIsNull(){
		setupSimpleConstructor();
		dlist = null;
		Discount d = new Discount(start, end, db, dlist);
	}
	
	
	
	//tests for the isValid method
	@Test
	public void testDiscountValid(){
		setupSimpleConstructor();
		cal.set(Calendar.YEAR, 2115);
		end = cal.getTime();
		Discount d = new Discount(start, end, db, dlist);
		assertTrue(d.isDateValid());
	}
	
	@Test
	public void testDiscountExpired(){
		setupSimpleConstructor(); //this sets both start and end to 2015-01-01, thus making the discount invalid
		Discount d = new Discount(start, end, db, dlist);
		assertFalse(d.isDateValid());
	}
	
	@Test
	public void testDiscountNotYetValid(){
		setupSimpleConstructor();
		cal.set(Calendar.YEAR, 2115);
		start = cal.getTime();
		end = cal.getTime();
		Discount d = new Discount(start, end, db, dlist);
		assertFalse(d.isDateValid());
	}
	
	@Test
	public void testValidSameDay(){
		setupSimpleConstructor();
		start = new Date();
		end = new Date();
		Discount d = new Discount(start, end, db, dlist);
		assertTrue(d.isDateValid());
	}
	
	@Test
	public void testValidProducts(){
		start = new Date();
		end = new Date();
		Product p1 = new Product("Ananas", 15);
		Product p2 = new Product("Banan", 10);
		DiscountPair dp1 = new DiscountPair(p1, 2);
		DiscountPair dp2= new DiscountPair(p2, 5);
		ArrayList<DiscountPair> dpList = new ArrayList<>();
		dpList.add(dp1);
		Discount discount = new Discount(start, end, 10.0, dpList);
		
		ArrayList<OrderLine> products = new ArrayList<>();
		OrderLine ol = new OrderLine(p1, 2);
		products.add(ol);
		
		assertTrue(discount.productsValid(products));
		ol = new OrderLine(p1, 1);
		products.clear();
		products.add(ol); //Too few of the right product being bought
		assertFalse(discount.productsValid(products));
	}
}
