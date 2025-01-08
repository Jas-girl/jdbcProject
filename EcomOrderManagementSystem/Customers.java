package com.EcommereceOrderManagementSystemsql;

public class Customers {
private int custId;
private String name;
private int phoneNumber;
public Customers(int custId, String name, int phoneNumber) {
	super();
	this.custId = custId;
	this.name = name;
	this.phoneNumber = phoneNumber;
}
public int getCustId() {
	return custId;
}
public void setCustId(int custId) {
	this.custId = custId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(int phoneNumber) {
	this.phoneNumber = phoneNumber;
}
@Override
public String toString() {
	return "Customers [custId=" + custId + ", name=" + name + ", phoneNumber=" + phoneNumber + "]";
}

}
