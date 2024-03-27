package com.hex.entity;


/**
 * This is the Order Items class.
 */
public class OrderItems extends Orderss{

	private int orderItemId;
	private Orderss order;// composition
	private Product product;//composition
	private int quantity;

	public OrderItems() {
		//System.out.println("Order Items Constr..");
	}

	public OrderItems(int orderItemId, Orderss order, Product product, int quantity) {
         this.orderItemId = orderItemId;
         this.order = order;
         this.quantity = quantity;
         this.product = product;

}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Orderss getOrder() {
		return order;
	}

	public void setOrder(Orderss order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	@Override
	public String toString() {
		return "OrderItems [orderItemId=" + orderItemId + ", order=" + order + ", product=" + product + ", quantity="
				+ quantity + "]";
	}}
