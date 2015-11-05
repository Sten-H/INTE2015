package register;

public class DiscountPair {
	
	private Product product;
	private int amount;
	
	public DiscountPair(Product p, int i){
		product = p;
		amount = i;
	}
	
	public Product getDiscountProduct(){
		return product;
	}
	
	public int getDiscountAmount(){
		return amount;
	}
	
	public boolean isApplicable(OrderLine ol){
		//FIXME this should use actual comparators or something fancier.
		return (product.getName().equals(ol.getProduct().getName()) && 
				amount <= ol.getProductAmount());
	}
}
