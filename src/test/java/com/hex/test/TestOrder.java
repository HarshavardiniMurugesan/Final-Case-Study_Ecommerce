package com.hex.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.hex.dao.EcomDao;
import com.hex.entity.*;
import com.hex.exception.CustomerNotFoundException;
import com.hex.exception.ProductNotFoundException;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOrder {
	
	Customer c;
	Orderss o;
	Product p;
	OrderItems oi;
	EcomDao dao;
	Scanner sc=new Scanner(System.in);
	
	/*@BeforeClass
	public static void setUpClass() {
		System.out.println("In Class...");
	}*/
	
	@Before
	public void setUp() {
		System.out.println("In");
		dao=new EcomDao();
		c=new Customer();
		o=new Orderss();
		p=new Product();
		oi=new OrderItems();
	}
	
	@Test
	public void testAddOrder1() throws SQLException, CustomerNotFoundException, ProductNotFoundException {
	
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
		
	   assertEquals(1,dao.placeOrder(newId, name, newQuantity, address, str));
	
	}
	
	@Test
	public void testGetOrdersByCus2() throws SQLException, CustomerNotFoundException {
		
		System.out.println("Customer ID: "); 
	    int newId= sc.nextInt();
		assertNotNull("Order is not placed",dao.getOrdersByCustomer(newId));
	}
	
	@Test
	public void testViewallOrder3() throws SQLException, CustomerNotFoundException {
		
		assertEquals(1,dao.viewAllOrders());
	
	}
	@After
	public void tearDown() {
		System.out.println("out");
		dao=null;
		c=null;
		p=null;
		o=null;
		oi=null;
		
	}
	/*@AfterClass
	public static void tearDownClass() {
		System.out.println("Out Class...");
	}*/


}
