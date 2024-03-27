package com.hex.dao;

import java.sql.*;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hex.entity.*;
import com.hex.exception.CustomerNotFoundException;
import com.hex.exception.ProductNotFoundException;
import com.hex.util.*;

/**
* This is Dao class
* works for purchasing of E-commerce.
*/
public class EcomDao {
	/**
	   * This is the Dao class which has Database Connectivity to Controller.
	   */

	Connection com;
	PreparedStatement ps;
	Statement stmt;
	ResultSet rs;
	List<Customer> c = new ArrayList<Customer>();
	List<Product> p = new ArrayList<Product>();
	List<Orderss> o = new ArrayList<Orderss>();
	int r;

	public int createCustomer(Customer c) throws SQLException {

		com = EcomUtil.getDBConn();
		ps = com.prepareStatement("insert into customer( NAME, EMAIL, PASSWORD) values(?,?,?)");
		// ps.setInt(1, c.getCustomerId());
		ps.setString(1, c.getName());
		ps.setString(2, c.getEmail());
		ps.setString(3, c.getPassword());
		int no = ps.executeUpdate();
		String name = c.getName();
		// System.out.println(no + " Inserted");
		System.out.println("***" + name + " Registered Successfully***");
		return no;

	}

	public int deleteCustomer(int newId) throws SQLException, CustomerNotFoundException {

		com = EcomUtil.getDBConn();
		ps = com.prepareStatement("delete from customer where customer_Id=?");
		ps.setInt(1, newId);
		int no = ps.executeUpdate();
		if (no == 0) {
			throw new CustomerNotFoundException("Customer Not Found in ID " + newId);
		}
		// System.out.println(no + " Deleted Successfully");
		System.out.println("Customer Deleted Successfully!!");
		return no;

	}

	public int createProduct(Product p) throws SQLException {

		com = EcomUtil.getDBConn();
		ps = com.prepareStatement("insert into product( NAME, PRICE, DESCRIPTION, STOCKQUANTITY) values(?,?,?,?)");
		// ps.setInt(1, p.getProductId());
		ps.setString(1, p.getProductName());
		ps.setDouble(2, p.getPrice());
		ps.setString(3, p.getDescription());
		ps.setInt(4, p.getStockQuantity());
		String product = p.getProductName();
		int no = ps.executeUpdate();
		// System.out.println(no + " Inserted");
		System.out.println(product + " Details created Successfully!!");
		return no;

	}

	public int deleteProduct(int newId) throws SQLException, ProductNotFoundException {

		com = EcomUtil.getDBConn();
		ps = com.prepareStatement("delete from Product where product_Id=?");
		ps.setInt(1, newId);
		int no = ps.executeUpdate();
		if (no == 0) {
			throw new ProductNotFoundException("Product Not Found");
		}
		// System.out.println(no + " Deleted Successfully");
		System.out.println("Product Deleted Successfully!!");
		return no;

	}

	public int addToCart(int id, String name1, int newQ)
			throws SQLException, ProductNotFoundException, CustomerNotFoundException {

		int id1;
		com = EcomUtil.getDBConn();
		ps = com.prepareStatement("select customer_id from customer where customer_id=?");
		ps.setInt(1, id);
		rs = ps.executeQuery();
		// System.out.println("selected successfully");
		rs.next();
		// System.out.println(rs.getRow());
		if (rs.getRow() == 0) {
			throw new CustomerNotFoundException("Customer Not Found at ID " + id);
		}

		ps = com.prepareStatement("select product_id from product where name=?");
		ps.setString(1, name1);
		rs = ps.executeQuery();
		// System.out.println("selected successfully");
		// System.out.println(rs.next());
		// System.out.println(rs.getRow());
		rs.next();
		if (rs.getRow() == 0) {
			throw new ProductNotFoundException("Product Not Found ");
		} else {
			id1 = rs.getInt(1);
			ps = com.prepareStatement("insert into cart(CUSTOMER_ID, PRODUCT_ID, QUANTITY) values(?,?,?)");
			ps.setInt(3, newQ);
			ps.setInt(2, id1);
			ps.setInt(1, id);
			int no = ps.executeUpdate();
			System.out.println("Added to Cart Successfully!!");
			return no;
		}
	}

	public int removeFromCart(int newId) throws SQLException, ProductNotFoundException {

		com = EcomUtil.getDBConn();
		ps = com.prepareStatement("delete from cart where cart_id=?");
		ps.setInt(1, newId);
		int no = ps.executeUpdate();
		if (no == 0) {
			throw new ProductNotFoundException("Product Not Found");
		}
		System.out.println("Item Deleted Successfully!!");
		return no;

	}

	public int getAllFromCart() throws SQLException {

		com = EcomUtil.getDBConn();
		stmt = com.createStatement();
		rs = stmt.executeQuery(
				"select cr.name,c.cart_id,c.product_id,p.name,c.quantity from cart c inner join product p on c.product_id=p.product_id inner join customer cr on cr.customer_id=c.CUSTOMER_ID;");

		while (rs.next()) {
			System.out.println("\nCustomer Name: " + rs.getString(1));
			System.out.println("Cart ID: " + rs.getInt(2));
			System.out.println("Product ID: " + rs.getInt(3));
			System.out.println("Product Name: " + rs.getString(4));
			System.out.println("Quantity: " + rs.getInt(5));
			r = 1;
		}
		return r;
	}

	public int placeOrder(int newId, String name, int newQ, String address, String date)
			throws SQLException, CustomerNotFoundException, ProductNotFoundException {

		com = EcomUtil.getDBConn();
		int id;
		ps = com.prepareStatement("select customer_id from customer where customer_id=?");
		ps.setInt(1, newId);
		rs = ps.executeQuery();
		// System.out.println("selected successfully");
		rs.next();
		// System.out.println(rs.getRow());
		if (rs.getRow() == 0) {
			throw new CustomerNotFoundException(
					"Customer Not Found at ID " + newId + "\nPlease Register by Choosing Option[1]");
		} else {
			ps = com.prepareStatement("select product_id,price from product where name=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			// System.out.println("selected successfully");
			rs.next();
			// System.out.println(rs.getRow());
			if (rs.getRow() == 0) {
				throw new ProductNotFoundException("Product not found ");
			} else {
				id = rs.getInt(1);
				double price = rs.getDouble(2);
				// System.out.println(id + " " + price);
				double totalPrice = newQ * price;
				// System.out.println(totalPrice);
				ps = com.prepareStatement(
						"INSERT INTO ORDERSS(CUSTOMER_ID,ORDER_DATE,TOTAL_PRICE,SHIPPING_ADDRESS) VALUES(?,?,?,?)");
				ps.setString(2, date);
				ps.setInt(1, newId);
				ps.setDouble(3, totalPrice);
				ps.setString(4, address);
				int no = ps.executeUpdate();
				System.out.println("***Order Details***");
				System.out.println("Product Name:" + name + "\nQuantity:" + newQ + "\nTotal Price: Rs." + totalPrice
						+ "\nOrder Date:" + date);
				System.out.println("Order Placed Successfully!!");
				ps = com.prepareStatement("select order_id from orderss where customer_id=? and total_price=?");
				ps.setInt(1, newId);
				ps.setDouble(2, totalPrice);
				rs = ps.executeQuery();
				rs.next();
				int orderId = rs.getInt(1);
				// System.out.println("=====" + orderId);
				// System.out.println("=====" + id);
				// System.out.println("=====" + newQ);
				ps = com.prepareStatement("insert into order_items(ORDER_ID, PRODUCT_ID, QUANTITY) values (?,?,?)");
				ps.setInt(1, orderId);
				ps.setInt(2, id);
				ps.setInt(3, newQ);
				int no1 = ps.executeUpdate();
				System.out.println("Inserted in Order Items!!");
				ps = com.prepareStatement("update product set stockquantity=stockquantity-? where product_id=?");
				ps.setInt(2, id);
				ps.setInt(1, newQ);
				ps.executeUpdate();
				System.out.println("updated");
				/*
				 * if (no == 0) { System.out.println("Customer not found! Please Register!!");
				 * throw new CustomerNotFoundException("Customer Not Found"); }
				 * System.out.println(no + " Order Placed Successfully");
				 */
				return no1;
			}
		}
	}

	public Orderss getOrdersByCustomer(int id) throws SQLException, CustomerNotFoundException {

		com = EcomUtil.getDBConn();
		Orderss o2 = new Orderss();
		Customer c2 = new Customer();
		ps = com.prepareStatement("select customer_id from customer where customer_id=?");
		ps.setInt(1, id);
		rs = ps.executeQuery();
		// System.out.println("selected successfully");
		rs.next();
		// System.out.println(rs.getRow());
		if (rs.getRow() == 0) {
			throw new CustomerNotFoundException(
					"Customer Not Found at ID " + id + "\nPlease Register by Choosing Option[1]");
		} else {
			ps = com.prepareStatement(
					"select o.order_id,o.customer_id,c.name,o.order_date,o.total_price from orderss o inner join customer c on  c.customer_id=o.customer_id where o.customer_id=?;");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			// System.out.println(rs);
			rs.next();
			if (rs.getRow() > 0) {
					int a = rs.getInt(1);
					int b = rs.getInt(2);
					String c = rs.getString(3);
					String d = rs.getString(4);
					double e = rs.getDouble(5);

					ps = com.prepareStatement(
							"select name from product where PRODUCT_ID in (select PRODUCT_ID from order_items where ORDER_ID in(select ORDER_ID from orderss where CUSTOMER_ID=?))");
					ps.setInt(1, id);
					rs = ps.executeQuery();
					rs.next();
					String str = rs.getString(1);
					System.out.println("OrderId=" + a + "\nCustomerId=" + b + "\nName=" + c + "\nProduct Name=" + str
							+ "\nOrder Date=" + d + "\nPrice=" + e);
					System.out.println(" seleted Successfully");
					o2.setOrderID(a);
					o2.setOrderDate(d);
					o2.setTotalAmount(e);
					c2.setCustomerId(b);
					c2.setName(c);
				return o2;
			} else {
				return null;
			}
		}
	}

	public int viewAllCustomers() throws SQLException {

		com = EcomUtil.getDBConn();
		stmt = com.createStatement();
		rs = stmt.executeQuery("select * from customer");
		while (rs.next()) {
			c.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			r = 1;
		}

		Iterator<Customer> ip = c.iterator();
		while (ip.hasNext()) {
			System.out.println(ip.next());
		}
		return r;
	}

	public int viewAllProducts() throws SQLException {

		com = EcomUtil.getDBConn();
		stmt = com.createStatement();
		rs = stmt.executeQuery("select * from Product");
		while (rs.next()) {
			p.add(new Product(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getInt(5)));
			r = 1;
		}

		Iterator<Product> ip = p.iterator();
		while (ip.hasNext()) {
			System.out.println(ip.next());
		}
		/*
		 * System.out.println("Sorting by ProductID"); Collections.sort(p);
		 * System.out.println(p);
		 */
		return r;

		/*
		 * Collections.sort(p, Product.nameCompartor);
		 * System.out.println("Sorting by Name"); System.out.println(p);
		 */
	}

	public int viewAllOrders() throws SQLException {

		com = EcomUtil.getDBConn();
		stmt = com.createStatement();
		rs = stmt.executeQuery(
				"select o.*,oi.quantity from orderss o inner join order_items oi on oi.ORDER_ID=o.ORDER_ID inner join product p on p.PRODUCT_ID=oi.PRODUCT_ID");
		/*
		 * while(rs.next()) { o.add(new
		 * Orderss(rs.getInt(1),rs.getInt(2),rs.getString(3),
		 * rs.getDouble(4),rs.getString(5),rs.getInt(6))); }
		 * 
		 * Iterator<Orderss> ip = o.iterator(); while (ip.hasNext()) {
		 * System.out.println(ip.next()); }
		 */
		while (rs.next()) {
			System.out.println("\nOrder Id=" + rs.getInt(1) + "\nCustomer Id=" + rs.getInt(2) + "\nOrder Date="
					+ rs.getString(3) + "\nPrice=" + rs.getDouble(4) + "\nShipping Address=" + rs.getString(5)
					+ "\nQuantity=" + rs.getInt(6));

			r = 1;
		}
		return r;
	}
}
