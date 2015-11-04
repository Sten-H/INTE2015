package register;

import java.util.ArrayList;
import java.util.Date;


public class Discount {
	
	private Date startDate;
	private Date endDate;
	private double discountAmount;
	private ArrayList<DiscountPair> discountPairList;
	
	public Discount(Date start, Date end, Double db, ArrayList<DiscountPair> dlist){
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
	
	public boolean isValid(){
		Date d = new Date();
		if (startDate.compareTo(d) < 0 && endDate.compareTo(d) > 0)
			return true;
		else
			return false;
	}
	
	public String toString(){
		return "[ " + startDate.toString() + ", " + endDate.toString() + ", " + discountAmount + " ]";
	}
}
