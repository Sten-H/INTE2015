package register;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * The DiscountManger class is a singleton which keeps track
 * of all the available discounts and can return the valid
 * discounts based on what is being bought. 
 */
public class DiscountManager {
	private static DiscountManager instance = null;
	private static int instanceCount = 0; //This exists only to be certain singleton pattern is working correctly
	private ArrayList<Discount> discountList = new ArrayList<>();
	private DiscountManager(){
		instanceCount +=1; // Only used for testing.
		// In a real world setting, discounts might be loaded in from a database here?
	}
	public static DiscountManager getInstance(){
		if(instance == null)
			instance = new DiscountManager();
		return instance;
	}

	/**
	 * getValidDiscounts will return an arraylist of discounts
	 * that are valid based on the products being bought.
	 * @param orderLineList are the products being bought.
	 * @return an arraylist of valid Discounts.
	 */
	public ArrayList<Discount> getValidDiscounts(ArrayList<OrderLine> orderLineList){
		ArrayList<Discount> validDiscounts = new ArrayList<>();
		for(Discount d : discountList){
			if(d.productsValid(orderLineList) && d.isDateValid()){
				validDiscounts.add(d);
			}
		}
		return validDiscounts;
	}
	 
	// This is purely for testing, manually sets available discounts.
	public void setDiscounts(ArrayList<Discount> discountList){
		this.discountList = discountList;
	}

	// This method exists only to test the singleton pattern's properties.
	public int getInstanceCount(){	
		return instanceCount;
	}
}
