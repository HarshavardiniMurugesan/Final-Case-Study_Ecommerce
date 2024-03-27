package com.hex.entity;


/**
 * This is the Cart class.
 */
public class Cart extends Product{
	
	private int cartId;
	private Customer customer;// composition
	private Product product;//composition
	private int quantity;

	public Cart() {
		//System.out.println("Cart Constr..");
	}

	public Cart(int cartId, Customer customer, Product product, int quantity) {
         this.cartId = cartId;
         this.product = product;
         this.quantity = quantity;
         this.customer = customer;

 }

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	}
