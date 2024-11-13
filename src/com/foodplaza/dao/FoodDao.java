package com.foodplaza.dao;

import java.sql.SQLException;
import java.util.List;

import com.foodplaza.pojo.Food;

public interface FoodDao {
	
	boolean addFood(Food f) throws ClassNotFoundException, SQLException;
	
	boolean updateFood(Food f);
	
	boolean deleteFood(int foodId) throws SQLException;
	
	Food displayFood(int foodId) throws ClassNotFoundException, SQLException;
	
	Food displayFood(String foodName) throws ClassNotFoundException, SQLException;
	
	List<Food> displayFood(double foodPrice) throws SQLException;
	
	List<Food> displayAllFood() throws SQLException;
	
	List<Food> displayFoodbyRange(double initialRange,double finalrange);

}
