package register;

public class Product {
	String name;
	double price;
	
	public Product(String name, double price){
		
		if(name == null)
			throw new NullPointerException("Name can not be null.");
		
		if (name.trim().length() < 1) 
			throw new IllegalArgumentException("Name must contain at least one letter.");
		
		if (name.length() > 15) 
			throw new IllegalArgumentException("Name contains too many letters.");
		
		if (price < 0) 
			throw new IllegalArgumentException("Price can not be negative");
		
		this.name = name;
		this.price = price;
	}
	
	public String getName(){
		return name;
	}
	public double getPrice(){
		return price;
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof Product){
			Product p = (Product)o;
			//FIXME comparing names is kind of weak. We might need a product ID.
			return name.equals(p.getName());
		}
		return false;
	}
	public int hashCode() {
		return name.hashCode();
	}
}