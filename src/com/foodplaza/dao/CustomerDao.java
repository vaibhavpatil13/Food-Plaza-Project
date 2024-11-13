package com.foodplaza.dao;

import java.sql.SQLException;
import java.util.List;

import com.foodplaza.pojo.Customers;

public interface CustomerDao {
	
	boolean addCustomer(Customers c) throws SQLException;
	
	boolean updateCustomer(Customers c);
	
	boolean deleteCustomer(Customers c);
	
	Customers displayCustomer(int customerID);
	
	List<Customers> displayCustomerbyName(String customerName) throws SQLException;
	
	Customers displayCustomerbyEmail(String customerEmail);
	
	List<Customers> displayCustomer(String customerAddress);
	
	List<Customers> displayAllCustomers() throws SQLException;
	
	List<Customers> displayCustomerbyCity(String city);
	

}
