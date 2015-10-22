/**
 * An order is built up by multiple OrderLine classes,
 * one OrderLine for each type of product bought. The
 * OrderLine keeps track of how many of one type of product
 * is being bought and it can calculate the total price of itself.
 */
public class OrderLine {
	private Product product;
	private int productAmount;
	/**
	 * An OrderLine constructor needs a product
	 * and an integer expressing the amount to buy.
	 * @param p is the product, cannot be null.
	 * @param amount is how many of the product, must be greater or equal to 1.
	 */
	public OrderLine(Product p, int amount){
		if(p == null)
			throw new NullPointerException("Product can not be null.");
		product = p;
		if(amount < 1)
			throw new IllegalArgumentException("productAmount can not be less than 1.");
		productAmount = amount;
	}
	/**
	 * Calculates the total price of the orderline
	 * and returns it as a double
	 * @return the total price of all products in the line.
	 */
	public double getTotalPrice(){
		return product.getPrice() * productAmount;
	}
}
