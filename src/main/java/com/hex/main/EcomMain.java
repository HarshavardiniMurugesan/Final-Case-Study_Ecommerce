package com.hex.main;

import java.sql.SQLException;

import java.util.Scanner;

import com.hex.dao.EcomController;
import com.hex.dao.OrderProcessorRepositoryImpl;
import com.hex.exception.CustomerNotFoundException;
import com.hex.exception.OrderNotFoundException;
import com.hex.exception.ProductNotFoundException;

/**
* <h1>Main Class</h1>
* The Main class implements an application that
* works for purchasing of E-commerce.
*
* @author  Harshavardini
* @version 1.0
* @since   2024-03-25 
* 
*/

public class EcomMain {
	
	 /**
	   * This is the Main class which has main method.
	   * @param args Unused.
	   * @exception SQLException On SQL Database error.
	   * @throws CustomerNotFoundException On customer is not found.
	   * @throws ProductNotFoundException On Product is Unavailable.
	   * @throws OrderNotFoundException On order is not found.
	   */
	
	
	
	
	public static void main(String[] args) throws SQLException, CustomerNotFoundException, OrderNotFoundException, ProductNotFoundException {
		/**
		   * This is the main mathod which is used for execution.
		   * @exception SQLException On SQL Database error.
		   * @throws CustomerNotFoundException On customer is not found.
	   * @throws ProductNotFoundException On Product is Unavailable.
	   * @throws OrderNotFoundException On order is not found.
	   */
		OrderProcessorRepositoryImpl ec = new EcomController();

		Scanner sc = new Scanner(System.in);
		String ch = null;
		do {
			System.out.println("***ECOMMERCE***");
			System.out.println("1. Register Customer");
			System.out.println("2. Delete Customer");
			System.out.println("3. Create Product");
			System.out.println("4. Delete Product");
			System.out.println("5. Add to cart");
			System.out.println("6. Remove from cart");
			System.out.println("7. View cart");
			System.out.println("8. Place order");
			System.out.println("9. View Customer Order");
			System.out.println("10. View Customers");
			System.out.println("11. View Products");
			System.out.println("12. View Orders");
			System.out.println("***Enter your Choice***");

			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				ec.createCustomer();
				break;
			}
			case 2: {
				ec.deleteCustomer();
				break;
			}
			case 3: {
				ec.createProduct();
				break;
			}
			case 4: {
				ec.deleteProduct() ;

				break;
			}
			case 5: {
				
				ec.addToCart();
				break;
			}
			case 6: {

				ec.removeFromCart();
				break;
			}
			case 7: {

				ec.getAllFromCart();
				break;
			}
			case 8: {

				ec.placeOrder();
				break;
			}
			case 9: {

				ec.getOrdersByCustomer();
				break;
			}
			case 10: {

				ec.viewAllCustomers();
				break;
			}
			case 11: {

				ec.viewAllProducts();
				break;
			}
			case 12: {

				ec.viewAllOrders();
				break;
			}
			default: {
				System.out.println("Enter the right choice. ");
			}
			}
			
			System.out.println("Do you want to continue? Type: Y or y");
			ch = sc.next();
		} while (ch.equals("Y") || ch.equals("y"));
		System.out.println("Thank you for Purchasing !\nVisit Again !!");
		System.exit(0);
	}
}


