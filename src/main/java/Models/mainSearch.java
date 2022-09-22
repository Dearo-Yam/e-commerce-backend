package Models;
import java.util.ArrayList;
import java.util.HashMap;



public class mainSearch {
	int userID;
	HashMap<Integer, ArrayList<String>> UserData 
	= new HashMap<Integer,  ArrayList<String>>();
	
	HashMap<Integer, ArrayList<String>> Products 
	= new HashMap<Integer,  ArrayList<String>>();
	
	HashMap<Integer, ArrayList<String>> Orders 
	= new HashMap<Integer,  ArrayList<String>>();
	
	HashMap<Integer, ArrayList<String>> OrderItems 
	= new HashMap<Integer,  ArrayList<String>>();
	
	HashMap<Integer, ArrayList<String>> CreditCard 
	= new HashMap<Integer,  ArrayList<String>>();
	
	HashMap<Integer, ArrayList<String>> CartItems 
	= new HashMap<Integer,  ArrayList<String>>();
	
	HashMap<Integer, ArrayList<String>> Addresses 
	= new HashMap<Integer,  ArrayList<String>>();
	
	public HashMap<Integer, ArrayList<String>> getProducts() {
		return Products;
	}

	public void setProducts(HashMap<Integer, ArrayList<String>> products) {
		Products = products;
	}

	public HashMap<Integer, ArrayList<String>> getOrders() {
		return Orders;
	}

	public void setOrders(HashMap<Integer, ArrayList<String>> orders) {
		Orders = orders;
	}

	public HashMap<Integer, ArrayList<String>> getOrderItems() {
		return OrderItems;
	}

	public void setOrderItems(HashMap<Integer, ArrayList<String>> orderItems) {
		OrderItems = orderItems;
	}

	public HashMap<Integer, ArrayList<String>> getCreditCard() {
		return CreditCard;
	}

	public void setCreditCard(HashMap<Integer, ArrayList<String>> creditCard) {
		CreditCard = creditCard;
	}

	public HashMap<Integer, ArrayList<String>> getCartItems() {
		return CartItems;
	}

	public void setCartItems(HashMap<Integer, ArrayList<String>> cartItems) {
		CartItems = cartItems;
	}

	public HashMap<Integer, ArrayList<String>> getAddresses() {
		return Addresses;
	}

	public void setAddresses(HashMap<Integer, ArrayList<String>> addresses) {
		Addresses = addresses;
	}
	
	public HashMap<Integer, ArrayList<String>> getUserData() {
		return UserData;
	}

	public void setUserData(HashMap<Integer, ArrayList<String>> userData) {
		UserData = userData;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	
	
	
	
}
