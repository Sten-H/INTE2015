package register;

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
	
	public boolean isApplicable(OrderLine ol){
		return (product.equals(ol.getProduct()) && productAmount <= ol.getProductAmount());
	}
	
	public String toString(){
		return "[ " + product.getName() + ", " + productAmount + " ]";
	}
}
