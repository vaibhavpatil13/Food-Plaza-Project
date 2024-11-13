package com.foodplaza.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.foodplaza.daoimpl.CustomerDaoImpl;
import com.foodplaza.pojo.Customers;
import com.foodplaza.pojo.Food;

public class CustomerService {
	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		int choice;
		boolean flag;
		
		int customerId;
		String customerName,customerEmail,customerAddress,customerPassword;
		long customerContactNo;
		
		Customers c = new Customers();
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		
		List<Customers> customerList = new ArrayList<>();
		
		
		
		do {
			System.out.println("1.Add customer\n2.Update Customer\n3.Delete customer\n4.Display Customer by Id\n5.Display Customer by Name\n6.Display Customer by Email\n7.Display all Customers");
			
			System.out.println("Enter Your Choice:");
			choice = sc.nextInt();
			
			switch(choice) {
			
			case 1:
				System.out.println("Enter Customer Name");
				customerName = sc.next();
				
				System.out.println("Enter Customer email");
				customerEmail = sc.next();
				
				sc.nextLine();
				System.out.println("Enter Customer Address");
				customerAddress = sc.nextLine();
				
				System.out.println("Enter Customer Password");
				customerPassword = sc.next();
				
				System.out.println("Enter contact number");
				customerContactNo = sc.nextLong();
				
				
				c.setCutomerName(customerName);
				c.setCustomerEmail(customerEmail);
				c.setCustomerAddress(customerAddress);
				c.setCustomerPassword(customerPassword);
				c.setCustomerContactNo(customerContactNo);
				
				flag = cdi.addCustomer(c);
				
				if(flag) {
					System.out.println("Customer Added Successfully");
				}
				else {
					System.out.println("Something went Wrong");
				}
				
				break;
				
			case 2:
				System.out.println("Enter customerId:");
				customerId=sc.nextInt();
			
				System.out.println("Enter Customer Name");
				customerName=sc.next();
				
				sc.nextLine();
				System.out.println("Enter Customer Address");
				customerAddress=sc.nextLine();
				
				System.out.println("Enter Customer Email");
				customerEmail=sc.next();
				
				System.out.println("Enter Customer Password");
				customerPassword=sc.next();
				
				System.out.println("Enter Customer Contact Number");
				customerContactNo=sc.nextLong();
				
				c.setCustomerId(customerId);
				c.setCutomerName(customerName);
				c.setCustomerAddress(customerAddress);
				c.setCustomerEmail(customerEmail);
				c.setCustomerPassword(customerPassword);
				c.setCustomerContactNo(customerContactNo);
				
				flag=cdi.updateCustomer(c);
				
				if(flag)
				{
					System.out.println("Customer updated successfully");
					
				}
				else
				{
					System.out.println("Something went wrong....");
				}
				
				break;
				
			case 3:
				System.out.println("Enter customerId:");
				customerId=sc.nextInt();
				
				c.setCustomerId(customerId);
				
				flag=cdi.deleteCustomer(c);
				
				if(flag)
				{
					System.out.println("Customer Deleted successfully");
					
				}
				else
				{
					System.out.println("Something went wrong....");
				}
				
				break;
				
			case 4:
				System.out.println("Enter CustomerId:");
				customerId=sc.nextInt();
				
				//f.setFoodId(foodId);
				
				c=cdi.displayCustomer(customerId);
				
				System.out.println(c.getCutomerName()+" "+c.getCustomerAddress()+" "+c.getCustomerEmail()+" "+c.getCustomerContactNo());

				break;
				
			case 5:
				System.out.println("Enter customer name: ");
				customerName = sc.next();
			    customerList = cdi.displayCustomerbyName(customerName);
				
				for(Customers c1 : customerList)
				{

					System.out.println(c1.getCustomerId()+" "+c1.getCustomerAddress() +" "+ c1.getCustomerContactNo() +" "+ c1.getCustomerEmail());
				}
				break;
				
			case 6:
				System.out.println("Enter Customer Email");
				customerEmail =sc.next();
				
				
				c=cdi.displayCustomerbyEmail(customerEmail);
				
				System.out.println(c.getCustomerId()+" "+c.getCutomerName()+" "+c.getCustomerAddress()+" "+c.getCustomerContactNo());

				break;
				
			case 7:
				customerList = cdi.displayAllCustomers();
				
				for(Customers c2 : customerList) {
					System.out.println(c2.getCustomerId()+","+c2.getCutomerName()+","+c2.getCustomerAddress()+","+c2.getCustomerEmail()+","+c2.getCustomerContactNo());
				}
				break;
			}
			
			
		}
		while(choice>5);
	}

}
