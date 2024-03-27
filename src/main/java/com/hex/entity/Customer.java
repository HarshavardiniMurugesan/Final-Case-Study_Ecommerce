package com.hex.entity;

/**
 * This is the Customer class.
 */

public class Customer {
    private int customerId;
    private String name;
    private String email;
    private String password;
    
    public Customer() {
    	//System.out.println("Customer Constr..");
    }

    public Customer(int customerId, String name, String email, String password) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.password = password;
     
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	@Override
	public String toString() {
		return "\nCustomerId=" + customerId + " \nName=" + name +" \nEmail=" + email + " \nPassword=" + password;
	}
    
    
}
