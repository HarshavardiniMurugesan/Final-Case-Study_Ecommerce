package com.hex.entity;

import java.util.Comparator;

/**
 * This is the Product class.
 */

public class Product implements Comparable<Product>{
    private int productId;
    private String productName;
    private double price;
    private String description;
    private int stockQuantity;
    
    public Product() {
    	//System.out.println("Product Constr..");
    }

    public Product(int productId, String productName, double price, String description,int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.stockQuantity= stockQuantity;
        
    }
    
	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public int getProductId() {
        return productId;
    }

    public void setProductId(int productID) {
        this.productId = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            System.out.println("Price cannot be negative.");
        }
    }

	@Override
	public String toString() {
		return "\nProductId=" + productId + " \nProductName=" + productName + " \nPrice=" + price +" \nDescription=" + description
				+" \nStock Quantity=" + stockQuantity+"\n";
	}
	
	public int compareTo(Product e) {

		return e.productId - this.productId;
	}
	public static Comparator nameCompartor = new Comparator<Product>() {

		public int compare(Product e1, Product e2) {

			return e1.getProductName().compareTo(e2.getProductName());
		}

	};


   
    
}
