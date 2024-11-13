package com.foodplaza.daoimpl;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodplaza.dao.OrdersDao;
import com.foodplaza.pojo.Food;
import com.foodplaza.pojo.Orders;
import com.foodplaza.utility.DBConnection;

public class OrdersDaoImpl implements OrdersDao {
	

	Connection conn;
	PreparedStatement ps;
	java.sql.Statement st;
	ResultSet rs;
	
	String foodName;
	String orderStatus;
	double totalBill;
	int row;
	
	//Orders o = new Orders();
	List<String> name = new ArrayList<>();
	List<Orders> orderList = new ArrayList<>();

	@Override
	public boolean placeOrder(String customerEmail) throws SQLException {
		// TODO Auto-generated method stub
		String foodQuery = "select foodName from cart where customerEmail = ?";
		
		String cartQuery = "select sum(foodprice*foodQty) as totalBill from cart where customerEmail = ?";
		
		String ordersQuery = "insert into orders(foodName,customerEmail,orderStatus,orderDate,totalBill) values(?,?,?,?,?)";
		
		try {
			conn = DBConnection.establishConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps = conn.prepareStatement(foodQuery);
		ps.setString(1, customerEmail);
		rs = ps.executeQuery();
		
		StringBuilder foodNameBuilder = new StringBuilder();
		
		
		while(rs.next()) {
			//foodName = rs.getString(1);
//			name.add(rs.getString(1));
			
			if(foodNameBuilder.length()>0) {
				foodNameBuilder.append(",");
			}
			foodNameBuilder.append(rs.getString("foodName"));
		}
		
		String foodNames = foodNameBuilder.toString();
		//Array obj = name.toArray();
		
		ps = conn.prepareStatement(cartQuery);
		ps.setString(1, customerEmail);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			totalBill = rs.getDouble(1);
		}
		
		ps = conn.prepareStatement(ordersQuery);
		//ps.setString(1, foodName);
		//ps.setArray(1, obj);
		
		ps.setString(1, foodNames);
		ps.setString(2 , customerEmail);
		ps.setString(3,"Pending");
		ps.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
		ps.setDouble(5, totalBill);
		
		row = ps.executeUpdate();
		
		if(row>0) {
			return true;
		}
		
		
		
		return false;
	}

	@Override
	public List<Orders> showOrders() throws SQLException {
		// TODO Auto-generated method stub
		String showOrdersQuery = "select * from orders";
		try {
			conn = DBConnection.establishConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		st = conn.createStatement();
		rs = st.executeQuery(showOrdersQuery);
		
		while(rs.next()) {
			Orders o1 = new Orders();
			
			o1.setOrderId(rs.getInt("orderId"));
			o1.setCustomerEmail(rs.getString("customerEmail"));
			o1.setFoodName(rs.getString("foodName"));
			o1.setOrderDate(rs.getString("orderDate"));
			o1.setOrderStatus(rs.getString("orderStatus"));
			o1.setTotalBill(rs.getDouble("totalBill"));
			
			orderList.add(o1);
		}
		return orderList;
	}

	@Override
	public List<Orders> showOrders(String customerEmail) throws SQLException {
		// TODO Auto-generated method stub
		
		String showOrderbyEmail = "select * from orders where customerEmail =?";
		try {
			conn = DBConnection.establishConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ps = conn.prepareStatement(showOrderbyEmail);
		ps.setString(1, customerEmail);
		rs = ps.executeQuery();
		
		while(rs.next()) {
	Orders o2 = new Orders();
			
			o2.setOrderId(rs.getInt("orderId"));
			o2.setCustomerEmail(rs.getString("customerEmail"));
			o2.setFoodName(rs.getString("foodName"));
			o2.setOrderDate(rs.getString("orderDate"));
			o2.setOrderStatus(rs.getString("orderStatus"));
			o2.setTotalBill(rs.getDouble("totalBill"));
			
			orderList.add(o2);
		}
		return orderList;
	}

	@Override
	public boolean cancelOrder(String customerEmail) throws SQLException {
		// TODO Auto-generated method stub
		String cancelQuery = "delete from orders where customerEmail =?";
		
		try {
			conn = DBConnection.establishConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ps = conn.prepareStatement(cancelQuery);
		ps.setString(1, customerEmail);
		
		row = ps.executeUpdate();
		
		if(row>0) {
			return true;
		}
		
		return false;
	}

	@Override
	public String checkOrderStatus(String customerEmail) throws SQLException {
		// TODO Auto-generated method stub
		
		String OrderStatusQuery = "select orderStatus from orders where customerEmail = ?";
		
		try {
			conn = DBConnection.establishConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps = conn.prepareStatement(OrderStatusQuery);
		ps.setString(1, customerEmail);
		rs = ps.executeQuery();
		
		while(rs.next()) {
			orderStatus = rs.getString(1);
		}
         return orderStatus;
	}

	@Override
	public boolean updateOrderStatus(Orders o) throws SQLException {
		// TODO Auto-generated method stub
		String UpdateStatus = "update orders set orderStatus = ? where customerEmail = ?";
		try {
			conn = DBConnection.establishConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ps =conn.prepareStatement(UpdateStatus);
		
		ps.setString(1,o.getOrderStatus());
		ps.setString(2, o.getCustomerEmail());
		
		//rs = ps.executeQuery();
		row = ps.executeUpdate();
		//Orders o2 = new Orders();
			if(row>0)
			{
				return true;
			
			}
			else
			{
				return false;
			}
	}

}
