package com.hex.test;

import java.sql.SQLException;
import java.util.Scanner;
import com.hex.entity.*;
import com.hex.exception.CustomerNotFoundException;

import org.junit.*;
import org.junit.runners.MethodSorters;

import com.hex.dao.*;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestCustomer {
	
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
		c=new Customer();
	}
	
	@Test
	public void testAddCustomer1() throws SQLException {
	

	System.out.println("Enter Name");
	String newName = sc.next();
	c.setName(newName);

	System.out.println("Enter Email");
	String newEmail = sc.next();
	c.setEmail(newEmail);

	System.out.println("Enter Password");
	String newPassword = sc.next();
	c.setPassword(newPassword);
	assertEquals(1,dao.createCustomer(c));
	
	}
	@Test
	public void testDeleteCustomer2() throws SQLException, CustomerNotFoundException {
		
		System.out.println("Enter Customer ID:");
		int newId2 = sc.nextInt();
		c.setCustomerId(newId2);
		assertEquals(1,dao.deleteCustomer(newId2));
	}
	
	@Test
	public void testViewallCustomer3() throws SQLException, CustomerNotFoundException {
		
		assertEquals(1,dao.viewAllCustomers());
	
	}
	@After
	public void tearDown() {
		System.out.println("out");
		dao=null;
		c=null;
	}
	/*@AfterClass
	public static void tearDownClass() {
		System.out.println("Out Class...");
	}*/

}
