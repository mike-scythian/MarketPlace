import java.util.*;

public class Product {

	private int id;
	private String name;
	private double price;
	private ArrayList<Integer> buyers = new ArrayList<Integer>();
	
	public Product() {}
	public Product(int i, String productName, double productPrice) {
		id = i;
		name = productName;
		price = productPrice;
	}
	
	public void init(int i, String productName, double productPrice) {
		id = i;
		name = productName;
		price = productPrice;
	}
	public String get(){
        return new String(id+" "+name+" "+price+"$");
    }
	public int getID() {
    	return id;
    }
	public double getPrice() {
		return price;
	}
	public void addBuyer(int buyerID) {
		buyers.add(buyerID);
	}
	public void removeBuyer(int buyerID) {
		buyers.removeIf(b -> b.equals(Integer.valueOf(buyerID)));
	}
	public void showBuyers() {
		if(buyers.isEmpty())
			System.out.println("");
		else
			System.out.println(buyers.toString());
	}
}
