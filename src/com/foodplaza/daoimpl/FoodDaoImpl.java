package com.foodplaza.daoimpl;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodplaza.dao.FoodDao;
import com.foodplaza.pojo.Food;
import com.foodplaza.utility.DBConnection;

public class FoodDaoImpl implements FoodDao {
	
	Connection conn;
	PreparedStatement ps;
	java.sql.Statement st;
	ResultSet rs;
	Food f = new Food();
	List<Food> fl = new ArrayList<>();
	
	int row;

	@Override
	public boolean addFood(Food f) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		String insertQuery = "insert into Food(foodName,foodPrice,foodCategory,foodType) values(?,?,?,?)";
		
		conn = DBConnection.establishConnection();
		ps = conn.prepareStatement(insertQuery);
		ps.setString(1, f.getFoodName());
		ps.setDouble(2, f.getFoodPrice());
		ps.setString(3, f.getFoodCategory());
		ps.setString(4, f.getFoodType());
		
		row = ps.executeUpdate();
		
		if(row>0) {
			return true;
		}
				
	    return false;
	}

	@Override
	public boolean updateFood(Food f) {
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
		String updatequery = "update Food set foodName =?,foodCategory=?,foodPrice=?,foodType=? where foodId = ?";
		try {
			ps = conn.prepareStatement(updatequery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ps.setString(1,f.getFoodName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps.setString(2, f.getFoodCategory());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps.setDouble(3,f.getFoodPrice());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps.setString(4, f.getFoodType());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps.setInt(5,f.getFoodId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			row = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(row>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean deleteFood(int foodId) throws SQLException {
		// TODO Auto-generated method stub
		try {
			conn = DBConnection.establishConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String deletequery = "delete from Food where foodid = ?";
		ps = conn.prepareStatement(deletequery);
		
		ps.setInt(1,foodId);
		row = ps.executeUpdate();
		if(row>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public Food displayFood(String foodName) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		conn = DBConnection.establishConnection();
		String displayQueryId = "select * from Food where foodName = ?";
		ps = conn.prepareStatement(displayQueryId);
		
		Food f3 = new Food();
		
		ps.setString(1, foodName);
		rs = ps.executeQuery();
		
		
		while(rs.next()) {
			f3.setFoodId(rs.getInt("foodId"));
			f3.setFoodName(rs.getString("foodName"));
			f3.setFoodCategory(rs.getString("foodCategory"));
			f3.setFoodType(rs.getString("foodType"));
			f3.setFoodPrice(rs.getDouble("foodPrice"));
		}
		
		return f3;
		
	}

	@Override
	public Food displayFood(int foodId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		conn = DBConnection.establishConnection();
		String displayQueryId = "select * from Food where foodId = ?";
		ps = conn.prepareStatement(displayQueryId);
		
		Food f3 = new Food();
		
		ps.setInt(1, foodId);
		rs = ps.executeQuery();
		
		
		while(rs.next()) {
			f3.setFoodId(rs.getInt("foodId"));
			f3.setFoodName(rs.getString("foodName"));
			f3.setFoodCategory(rs.getString("foodCategory"));
			f3.setFoodType(rs.getString("foodType"));
			f3.setFoodPrice(rs.getDouble("foodPrice"));
		}
		
		return f3;
	}

	@Override
	public List<Food> displayFood(double foodPrice) throws SQLException {
		// TODO Auto-generated method stub
		fl.clear();
		try {
			conn = DBConnection.establishConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String displayfood = "select * from Food where foodprice = ?";
		ps = conn.prepareStatement(displayfood);
		ps.setDouble(1, foodPrice);
		rs = ps.executeQuery();
		
		
		while(rs.next())
		{
			Food fs = new Food();
			fs.setFoodId(rs.getInt("foodId"));
			fs.setFoodName(rs.getString("foodName"));
			fs.setFoodPrice(rs.getDouble("foodPrice"));
			fs.setFoodCategory(rs.getString("foodCategory"));
			fs.setFoodType(rs.getString("foodType"));
			
			fl.add(fs);
		}
		return fl;
	}

	@Override
	public List<Food> displayAllFood() throws SQLException {
		// TODO Auto-generated method stub
		fl.clear();
		try {
			conn = DBConnection.establishConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String displayfood = "select * from Food";
		st = conn.createStatement();
		rs = st.executeQuery(displayfood);
		
		
		while(rs.next())
		{
			Food fs = new Food();
			fs.setFoodId(rs.getInt("foodId"));
			fs.setFoodName(rs.getString("foodName"));
			fs.setFoodPrice(rs.getDouble("foodPrice"));
			fs.setFoodCategory(rs.getString("foodCategory"));
			fs.setFoodType(rs.getString("foodType"));
			
			fl.add(fs);
		}
		return fl;
	}

	@Override
	public List<Food> displayFoodbyRange(double initialRange, double finalrange) {
		// TODO Auto-generated method stub
		return null;
	}

}
