package com.foodplaza.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.foodplaza.daoimpl.CartDaoImpl;
import com.foodplaza.daoimpl.CustomerDaoImpl;
import com.foodplaza.pojo.Cart;

public class CartService {
	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		boolean flag=true;
		int choice;
		
		int cartId,foodQty,foodId;
		String foodName,customerEmail;
		Double foodPrice;
		
		Cart ct = new Cart();
		CartDaoImpl ctdi = new CartDaoImpl();
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		List<Cart> cartList = new ArrayList<>();
		
		do {
			
			System.out.println("1.Add to cart\n2.delete from cart\n3.delete cart by customer emailID\n4.Show cart\n5.Show cart by Email");
			System.out.println("Enter your choice");
			choice = sc.nextInt();
			
			switch(choice) {
			
			case 1:
				
				System.out.println("Enter customer Email");
				customerEmail = sc.next();
				
				if(cdi.displayCustomerbyEmail(customerEmail) != null) {
					System.out.println("Enter food name");
					foodName = sc.next();
					System.out.println("Enter food quantity");
					foodQty = sc.nextInt();
					
					ct.setCustomerEmail(customerEmail);
					ct.setFoodName(foodName);
					ct.setFoodQty(foodQty);
					
					flag = ctdi.addToCart(ct);
				}
				else {
					System.out.println("email is not valid");
				}
				
				if(flag) {
					System.out.println("Cart added successfully");
				}
				else {
					System.out.println("something went wrong");
				}
				
				break;
				
			case 2:
				
				System.out.println("Enter cartID");
				cartId = sc.nextInt();
				
				flag = ctdi.deleteCart(cartId);
				
				if(flag) {
					System.out.println("cart deleted successfully");
				}
				else {
					System.out.println("something went wrong");
				}
				
				break;
				
			case 3:
				
				System.out.println("Enter customer emailID");
				customerEmail = sc.next();
				
				flag = ctdi.deleteCart(customerEmail);
				
				if(flag) {
					System.out.println("cart deleted successfully");
				}
				else {
					System.out.println("something went wrong");
				}
				
				break;
				
			case 4:
				
				cartList = ctdi.showCart();
				
				for(Cart c1 : cartList) {
					System.out.println(c1.getCartId()+","+c1.getCustomerEmail()+","+c1.getFoodId()+","+c1.getFoodName()+","+c1.getFoodPrice()+","+c1.getFoodQty());
					
				}
				break;
				
			case 5:
				
				System.out.println("Enter customer email id");
				customerEmail = sc.next();
				
				cartList = ctdi.showCart(customerEmail);
				
				for(Cart c1 : cartList) {
					System.out.println(c1.getCartId()+","+c1.getFoodId()+","+c1.getFoodName()+","+c1.getFoodPrice()+","+c1.getFoodQty());
					
				}
				break;
				
				
			}			
		
		}
		while(choice>5);
	}

}
