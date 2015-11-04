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
	
	public boolean isValid(){
		Date d = new Date();
		if (startDate.before(d) && endDate.after(d))
			return true;
		if (Integer.parseInt(sdf.format(endDate)) - Integer.parseInt(sdf.format(d)) == 0)
			return true;
		return false;
	}
}
