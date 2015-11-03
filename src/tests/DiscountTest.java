package tests;
import static org.junit.Assert.*;
import register.Discount;
import register.DiscountPair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.Test;

public class DiscountTest {

	private Calendar cal;
	private Double db;
	private ArrayList<DiscountPair> dlist;
	private Date start;
	private Date end;
	
	private void setupSimpleConstructor(){
		cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, 2015);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 1);
		start = cal.getTime();
		end = cal.getTime();
		db = (double) 1;
		ArrayList<DiscountPair> dlist = new ArrayList<DiscountPair>();
	}
	
	@Test
	public void testSimpleConstructor() throws ParseException{
		setupSimpleConstructor();	
		Discount d = new Discount(start, end, db, dlist);
		assertEquals(d.getStartDate(), start);
		assertEquals(d.getEndDate(), end);
		assertEquals(d.getDiscountAmount(), 1,0);
		assertEquals(d.getDiscountPairList(), dlist);
	}
}
