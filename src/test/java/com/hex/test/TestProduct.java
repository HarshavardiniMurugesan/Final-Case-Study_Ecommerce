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
public class TestProduct {
	
	Product p;
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
		p=new Product();
		
	}
	
	@Test
	public void testAddProduct1() throws SQLException {
	
		System.out.println("Enter Product Name");
		String newName = sc.next();
		p.setProductName(newName);
		
		System.out.println("Enter Price:");
		int newPrice = sc.nextInt();
		p.setPrice(newPrice);

		System.out.println("Enter Description");
		String newDes = sc.next();
		p.setDescription(newDes);

		System.out.println("Enter Stock Quantity");
		int newQuantity = sc.nextInt();
		p.setStockQuantity(newQuantity);
		
	    assertEquals(1,dao.createProduct(p));
	
	}
	@Test
	public void testDeleteProduct2() throws SQLException, ProductNotFoundException {
		
		System.out.println("Enter Product ID:");
		int newId = sc.nextInt();
		assertEquals(1,dao.deleteProduct(newId));
	}
	
	@Test
	public void testViewallProduct3() throws SQLException, CustomerNotFoundException {
		
		assertEquals(1,dao.viewAllProducts());
	
	}
	@After
	public void tearDown() {
		System.out.println("out");
		dao=null;
	}
	/*@AfterClass
	public static void tearDownClass() {
		System.out.println("Out Class...");
	}*/

	

}
