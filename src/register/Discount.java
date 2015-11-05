package register;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;




public class Discount {
	
	private Date startDate;
	private Date endDate;
	private double discountAmount;
	private ArrayList<DiscountPair> discountPairList;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	public Discount(Date start, Date end, Double db, ArrayList<DiscountPair> dlist){
		if (dlist == null)
			throw new IllegalArgumentException();
		if (start == null)
			throw new IllegalArgumentException();
		if (end == null)
			throw new IllegalArgumentException();
		if (start.compareTo(end) > 0)
			throw new IllegalArgumentException();
		if (db <= 0)
			throw new IllegalArgumentException();
		if (dlist.size() < 1)
			throw new IllegalArgumentException();
		
		startDate = start;
		endDate = end;
		discountAmount = db;
		discountPairList = dlist;		
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
	
	public boolean productsValid(ArrayList<OrderLine> products){
		boolean wholeMatch = true;
		//For each discount pair we see if we can find a match in the list of products being bought.
		for(DiscountPair dp : discountPairList){
			boolean innerMatch = false;
			for(OrderLine ol : products){
				if(dp.isApplicable(ol)){
					innerMatch = true;
					break;
				}
			}
			if(innerMatch == false){
				wholeMatch = false;
				break;	//One of the pairs in the discount didn't match, failed.
			}
			else {
				innerMatch = false;	//It was a match, now we reset and go again.
			}
		}
		return wholeMatch;
	}
	
	public boolean isValid(){
		Date d = new Date();
		if (startDate.before(d) && endDate.after(d))
			return true;
		if (Integer.parseInt(sdf.format(endDate)) - Integer.parseInt(sdf.format(d)) == 0)
			return true;
		return false;
	}

	public String toString(){
		return "[ " + startDate.toString() + ", " + endDate.toString() + ", " + discountAmount + ", " + discountPairList + " ]";
	}
}
