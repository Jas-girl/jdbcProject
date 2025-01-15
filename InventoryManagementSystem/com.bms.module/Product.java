package com.bms.module;

public class Product {
private int productId;
private String name;
private String category;
private float price;
private int stock;
public Product() {
	// TODO Auto-generated constructor stub
}
public Product(int productId, String name, String category, float price, int stock) {
	super();
	this.productId = productId;
	this.name = name;
	this.category = category;
	this.price = price;
	this.stock = stock;
}
public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public float getPrice() {
	return price;
}
public void setPrice(float price) {
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
	return "Product [productId=" + productId + ", name=" + name + ", category=" + category + ", price=" + price
			+ ", stock=" + stock + "]";
}

}
