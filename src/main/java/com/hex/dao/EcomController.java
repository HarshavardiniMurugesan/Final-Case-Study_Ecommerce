package com.hex.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hex.dao.*;
import com.hex.entity.*;
import com.hex.exception.*;

/**
 * This is the Controller class which contains Business Logics.
 */
public class EcomController implements OrderProcessorRepositoryImpl {
	
	/**
	   * This is the Controller class which contains Business Logics.
	   * @implements OrderProcessorRepositoryImpl OrderProcessorRepositoryImpl contains all abstract class.
  */
	Scanner sc=new Scanner(System.in);
	EcomDao dao=new EcomDao();
	Customer c=new Customer();
	Product p=new Product();
    Cart cr=new Cart();
    Orderss o=new Orderss();
    OrderItems oi=new OrderItems();
    //EcomController ee=new EcomController();
	
	
	public void createCustomer() throws SQLException {
		
		/**
		   * This method is used to create customer details. 
		   * @return nothing.
		   * @throws SQLException On SQL Database error.
		   */
		
		/*System.out.println("Enter Customer ID:");
		int newId = sc.nextInt();
		c.setCustomerId(newId);*/

		System.out.println("Enter Name");
		String newName = sc.next();
		c.setName(newName);

		System.out.println("Enter Email");
		String newEmail = sc.next();
		boolean x=isValidEmail(newEmail);
		
        if(x==true) {
            System.out.println(newEmail + " is valid ");
        	c.setEmail(newEmail);
        }else {
        	System.out.println(newEmail + " is not valid\nEnter valid Email[Format: xyz@gmail.com]");
        	String newEmail2 = sc.next();
        	c.setEmail(newEmail2);

        }
		
        System.out.println("Enter Password");
		String newPassword = sc.next();
		c.setPassword(newPassword);
		dao.createCustomer(c);
		
	}
	
	public boolean isValidEmail(String email1) {
		
		String emailFormat = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.matches(emailFormat, email1);
        
    }
    
	public void deleteCustomer() throws SQLException, CustomerNotFoundException {
		
		/**
		   * This method is used to delete customer details. 
		   * @throws SQLException On SQL Database error.
		   * @throws CustomerNotFoundException.
		   * @return nothing.
		   */

		
		System.out.println("Enter Customer ID:");
		int newId = sc.nextInt();
		c.setCustomerId(newId);
		dao.deleteCustomer(newId);
		
	}
	public void createProduct() throws SQLException {
		
		/**
		   * This method is used to create Product details. 
		   * @return nothing.
		   * @throws SQLException On SQL Database error.
		   */
		
		/*System.out.println("Enter Product ID:");
		int newId = sc.nextInt();
		p.setProductId(newId);*/
		//String s=" ";

		System.out.println("Enter Product Name");
		String newName = sc.next();
		sc.nextLine();
		p.setProductName(newName);
		
		System.out.println("Enter Price:");
		double newPrice = sc.nextDouble();
		p.setPrice(newPrice);

		System.out.println("Enter Description");
		String newDes = sc.next();
		sc.nextLine();
		p.setDescription(newDes);

		System.out.println("Enter Stock Quantity");
		int newQuantity = sc.nextInt();
		p.setStockQuantity(newQuantity);
		dao.createProduct(p);
		
		
	}
	public void deleteProduct() throws SQLException, ProductNotFoundException {
		
		/**
		   * This method is used to delete product details. 
		   * @return nothing.
		   * @throws SQLException On SQL Database error.
		   * @throws ProductNotFoundException. 
		   */
		
		System.out.println("Enter Product ID:");
		int newId = sc.nextInt();
		//c.setCustomerId(newId);
		dao.deleteProduct(newId);
			
	}
	public void addToCart() throws SQLException, ProductNotFoundException, CustomerNotFoundException {
		
		/**
		   * This method is used to add cart details. 
		   * @return nothing.
		   * @throws SQLException On SQL Database error.
		   * @throws ProductNotFoundException.
		   * @throws  CustomerNotFoundException.
		   */
		
		System.out.println("Enter Customer ID");
		int id = sc.nextInt();
		c.setCustomerId(id);
		
		System.out.println("Enter Product Name you want to add to cart");
		String name1 = sc.next();
		sc.nextLine();
		p.setProductName(name1);		
		
		System.out.println("Enter Quantity");
		int newQuantity = sc.nextInt();
		cr.setQuantity(newQuantity);
		
		dao.addToCart(id,name1,newQuantity);
		
	}
	public void removeFromCart() throws SQLException, ProductNotFoundException {
		
		/**
		   * This method is used to remove cart details. 
		   * @return nothing.
		   * @throws SQLException On SQL Database error.
		   * @throws ProductNotFoundException.
		   */
		
		
		System.out.println("Enter Cart ID:");
		int newId = sc.nextInt();
	    //p.setProductId(newId);
		dao.removeFromCart(newId);
		
	}
	public void getAllFromCart() throws SQLException {
		
		/**
		   * This method is used to get all cart details. 
		   * @return nothing.
		   * @throws SQLException On SQL Database error.

		   */
		
		
		dao.getAllFromCart();
		
	}
	public void placeOrder() throws SQLException, CustomerNotFoundException, ProductNotFoundException {
		
		/**
		   * This method is used to place order. 
		   * @return nothing.
		   * @throws SQLException On SQL Database error.
		   * @throws ProductNotFoundException.
		   * @throws  CustomerNotFoundException.
		   */
		
		
		System.out.println("Placing Order...");
		
		System.out.println("Enter Customer ID:");
		int newId = sc.nextInt();
		c.setCustomerId(newId);
		
		System.out.println("Enter Product Name you want to Purchase");
		String name = sc.next();
		p.setProductName(name);
		
		System.out.println("Enter Quantity");
		int newQuantity = sc.nextInt();
		oi.setQuantity(newQuantity);
		
		System.out.println("Enter Shipping Address");
		String address = sc.next();
		o.setShippingAddress(address);
		
		LocalDate date=LocalDate.now();
		String str = date.toString();
		o.setOrderDate(str);
		
		dao.placeOrder(newId, name, newQuantity, address, str);
		
		
		
	}
	public void getOrdersByCustomer() throws SQLException, CustomerNotFoundException, OrderNotFoundException {
		/**
		   * This method is used to get order details by customer. 
		   * @return nothing.
		   * @throws SQLException On SQL Database error.
		   * @throws ProductNotFoundException.
		   * @throws  CustomerNotFoundException.
		   */
		
		
		System.out.println("Customer ID: "); 
	    int newId= sc.nextInt();
		Orderss order = dao.getOrdersByCustomer(newId);
		/*if(order==null) {
			throw new CustomerNotFoundException("Customer Not Found");
			
		}*/
	    if(order==null) {
			throw new OrderNotFoundException("Order is not placed");
		}
		
	}
	public void viewAllCustomers() throws SQLException {
		/**
		   * This method is used to get all customer details. 
		   * @return nothing.
		   * @throws SQLException On SQL Database error.

		   */
		
		dao.viewAllCustomers();
	}
	public void viewAllProducts() throws SQLException {
		/**
		   * This method is used to get all product details. 
		   * @return nothing.
		   * @throws SQLException On SQL Database error.
		 
		   */
		
		dao.viewAllProducts();
	}
	public void viewAllOrders() throws SQLException{
		/**
		   * This method is used to get all order details. 
		   * @return nothing.
		   * @throws SQLException On SQL Database error.
		
		   */
		
		dao.viewAllOrders();
	}

}
