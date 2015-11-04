package tests;
import static org.junit.Assert.*;
import register.Discount;
import register.DiscountPair;
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
	
	@Test
	public void testSimpleConstructor() throws ParseException{
		setupSimpleConstructor();	
		Discount d = new Discount(start, end, db, dlist);
		assertEquals(d.getStartDate(), start);
		assertEquals(d.getEndDate(), end);
		assertEquals(d.getDiscountAmount(), 1,0);
		assertEquals(d.getDiscountPairList(), dlist);
	}
	
	
	//below are the tests for the constructor arguments
	
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
		assertTrue(d.isValid());
	}
	
	@Test
	public void testDiscountExpired(){
		setupSimpleConstructor(); //this sets both start and end to 2015-01-01, thus making the discount invalid
		Discount d = new Discount(start, end, db, dlist);
		assertFalse(d.isValid());
	}
	
	@Test
	public void testDiscountNotYetValid(){
		setupSimpleConstructor();
		cal.set(Calendar.YEAR, 2115);
		start = cal.getTime();
		end = cal.getTime();
		Discount d = new Discount(start, end, db, dlist);
		assertFalse(d.isValid());
	}
	
	@Test
	public void testValidSameDay(){
		setupSimpleConstructor();
		start = new Date();
		end = new Date();
		Discount d = new Discount(start, end, db, dlist);
		assertTrue(d.isValid());
	}
}
