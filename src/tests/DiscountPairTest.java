package tests;
import static org.junit.Assert.*;
import register.DiscountPair;
import register.Product;

import java.util.*;
import org.junit.Test;

public class DiscountPairTest {
	
	@Test
	public void testSimpleConstructor(){
		Product p = new Product("A", 10);
		int i = 1;
		DiscountPair pair = new DiscountPair(p, i);
		assertEquals(pair.getDiscountProduct(), p);
		assertEquals(pair.getDiscountAmount(), i);
	}
}
