package com.bms.controller;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Scanner;

import com.bms.module.Costumer;
import com.bms.module.Product;
import com.bms.service.BillService;

public class BillController {
BillService service=new BillService();
Scanner sc=new Scanner(System.in);
public void showMenu() throws SQLException {
	while(true) {
		System.out.println("1->Create database and table");
		System.out.println("2->Insert the product");
		System.out.println("3->Update the product");
		System.out.println("4->List of products");
		System.out.println("5->Insert the costumer");
		System.out.println("6->Place the order");
		System.out.println("7->Search Product by Name");
		System.out.println("8->Search Product by category");
		System.out.println("9->Sales report by product wise");
		System.out.println("10->update of costumer");
		System.out.println("11->View all Costumer");
		System.out.println("12->Viewbill" );
		System.out.println("13->exit");
		System.out.println("Enter the choice");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:createTableAndDatabase();break;
		case 2:insertProduct(sc);break;
		case 3:updateProduct();break;
		case 4:viewProducts();break;
		case 5:insertCostumer(sc);break;
		case 6:generateOrder(sc);break;
		case 7:SearchName(sc);break;
		case 8:SearchProductByCategory();break;
		case 9:salesReportByProduct();break;
		case 10:updateCostumer();break;
		case 11:viewCostumer();break;
		case 12:viewBill();break;
		default:System.out.println("Invalid choice");
		}
	}
}
private void viewBill() throws SQLException {
	System.out.println("Enter the order id");
	int orderid=sc.nextInt();
	service.bill(orderid);
	
	
}
private void viewCostumer() throws SQLException {
	for (Costumer element : service.viewCostumer()) {
		System.out.println(element);
	}
	
}
private void updateCostumer() throws SQLException {
	System.out.println("Enter the costumer id");
	int cid=sc.nextInt();
	System.out.println("Enter the phone number");
	int phone=sc.nextInt();
	System.out.println(service.updateCostumer(cid, phone));
	
}
private void salesReportByProduct() throws SQLException {
	System.out.println("Enter the product id");
	int proid=sc.nextInt();
	service.salesReportByProduct(proid);
	
}
private void SearchProductByCategory() throws SQLException {
	System.out.println("Enter the category to find the product");
	String category=sc.next();
	for (Product element : service.searchProductByCategory(category)) {
		System.out.println(element);
	}
	
}
private void SearchName(Scanner sc) throws SQLException {
	System.out.println("Enter the product name");
	String proName=sc.next();
	System.out.println(service.searchProductByName(proName));
	
	
}
private void generateOrder(Scanner sc) throws SQLException {
	System.out.println("Enter the costumer id");
	int cId=sc.nextInt();
	System.out.println("Enter the product id");
	int pid=sc.nextInt();
	System.out.println(service.generateOrder(cId, pid));
	
}
private void insertCostumer(Scanner sc) throws SQLException {
	System.out.println("Enter the costumer id");
	int cid=sc.nextInt();
	System.out.println("Enter the costumer name");
	String name=sc.next();
	System.out.println("Enter the phone Number");
	int number=sc.nextInt();
	Costumer c=new Costumer(cid, name, number);
	System.out.println(service.insertCostumer(c));
	
	
}
private void viewProducts() throws SQLException {
	List<Product> product=service.viewProducts();
	for (Product product2 : product) {
		System.out.println(product2);
	}
	
}
private void updateProduct() throws SQLException {
	System.out.println("Enter the product Id");
	int id=sc.nextInt();
	System.out.println("Enter the price");
	float price=sc.nextFloat();
	System.out.println("Enter the stock");
	int stock=sc.nextInt();
	System.out.println(service.updateProduct(price, stock, id));
	
	
}
private void insertProduct(Scanner sc2) throws SQLException {
	System.out.println("Enter the product id");
	int id=sc.nextInt();
	System.out.println("Enter the product name");
	String name=sc.next();
	System.out.println("Enter the category");
	String category=sc.next();
	System.out.println("Enter the price");
	float price=sc.nextFloat();
	System.out.println("Enter the stock");
	int stock=sc.nextInt();
	try {
		System.out.println(service.insertProduct(new Product(id, name, category, price, stock)));
	}
	catch(SQLIntegrityConstraintViolationException e) {
		System.out.println("Duplicate product id ");
	}
	
	
	
}
private void createTableAndDatabase() throws SQLException {
	System.out.println(service.createTableAndDatabase());
	
}

}
