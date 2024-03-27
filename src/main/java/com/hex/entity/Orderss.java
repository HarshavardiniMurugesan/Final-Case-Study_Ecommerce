package com.hex.entity;

/**
 * This is the Orderss class.
 */
public class Orderss extends Customer{
	private int orderId;
	private String orderDate;
	private double totalAmount;
	private Customer customer;// composition
	private String shippingAddress;

	public Orderss() {
		//System.out.println("Order Constr..");
	}

	public Orderss(int orderId, String orderDate, double totalAmount, Customer customer,String shippingAddress) {
         this.orderId = orderId;
         this.orderDate = orderDate;
         this.totalAmount = totalAmount;
         this.customer = customer;
         this.shippingAddress= shippingAddress;
 }


	public Orderss(int int1, int int2, String string, double double1, String string2, int int3) {
		// TODO Auto-generated constructor stub
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderID(int orderId) {
		this.orderId = orderId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	@Override
	public String toString() {
		return "\nOrderId=" + orderId + " \nOrderDate=" + orderDate + " \nTotalAmount=" + totalAmount
				+ " \nCustomer=" + customer;
	}

}

