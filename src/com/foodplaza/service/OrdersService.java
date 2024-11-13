package com.foodplaza.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.foodplaza.daoimpl.CartDaoImpl;
import com.foodplaza.daoimpl.OrdersDaoImpl;
import com.foodplaza.pojo.Orders;

public class OrdersService {
	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		
		int orderId;
		String foodName,customerEmail,orderStatus,orderDate;
		double totalBill;
		int choice;
		boolean flag;
		
		Orders o = new Orders();
		CartDaoImpl ctd = new CartDaoImpl();
		OrdersDaoImpl odi = new OrdersDaoImpl();
		List<Orders> list = new ArrayList<>();
		
		do {
			
			System.out.println("1.Place order\n2.Show Orders\n3.Show Orders by Customer Email\n4.Deleter Order\n5.Check Order Status\n6.Update Order Status");
			
			System.out.println("Enter your choice");
			
			choice = sc.nextInt();
			
			switch(choice) {
			
			case 1:
				System.out.println("Enter customer email id");
				customerEmail = sc.next();
				
				if(ctd.showCart(customerEmail)!= null) {
					flag = odi.placeOrder(customerEmail);
					
					if(flag) {
						System.out.println("order placed successfully");
					}else {
						System.out.println("something went wrong");
					}
				}else {
					System.out.println("Enter valid customer email id");
				}
				
				break;
				
			case 2:
				list = odi.showOrders();
				 for(Orders o1: list) {
					 System.out.println(o1.getOrderId()+","+o1.getCustomerEmail()+","+o1.getFoodName()+","+o1.getOrderDate()+","+o1.getOrderStatus()+","+o1.getTotalBill());
						
				 }
				 break;
				
			case 3:
				System.out.println("Enter Email id");
				customerEmail = sc.next();
				list = odi.showOrders(customerEmail);
				
				 for(Orders o2: list) {
					 System.out.println(o2.getOrderId()+","+o2.getCustomerEmail()+","+o2.getFoodName()+","+o2.getOrderDate()+","+o2.getOrderStatus()+","+o2.getTotalBill());
						
				 }
				 break;
				
			case 4:
				System.out.println("Enter Email id");
				customerEmail = sc.next();
				
				flag = odi.cancelOrder(customerEmail);
				
				if(flag) {
					System.out.println("Order cancelled Successfully");
				}
				else {
					System.out.println("something went wrong");
				}
				break;
				
			case 5:
				System.out.println("Enter Email Id");
				customerEmail = sc.next();
				
				orderStatus = odi.checkOrderStatus(customerEmail);
				
				System.out.println("Your order is "+orderStatus);
				break;
				
			case 6:
				System.out.println("Enter email id");
				customerEmail = sc.next();
				
				System.out.println("Enter order Status");
				orderStatus = sc.next();
				
				o.setCustomerEmail(customerEmail);
				o.setOrderStatus(orderStatus);
				
				flag = odi.updateOrderStatus(o);
				
				if(flag) {
					System.out.println("stetus updated sucessfully");
				}else {
					System.out.println("something went wrong");
				}
				break;
				
			}
		}
		
		while(choice>5);
	}

}
