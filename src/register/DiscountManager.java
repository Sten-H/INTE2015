package register;

/**
 * The DiscountManger class keeps tracks of all the discounts available. 
 * It is implemented as a singleton, so only one instance will be used.
 * Discounts are read from a JSON-text file at creation.
 */
public class DiscountManager {
	private static DiscountManager instance = null;
	private static int instanceCount = 0; //This exists only to be certain singleton pattern is working correctly
	private DiscountManager(){
		instanceCount +=1;
	}
	public static DiscountManager getInstance(){
		if(instance == null)
		{
			instance = new DiscountManager();
		}
		return instance;
	}
	/**
	 * This exists only to test the singleton patterns properties.
	 * @return the amount of DiscountManagers created (should never be > 1)
	 */
	public int getInstanceCount(){
		return instanceCount;
	}
}
