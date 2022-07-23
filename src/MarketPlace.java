import java.util.*;

public class MarketPlace {
	
	private ArrayList<User> Users = new ArrayList<>();
	private ArrayList<Product> Products = new ArrayList<>();
	private static Scanner scan = new Scanner(System.in);
	
	public void addProduct() {
		int pID;
		String pName;
		double pPrice = 0.0;
		String isPrice;
		
//Autogeneration ID
		if(Products.isEmpty())
			pID = 1000;
		else
			pID = Products.get(Products.size()-1).getID()+1;
		
//Checking input name of product correct
		do{
			System.out.println("Enter product name\t");
			pName = scan.next();
		}while(pName.isBlank() || pName.equals(null));
		
//Checking input price is a number
		do{
			System.out.println("Enter price\t");
			isPrice = scan.next();
			}while(isNumber(isPrice) == false);
		pPrice = Double.parseDouble(isPrice);
		
//Creating and add new product example 
		Products.add(new Product(pID, pName, pPrice));
		showMenu();
	}
	public void addUser() {
		int uID;
		String uFirstName;
		String uLastName;
		String isMoney;
		double uMoney;
		
//Autogeneration user ID
		if (Users.isEmpty())
			uID = 1;
		else
			uID = Users.get(Users.size()-1).getID()+1;
		
//Checking input first name and last name of product correct
		do {
			System.out.println("Enter user first name\t");
			uFirstName = scan.next();
		}while(uFirstName.isBlank() || uFirstName.equals(null));
		do {
			System.out.println("Enter user last name\t");
			uLastName = scan.next();
		}while(uLastName.isBlank() || uLastName.equals(null));
		
//Checking input amount money is number
		do {
			System.out.println("Enter amount of money");
			isMoney = scan.next();
		}while(isNumber(isMoney) == false);
		uMoney = Double.parseDouble(isMoney);
		
//Creating and add new user
		Users.add(new User(uID, uFirstName, uLastName, uMoney));

		showMenu();
	}
	
	public void deleteProduct(int productID) {
		for(Product p : Products)
			if(p.getID() == productID) {
				Products.remove(Products.indexOf(p));
				for(User u : Users)					
					u.removeProduct(productID);				//Deleting from all purchases
			}
		System.out.println("Deleting is successful");
		showMenu();
	}
	
	public void deleteUser(int userID) {
		for(User u : Users)
			if(u.getID() == userID)
				Users.remove(Users.indexOf(u));
		System.out.println("Deleting is successful");
		showMenu();
	}
	
	public void showAllUsers() {
		for(User u : Users) 
			System.out.println(u.get());
		showMenu();
	}
	
	public void showAllProducts() {
		for(Product p : Products) 
			System.out.println(p.get());
		showMenu();
	}
	
	public void buy() throws DontHaveMoneyException {
		
		System.out.println("Enter user ID\t");
		int userID = scan.nextInt();
		System.out.println("Enter product ID\t");
		int productID = scan.nextInt();

		
		for(User u : Users)
			if(u.getID() == userID) 
				for(Product p : Products)
					if(p.getID() == productID)  
								if (u.getMoney() >= p.getPrice()) {						//If enough money
									u.changeMoney(u.getMoney() - p.getPrice());			//Decreasing user money
									u.addProduct(p.getID());							//Add to user purchases
									p.addBuyer(u.getID());								//Add to list of users these has been to buy this product
									System.out.println("Purchase is successful!");
								}
								else 
									throw new DontHaveMoneyException("\nYou don have enough money\n");
		showMenu();						
	}
	
	public void showPurchases() {
		System.out.println("Enter user ID\t");
		int user = scan.nextInt();
		for(User u : Users)
			if(u.getID() == user) {
				u.showPurchases();
				break;
			}
		showMenu();
	}
	
	public void showBuyers() {
		System.out.println("Enter product ID\t");
		int product = scan.nextInt();
		for(Product p : Products)
			if(p.getID() == product) {
				p.showBuyers();
				break;
			}
		showMenu();
	}
	
	public void showMenu() {
		System.out.println("1 - SHOW ALL PRODUCTS");
		System.out.println("2 - SHOW ALL USERS");
		System.out.println("3 - BUY PRODUCT");
		System.out.println("4 - ADD NEW PRODUCT");
		System.out.println("5 - ADD NEW USER");
		System.out.println("6 - DELETE PRODUCT");
		System.out.println("7 - DELETE USER");
		System.out.println("8 - SHOPPING LIST");
		System.out.println("9 - BUYERS LIST");
		System.out.println("0 - EXIT");
		System.out.print("Make a choice\t");
		int choice = scan.nextInt();
		switch(choice) {
			case 1 -> this.showAllProducts();
			case 2 -> this.showAllUsers();
			case 3 -> {
						try{
							this.buy();
						}catch(DontHaveMoneyException e) {
							System.out.println(e.toString());
						}finally {
							this.showMenu();
						}
			}
			case 4 -> this.addProduct();
			case 5 -> this.addUser();
			case 6 -> {
				System.out.println("Enter product ID which you want delete");
				deleteProduct(scan.nextInt());
			}
			case 7 -> {
				System.out.println("Enter user ID which you want delete");
				deleteUser(scan.nextInt());
			}
			case 8 -> this.showPurchases();
			case 9 -> this.showBuyers();
			case 0 -> {
				scan.close();
				System.exit(0);
			}
		}
	}
	
//Function of checking input string is number or not
	protected boolean isNumber(String str) {
		char[] tempArray = str.trim().toCharArray();
		String compareString = "0123456789.";
		boolean flag = true;
		for(char c :tempArray)
			if(compareString.contains(Character.toString(c)))
				flag = true;
			else 
				return false;
		return flag;
				
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MarketPlace market = new MarketPlace();
		market.showMenu();
		
	}

}
