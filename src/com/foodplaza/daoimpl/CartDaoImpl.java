package com.foodplaza.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodplaza.dao.CartDao;
import com.foodplaza.pojo.Cart;
import com.foodplaza.pojo.Customers;
import com.foodplaza.utility.DBConnection;

public class CartDaoImpl implements CartDao{
	
	Connection conn;
	PreparedStatement ps;
	java.sql.Statement st;
	ResultSet rs;
	
	Cart ct = new Cart();
	List<Cart> cartList = new ArrayList<>();
	
	int row;

	@Override
	public boolean addToCart(Cart ct) {
		// TODO Auto-generated method stub 
		String queryFood = "select foodId , foodPrice from Food where foodName = ?";
		String queryCart = "insert into cart(foodQty,foodId,foodName,customerEmail,foodPrice) values(?,?,?,?,?)";
		
		try {
			conn = DBConnection.establishConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps = conn.prepareStatement(queryFood);
			ps.setString(1, ct.getFoodName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(rs.next()) {
				int foodId = rs.getInt(1);
				double foodPrice = rs.getDouble(2);
				
				ps = conn.prepareStatement(queryCart);
				ps.setInt(1,ct.getFoodQty());
				ps.setInt(2, foodId);
				ps.setString(3, ct.getFoodName());
				ps.setString(4, ct.getCustomerEmail());
				ps.setDouble(5, foodPrice);
				
				row = ps.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(row>0) {
			return true;
		}
		
		return false;
	
	}

	@Override
	public boolean deleteCart(int cartId) throws SQLException {
		// TODO Auto-generated method stub
		String deleteQuery = "delete from cart where cartId =?";
		try {
			try {
				conn = DBConnection.establishConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps=conn.prepareStatement(deleteQuery);
			ps.setInt(1,cartId);
			row=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(row>0) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteCart(String customerEmail) throws SQLException {
		// TODO Auto-generated method stub
		String deleteQuery = "delete from cart where customerEmail =?";
		try {
			conn = DBConnection.establishConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps = conn.prepareStatement(deleteQuery);
		ps.setString(1, customerEmail);
		
		row = ps.executeUpdate();
		
		if(row>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Cart> showCart() throws SQLException {
		// TODO Auto-generated method stub
		
		try {
			conn = DBConnection.establishConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String showCart = "select * from cart";
		st = conn.createStatement();
		rs = st.executeQuery(showCart);
		
		while(rs.next()) {
			Cart ct1 = new Cart();
			ct1.setCartId(rs.getInt("cartId"));
			ct1.setCustomerEmail(rs.getString("customerEmail"));
			ct1.setFoodId(rs.getInt("foodId"));
			ct1.setFoodName(rs.getString("foodName"));
			ct1.setFoodPrice(rs.getDouble("foodPrice"));
			ct1.setFoodQty(rs.getInt("foodQty"));
			
			cartList.add(ct1);
		}
		return cartList;
	}

	@Override
	public List<Cart> showCart(String customerEmail) throws SQLException {
		// TODO Auto-generated method stub
		String queryEmail = "select * from cart where customerEmail = ?";
		try {
			conn = DBConnection.establishConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps = conn.prepareStatement(queryEmail);
		ps.setString(1, customerEmail);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			Cart ct2 = new Cart();
			ct2.setCartId(rs.getInt("cartId"));
			ct2.setFoodId(rs.getInt("foodId"));
			ct2.setFoodName(rs.getString("foodName"));
			ct2.setFoodPrice(rs.getDouble("foodPrice"));
			ct2.setFoodQty(rs.getInt("foodQty"));
			
			cartList.add(ct2);
		}
		return cartList;
	}

}
