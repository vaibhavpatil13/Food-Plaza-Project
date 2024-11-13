package com.foodplaza.dao;

import java.sql.SQLException;
import java.util.List;

import com.foodplaza.pojo.Cart;

public interface CartDao {
	
	boolean addToCart(Cart ct);
	boolean deleteCart(int cartId) throws SQLException;
	boolean deleteCart(String customerEmail) throws SQLException;
	List<Cart> showCart() throws SQLException;
	List<Cart> showCart(String customerEmail) throws SQLException;

}
