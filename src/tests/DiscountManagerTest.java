package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
			start = cal.getTime();
			cal.set(Calendar.DATE, endDays[i]);
			end = cal.getTime();
			//Create Discount Pair placeholder
			ArrayList<DiscountPair> dlist = new ArrayList<DiscountPair>();
			Product p = new Product(products[i], 10);
			DiscountPair dp = new DiscountPair(p, productAmount[i]);
			
			dlist.add(dp);
			d = new Discount(start, end, discountAmount[i], dlist);
			discountList.add(d);
		}
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
	public void testOrderEligibleForDiscount(){
		DiscountManager dm1 = DiscountManager.getInstance();
		//Set discounts manually so we know what we have.
		dm1.setDiscounts(createDiscountList());
		//Create order that customer wants to apply valid discount to.
		Product p1 = new Product("Paj", 33);
		OrderLine ol1 = new OrderLine(p1, 2);
		ArrayList<OrderLine> orderLineList = new ArrayList<>();
		orderLineList.add(ol1);
		
		ArrayList<Discount> applicableDiscounts = dm1.getValidDiscounts(orderLineList);
		
		//FIXME not done.
		assertTrue(applicableDiscounts == null);
	}
	
	@Test
	public void testLoadingDiscounts(){
		DiscountManager dm1 = DiscountManager.getInstance();
		assertTrue(dm1.getAllDiscounts().size() > 0);
	}
}
