/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package register;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This class is only meant as a typical use case for profiling
 * 
 */
public class Main {
    public static void main(String args[]){
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
                
                DiscountManager dm = DiscountManager.getInstance();
                dm.setDiscounts(discountList);
                
                Product p3 = new Product("Milk", 10);
		Product p4 = new Product("Bread", 12);
		OrderLine ol1 = new OrderLine(p3, 2);
		OrderLine ol2 = new OrderLine(p4, 1);
                OrderLine ol3 = new OrderLine(p1, 3);
                OrderLine ol4 = new OrderLine(p2, 10);
		ArrayList<OrderLine> orderLineList= new ArrayList<OrderLine>();
		orderLineList.add(ol1);
		orderLineList.add(ol2);
                orderLineList.add(ol3);
                orderLineList.add(ol4);
                CustomerInformation cI = new CustomerInformation("12345678900", 3);
		Order order = new Order(orderLineList, cI);
                
                order.getValidDiscounts(orderLineList);
                Receipt r = order.createReceipt();
                
                System.out.println(r);
    }
}
