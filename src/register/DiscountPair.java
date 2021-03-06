package register;

/**
 * Class that combines a product with a number, to signify the number of 
 * products. Used in the Discount-class.
 *
 */
public class DiscountPair {
	
	private Product product;
	private int productAmount;
	
	public DiscountPair(Product p, int i){
		product = p;
		productAmount = i;
	}
	
	public Product getDiscountProduct(){
		return product;
	}
	
	public int getProductAmount(){
		return productAmount;
	}
	
	/**
	 * Checks if DiscountPair has same Product and same or greater productAmount as OrderLine
	 * @param ol an OrderLine to compare to DiscountPair
	 * @return true if it is a match
	 */
	public boolean matchesOrderLine(OrderLine ol){
		return (product.equals(ol.getProduct()) && productAmount <= ol.getProductAmount());
	}
	
	public String toString(){
		return productAmount + "x " + product.getName();
	}
}
