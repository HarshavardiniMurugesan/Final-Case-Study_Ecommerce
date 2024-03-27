package com.hex.test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
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
public class TestCart {
	
	Cart cr;
	Product p;
	Customer c;
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
		cr=new Cart();
		p=new Product();
		c=new Customer();
		
	}
	
	@Test
	public void testAddCart1() throws SQLException, ProductNotFoundException, CustomerNotFoundException {
	
		System.out.println("Enter Customer ID");
		int id = sc.nextInt();
		c.setCustomerId(id);
		
		System.out.println("Enter Product Name you want to add to cart");
		String name1 = sc.next();
		p.setProductName(name1);
		
		System.out.println("Enter Quantity");
		int newQuantity = sc.nextInt();
		cr.setQuantity(newQuantity);
		
	    assertEquals(1,dao.addToCart(id,name1,newQuantity));
	
	}
	@Test
	public void testDeleteCart2() throws SQLException, ProductNotFoundException {
		
		System.out.println("Enter Cart ID:");
		int newId = sc.nextInt();
		assertEquals(1,dao.removeFromCart(newId));
	}
	
	@Test
	public void testViewallCart3() throws SQLException, CustomerNotFoundException {
		
		assertEquals(1,dao.getAllFromCart());
	
	}
	@After
	public void tearDown() {
		System.out.println("out");
		dao=null;
		cr=null;
		p=null;
		c=null;
	}
	/*@AfterClass
	public static void tearDownClass() {
		System.out.println("Out Class...");
	}*/

	

}
