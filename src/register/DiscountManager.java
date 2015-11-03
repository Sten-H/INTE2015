package register;

import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * The DiscountManger class keeps tracks of all the discounts available. 
 * It is implemented as a singleton, so only one instance will be used.
 * Discounts are read from a JSON-text file at creation.
 */
public class DiscountManager {
	private static DiscountManager instance = null;
	private static int instanceCount = 0; //This exists only to be certain singleton pattern is working correctly
	private ArrayList<String> discountList = new ArrayList<>(); //This will not be String later on.
	private DiscountManager(){
		instanceCount +=1; //Only used for testing.
		loadDiscountsFromFile();
	}
	public static DiscountManager getInstance(){
		if(instance == null)
		{
			instance = new DiscountManager();
		}
		return instance;
	}
	private void loadDiscountsFromFile(){
		
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
	public ArrayList<String> getDiscounts(){
		return discountList;
	}
}
