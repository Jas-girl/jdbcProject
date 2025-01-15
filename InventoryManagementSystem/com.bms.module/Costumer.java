package com.bms.module;

public class Costumer {
private int cid;
private String name;
private int phone;
public Costumer(int cid, String name, int phone) {
	super();
	this.cid = cid;
	this.name = name;
	this.phone = phone;
}
public int getCid() {
	return cid;
}
public void setCid(int cid) {
	this.cid = cid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getPhone() {
	return phone;
}
public void setPhone(int phone) {
	this.phone = phone;
}
@Override
public String toString() {
	return "Costumer [cid=" + cid + ", name=" + name + ", phone=" + phone + "]";
}

}
