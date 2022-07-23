import java.util.*;

public class User {
    
    private int id;
    private String fName;
    private String lName;
    private double money;
    private ArrayList<Integer> purchases = new ArrayList<Integer>();
 
    public User(){}
 
    public User(int i, String name1, String name2, double m){
        id = i;
        fName = name1;
        lName = name2;
        money = m;
    }
    
    public String get(){
        return new String(id+" "+fName+" "+lName+		" "+money+"$");
    }
    public void init(int i, String name1, String name2, double m){
        id = i;
        fName = name1;
        lName = name2;
        money = m;
    }
    public int getID() {
    	return id;
    }
    public double getMoney() {
    	return money;
    }
    public void changeMoney(double change) {
    	money = change;
    }
    public void addProduct(int productID) {
		purchases.add(Integer.valueOf(productID));
	}
	public void removeProduct(int productID) {
			purchases.removeIf(p -> p.equals(Integer.valueOf(productID)));
	}
	public void showPurchases() {
		if(purchases.isEmpty())
			System.out.println("");
		else
			System.out.println(purchases.toString());
	}

}