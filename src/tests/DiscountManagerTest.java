package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import register.Discount;
import register.DiscountManager;
import register.DiscountPair;
import register.OrderLine;
import register.Product;

public class DiscountManagerTest {
	
	private ArrayList<Discount> createDiscountList(){
		Calendar cal;
		Date start;
		Date end;
		ArrayList<Discount> discountList = new ArrayList<>();
		cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, 2015);
		cal.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH));
		
		//Values for multiple discounts
		int startDays[] = {1, 4, 6, 10};
		int endDays[] = {3, 6, 10, 15};
		String products[] = {"Paj", "Tzay", "Gurka", "Glass"};
		int productAmount[] = {2, 1, 4, 3};
		double discountAmount[] = {20.0, 14.3, 11.4, 4.5};
		Discount d;
		
		//Create discounts
		for(int i = 0; i < startDays.length; i++){
			cal.set(Calendar.DATE, startDays[i]);
			start = new Date();
			cal.set(Calendar.DATE, endDays[i]);
			end = new Date();
			//Create Discount Pair placeholder
			ArrayList<DiscountPair> dlist = new ArrayList<>();
			Product p = new Product(products[i], 10);
			DiscountPair dp = new DiscountPair(p, productAmount[i]);
			
			dlist.add(dp);
			d = new Discount(start, end, discountAmount[i], dlist);
			discountList.add(d);
		}
		//Add one complex discount requring multiple products
		Product p1 = new Product("Majs", 10);
		Product p2 = new Product("Tortilla", 20);
		DiscountPair dp1 = new DiscountPair(p1, 2);
		DiscountPair dp2 = new DiscountPair(p2, 3);
		ArrayList<DiscountPair> dplist = new ArrayList<>();
		dplist.add(dp1);
		dplist.add(dp2);
		discountList.add(new Discount(new Date(), new Date(), 10.0, dplist));
		return discountList;
	}
	
	@Test
	public void testSingletonPattern() {
		DiscountManager dm1 = DiscountManager.getInstance();
		assertEquals(1, dm1.getInstanceCount(), 0);
		DiscountManager dm2 = DiscountManager.getInstance();
		//Count should still be 1 since its a singleton.
		assertEquals(1, dm1.getInstanceCount(), 0);
		assertEquals(1, dm2.getInstanceCount(), 0);
	}
	@Test
	public void testSimpleDiscountValid(){
		DiscountManager dm1 = DiscountManager.getInstance();
		//Set discounts manually so we know what we have.
		dm1.setDiscounts(createDiscountList());
		//Create order that customer wants to apply valid discount to.
		Product p1 = new Product("Paj", 33);
		Product p2 = new Product("Kaviar", 20);
		OrderLine ol1 = new OrderLine(p1, 2);
		OrderLine ol2 = new OrderLine(p2, 2);
		ArrayList<OrderLine> orderLineList = new ArrayList<>();
		orderLineList.add(ol1);
		orderLineList.add(ol2);
		
		ArrayList<Discount> applicableDiscounts = dm1.getValidDiscounts(orderLineList);
		assertTrue(applicableDiscounts.size() == 1);
	}
	@Test
	public void testComplexDiscountValid(){
		DiscountManager dm = DiscountManager.getInstance();
		dm.setDiscounts(createDiscountList());
		Product p1 = new Product("Majs", 10);
		Product p2 = new Product("Tortilla", 20);
		//2 majs 3 tortilla is a discount
		OrderLine ol1 = new OrderLine(p1, 3);
		OrderLine ol2 = new OrderLine(p2, 6);
		ArrayList<OrderLine> orders = new ArrayList<>(Arrays.asList(ol1, ol2));
		assertTrue(dm.getValidDiscounts(orders).size() == 1);
	}
	
	@Test
	public void testComplexDiscountInvalidTooFewAmountRightProduct(){
		DiscountManager dm = DiscountManager.getInstance();
		dm.setDiscounts(createDiscountList());
		Product p1 = new Product("Majs", 10);
		Product p2 = new Product("Tortilla", 20);
		//2 majs 3 tortilla is a discount
		OrderLine ol1 = new OrderLine(p1, 1);
		OrderLine ol2 = new OrderLine(p2, 6);
		ArrayList<OrderLine> orders = new ArrayList<>(Arrays.asList(ol1, ol2));
		assertTrue(dm.getValidDiscounts(orders).size() == 0);
	}
	@Test
	public void testLoadingDiscounts(){
		DiscountManager dm1 = DiscountManager.getInstance();
		assertTrue(dm1.getAllDiscounts().size() > 0);
	}
}
