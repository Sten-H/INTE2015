package register;

public class Product {
	String name;
	double price;
	
	public Product(String name, double price){
		
		if (name.trim().length() < 1) 
			throw new IllegalArgumentException("Name must contain at least one letter.");
		
		if (name.length() > 15) 
			throw new IllegalArgumentException("Name conatains too many letters.");
		
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