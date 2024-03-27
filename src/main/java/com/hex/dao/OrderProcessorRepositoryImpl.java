package com.hex.dao;

import java.sql.SQLException;

import com.hex.exception.CustomerNotFoundException;
import com.hex.exception.OrderNotFoundException;
import com.hex.exception.ProductNotFoundException;

/**
 * This is the Interface which contains Abstract methods.
 */
public interface OrderProcessorRepositoryImpl {
	
	/**
	   * This is the Interface which contains Abstract methods.
	   */
	
	public void createCustomer() throws SQLException;
	public void deleteCustomer() throws SQLException, CustomerNotFoundException;
	public void createProduct() throws SQLException;
	public void deleteProduct() throws SQLException, ProductNotFoundException;
	public void addToCart() throws SQLException, ProductNotFoundException, CustomerNotFoundException;
	public void removeFromCart() throws SQLException, ProductNotFoundException;
	public void getAllFromCart() throws SQLException;
	public void placeOrder() throws SQLException, CustomerNotFoundException, ProductNotFoundException;
	public void getOrdersByCustomer() throws SQLException, CustomerNotFoundException, OrderNotFoundException;
	public void viewAllCustomers() throws SQLException;
	public void viewAllProducts() throws SQLException;
	public void viewAllOrders() throws SQLException;

}
