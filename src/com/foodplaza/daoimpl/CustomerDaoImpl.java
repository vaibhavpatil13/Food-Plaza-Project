package com.foodplaza.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodplaza.dao.CustomerDao;
import com.foodplaza.pojo.Customers;
import com.foodplaza.pojo.Food;
import com.foodplaza.utility.DBConnection;

public class CustomerDaoImpl implements CustomerDao{
	
	Connection conn;
	PreparedStatement ps;
	java.sql.Statement st;
	ResultSet rs;
	
	Customers c = new Customers();
	List<Customers> cl = new ArrayList<>();
	
	int row;

	@Override
	public boolean addCustomer(Customers c) throws SQLException {
		// TODO Auto-generated method stub

//		String insertQuery = "insert into Food(foodName,foodPrice,foodCategory,foodType) values(?,?,?,?)";
//		
//		conn = DBConnection.establishConnection();
//		ps = conn.prepareStatement(insertQuery);
//		ps.setString(1, f.getFoodName());
//		ps.setDouble(2, f.getFoodPrice());
//		ps.setString(3, f.getFoodCategory());
//		ps.setString(4, f.getFoodType());
//		
//		row = ps.executeUpdate();
//		
//		if(row>0) {
//			return true;
//		}
//				
//	    return false;
		
		String insertQuery = "insert into Customers(customerName,customerAddress,customerEmail,customerPassword,customerContactNo) values(?,?,?,?,?)";
		
		try {
			conn = DBConnection.establishConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps = conn.prepareStatement(insertQuery);
		ps.setString(1, c.getCutomerName());
		ps.setString(2, c.getCustomerAddress());
		ps.setString(3, c.getCustomerEmail());
		ps.setString(4, c.getCustomerPassword());
		ps.setLong(5, c.getCustomerContactNo());
		
        row = ps.executeUpdate();
		
		if(row>0) {
			return true;
		}
				
	    return false;
	}

	@Override
	public boolean updateCustomer(Customers c) {
		// TODO Auto-generated method stub
		try {
			conn=DBConnection.establishConnection();
			String updateQuery = "update customers set customerName=? , customerAddress=? , customerEmail=? , customerPassword=? , customerContactNo=? where customerId=?";
			ps=conn.prepareStatement(updateQuery);
			
			ps.setString(1, c.getCutomerName());
			ps.setString(2, c.getCustomerAddress());
			ps.setString(3, c.getCustomerEmail());
			ps.setString(4, c.getCustomerPassword());
			ps.setLong(5, c.getCustomerContactNo());
			ps.setInt(6, c.getCustomerId());
			
			row = ps.executeUpdate();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		if(row>0) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteCustomer(Customers c) {
		// TODO Auto-generated method stub
		try {
			conn=DBConnection.establishConnection();
			String deleteQuery = "delete  from customers where customerId=?";
			ps=conn.prepareStatement(deleteQuery);
			
			ps.setInt(1, c.getCustomerId());
			row=ps.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(row>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Customers displayCustomer(int customerId) {
		// TODO Auto-generated method stub
		try {
			conn=DBConnection.establishConnection();
			String displayQuery = "select * from customers where customerId=?";
			ps=conn.prepareStatement(displayQuery);
			
			ps.setInt(1, customerId);
			rs=ps.executeQuery();
			while(rs.next()) {
				
				c.setCutomerName(rs.getString("customerName"));;
				c.setCustomerAddress(rs.getString("customerAddress"));
				c.setCustomerEmail(rs.getString("customerEmail"));
				c.setCustomerContactNo(rs.getLong("customerContactNo"));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return c;
	}

	@Override
	public List<Customers> displayCustomerbyName(String customerName) throws SQLException {
		// TODO Auto-generated method stub
		cl.clear();
		try {
			conn = DBConnection.establishConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String displayCustomer = "select * from Customers where customerName = ?";
		ps = conn.prepareStatement(displayCustomer);
		ps.setString(1, customerName);
		rs = ps.executeQuery();
		
		
		while(rs.next())
		{
			Customers c = new Customers();
			c.setCustomerId(rs.getInt("customerId"));
			c.setCustomerAddress(rs.getString("customerAddress"));
			c.setCustomerContactNo(rs.getLong("customerContactNo"));
			c.setCustomerEmail(rs.getString("customerEmail"));
			
			cl.add(c);
		}
		return cl;
	}

	@Override
	public Customers displayCustomerbyEmail(String customerEmail) {
		// TODO Auto-generated method stub
		try {
			conn=DBConnection.establishConnection();
			String displayQuery = "select * from customers where customerEmail=?";
			ps=conn.prepareStatement(displayQuery);
			
			ps.setString(1, customerEmail);
			rs=ps.executeQuery();
			while(rs.next()) {
				
				c.setCutomerName(rs.getString("customerName"));;
				c.setCustomerAddress(rs.getString("customerAddress"));
				c.setCustomerId(rs.getInt("customerId"));
				c.setCustomerContactNo(rs.getLong("customerContactNo"));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return c;
	}

	@Override
	public List<Customers> displayCustomer(String customerAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customers> displayAllCustomers() throws SQLException {
		// TODO Auto-generated method stub
		cl.clear();
		try {
			conn = DBConnection.establishConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String displayCustomer = "select * from Customers";
		st = conn.createStatement();
		rs = st.executeQuery(displayCustomer);
		
		
		while(rs.next())
		{
			Customers c = new Customers();
			c.setCustomerId(rs.getInt("customerId"));
			c.setCustomerAddress(rs.getString("customerAddress"));
			c.setCustomerContactNo(rs.getLong("customerContactNo"));
			c.setCustomerEmail(rs.getString("customerEmail"));
			c.setCutomerName(rs.getString("customerName"));
			
			cl.add(c);
		}
		return cl;
	}

	@Override
	public List<Customers> displayCustomerbyCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

}
