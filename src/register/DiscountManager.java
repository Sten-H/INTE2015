package register;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * The DiscountManger class keeps tracks of all the discounts available. 
 * It is implemented as a singleton, so only one instance will be used.
 * Discounts are read from a JSON-text file at creation.
 */
public class DiscountManager {
	private static DiscountManager instance = null;
	private static int instanceCount = 0; //This exists only to be certain singleton pattern is working correctly
	private ArrayList<Discount> discountList = new ArrayList<>(); //This will not be String later on.
	private DiscountManager(){
		instanceCount +=1; //Only used for testing.
		loadDiscounts();
	}
	public static DiscountManager getInstance(){
		if(instance == null)
		{
			instance = new DiscountManager();
			
		}
		return instance;
	}
	
	// This really would have been nicer if it read from a file, but I'll start with this more nasty faster way.
	private void loadDiscounts() {
		Calendar cal;
		Date start;
		Date end;
		
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
			ArrayList<DiscountPair> dlist = new ArrayList<DiscountPair>();
			Product p = new Product(products[i], 10);
			DiscountPair dp = new DiscountPair(p, productAmount[i]);
			
			dlist.add(dp);
			d = new Discount(start, end, discountAmount[i], dlist);
			discountList.add(d);
		}
	}

	/**
	 * getValidDiscounts vill return an arraylist of discounts
	 * that are applicable derived from the actual products that
	 * are being bought.
	 * @param orders are the products being bought
	 * @return an arraylist of applicable discounts
	 */
	public ArrayList<Discount> getValidDiscounts(ArrayList<OrderLine> orders){
		ArrayList<Discount> validDiscounts = new ArrayList<>();
		for(Discount d : discountList){
			if(d.productsValid(orders) && d.isValid()){
				validDiscounts.add(d);
			}
		}
		return validDiscounts;
	}
	/**
	 * This is purely for testing, manually set available discounts.
	 * @param discounts are the available discounts
	 */
	public void setDiscounts(ArrayList<Discount> discounts){
		discountList = discounts;
	}
	
	/**
	 * This exists only to test the singleton patterns properties.
	 * @return the amount of DiscountManagers created (should never be > 1)
	 */
	public int getInstanceCount(){	
		return instanceCount;
	}
	/**
	 * This exists only to test that the class
	 * @return the discounts that are currently available in an array
	 */
	public ArrayList<Discount> getAllDiscounts(){
		return discountList;
	}
}
