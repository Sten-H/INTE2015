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
}