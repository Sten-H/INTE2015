package register;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * each object of "Discount" is similar to a discount coupon, having a combination
 * of products, a discount amount, and a validity time span.
 */
public class Discount {
	
	private Date startDate, endDate;
	private double discountAmount;
	private ArrayList<DiscountPair> discountPairList;
	
	public Discount(Date startDate, Date endDate, Double discountAmount, ArrayList<DiscountPair> discountPairList){
		if (discountPairList == null)
			throw new IllegalArgumentException("discountPairList cannot be null!");
		if (startDate == null)
			throw new IllegalArgumentException("startDate cannot be null!");
		if (endDate == null)
			throw new IllegalArgumentException("endDate cannot be null!");
		if (startDate.compareTo(endDate) > 0)
			throw new IllegalArgumentException("startDate cannot be later than endDate!");
		if (discountAmount <= 0)
			throw new IllegalArgumentException("discountAmount must be atleast 1!");
		if (discountPairList.size() < 1)
			throw new IllegalArgumentException("discountPairList cannot be empty!");
		
		this.startDate = startDate;
		this.endDate = endDate;
		this.discountAmount = discountAmount;
		this.discountPairList = discountPairList;		
	}
	
	public Date getStartDate(){
		return startDate;
	}
	
	public Date getEndDate(){
		return endDate;
	}
	
	public Double getDiscountAmount(){
		return discountAmount;
	}
	
	public ArrayList<DiscountPair> getDiscountPairList(){
		return discountPairList;
	}
	
	
	
	/**
	 * matches the discount with an array of products, to see if the discount is valid.
	 * @param products, a list of the products being bought.
	 * @return true if the products are valid for the discount
	 */
	public boolean productsValid(ArrayList<OrderLine> products){
		boolean wholeMatch = true;
		//For each discount pair we see if we can find a match in the list of products being bought.
		for(DiscountPair dp : discountPairList){
			boolean innerMatch = false;
			for(OrderLine ol : products){
				if(dp.matchesOrderLine(ol)){
					innerMatch = true;
					break;
				}
			}
			if(innerMatch == false){
				wholeMatch = false;
				break;	//One of the pairs in the discount didn't match, failed.
			}
		}
		return wholeMatch;
	}
	
	
	/**
	 * compares the discounts startDate and endDate with todays date, to check if the discount is valid
	 * @return true if date is valid
	 */
	public boolean isDateValid(){
		Date todaysDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if (startDate.before(todaysDate) && endDate.after(todaysDate))
			return true;
		
		//this if-statement is required for the special case where the endDate is the same as todays date.
		if (Integer.parseInt(sdf.format(endDate)) - Integer.parseInt(sdf.format(todaysDate)) == 0)
			return true;
		return false;
	}

	public String toString(){
		return "[ " + startDate.toString() + ", " + endDate.toString() + ", " + discountAmount + ", " + discountPairList + " ]";
	}
}
