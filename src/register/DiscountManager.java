package register;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;


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
		int startDays[] = {1, 4, 6, 10};
		int endDays[] = {3, 6, 10, 15};
		String products[] = {"Paj", "Tzay", "Gurka", "Glass"};
		double discountAmount[] = {20.0, 14.3, 11.4, 4.5};
		Discount d;
		
		for(int i = 0; i < startDays.length; i++){
			cal.set(Calendar.DATE, startDays[i]);
			start = cal.getTime();
			cal.set(Calendar.DATE, endDays[i]);
			end = cal.getTime();
			//Create Discount Pair placeholder
			ArrayList<DiscountPair> dlist = new ArrayList<DiscountPair>();
			DiscountPair dp = new DiscountPair();
			
			dlist.add(dp);
			d = new Discount(start, end, discountAmount[i], dlist);
			discountList.add(d);
		}
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
	public ArrayList<Discount> getDiscounts(){
		return discountList;
	}
}
