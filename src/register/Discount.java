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
}