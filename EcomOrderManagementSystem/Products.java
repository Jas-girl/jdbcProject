package com.EcommereceOrderManagementSystemsql;

public class Products {
private int productId;
private String productName;
private int price;
private int stock;

public Products() {
	super();
	// TODO Auto-generated constructor stub
}
public Products(int productId, String productName, int price, int stock) {
	super();
	this.productId = productId;
	this.productName = productName;
	this.price = price;
	this.stock = stock;
}
public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getStock() {
	return stock;
}
public void setStock(int stock) {
	this.stock = stock;
}
@Override
public String toString() {
	return "Products [productId=" + productId + ", productName=" + productName + ", price=" + price + ", stock=" + stock
			+ "]";
}

	
}
