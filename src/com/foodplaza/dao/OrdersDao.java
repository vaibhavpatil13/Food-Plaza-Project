package com.foodplaza.dao;

import java.sql.SQLException;
import java.util.List;

import com.foodplaza.pojo.Orders;

public interface OrdersDao {
	
	boolean placeOrder(String customerEmail) throws SQLException;
	
	List<Orders> showOrders() throws SQLException;
	
	List<Orders> showOrders(String customerEmail) throws SQLException;
	
	boolean cancelOrder(String customerEmail) throws SQLException;
	
	String checkOrderStatus(String customerEmail) throws SQLException;
	
	boolean updateOrderStatus(Orders o) throws SQLException;

}
