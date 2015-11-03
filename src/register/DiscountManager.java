package register;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;


/**
 * The DiscountManger class keeps tracks of all the discounts available. 
 * It is implemented as a singleton, so only one instance will be used.
 * Discounts are read from a JSON-text file at creation.
 */
public class DiscountManager {
	private static DiscountManager instance = null;
	private static int instanceCount = 0; //This exists only to be certain singleton pattern is working correctly
	private ArrayList<String> discountList = new ArrayList<String>(); //This will not be String later on.
	private DiscountManager(){
		instanceCount +=1; //Only used for testing.
		try {
			discountList = loadDiscountsFromFile();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static DiscountManager getInstance(){
		if(instance == null)
		{
			instance = new DiscountManager();
		}
		return instance;
	}
	private ArrayList<String> loadDiscountsFromFile() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		File f = new File("resources/discounts.json");
		ArrayList<String> discounts = mapper.readValue(f, mapper.getTypeFactory().constructCollectionType(List.class, String.class));
		return discounts;
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
