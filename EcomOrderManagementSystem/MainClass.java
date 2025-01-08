package com.EcommereceOrderManagementSystemsql;
import java.sql.SQLException;
import java.util.Scanner;

public class MainClass {
public static void main(String[] args) throws SQLException {
	ControllerClass controller=new ControllerClass();
	Scanner sc=new Scanner(System.in);
	while(true) {
		System.out.println("1->Create the table of products");
		System.out.println("2->Create the table of customers");
		System.out.println("3->Insert the values in the products");
		System.out.println("4->Insert the values in the customer");
		System.out.println("5->Place order for customer");
		System.out.println("6->View all the orders");
		System.out.println("7->Update the status");
		System.out.println("8->Sales Info");
		System.out.println("9->View products");
		System.out.println("10->View Customers");
		System.out.println("11->Update the stock of the product");
		System.out.println("12->Exit....");
		System.out.println("Enter the choice");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:controller.createTableProducts();break;
		case 2:controller.createTableCustomers();break;
		case 3:
			System.out.println("Enter the product Id");
			int proId=sc.nextInt();
			System.out.println("Enter the product Name");
			String proName=sc.next();
			System.out.println("Enter the price");
			int price=sc.nextInt();
			System.out.println("Enter the stock");
			int stock=sc.nextInt();
			controller.insertProducts(new Products(proId, proName, price, stock));
			break;
		case 4:
			System.out.println("Enter the customer id");
			int cusTd=sc.nextInt();
			System.out.println("Enter the name");
			String name=sc.next();
			System.out.println("Enter the phone Number");
			int no=sc.nextInt();
			controller.insertCustomer(new Customers(cusTd, name, no));
			break;
		case 5:
			System.out.println("Enter the product id");
			int proId1=sc.nextInt();
			System.out.println("Enter the quantity needed by user");
			int qty=sc.nextInt();
			System.out.println("Enter the customer id");
			int cid=sc.nextInt();
			controller.placeOrder(proId1, qty, cid);
			break;
		case 6:controller.viewAll();break;
		case 7:System.out.println("Enter the order Id");
		int orderId=sc.nextInt();
		System.out.println("Enter the status (e.g., \"Pending,\" \"Shipped,\" \"Delivered\")");
		String status=sc.next();
		controller.updateOrder(orderId, status);
		break;
		case 8:controller.salesInfo();break;
		case 9:controller.viewProducts();break;
		case 10:controller.viewCustomer();break;
		case 11:
			System.out.println("Enter the quantity to update");
			int qty1=sc.nextInt();
			System.out.println("Enter the product id");
			int id1=sc.nextInt();
			controller.updateProduct(qty1, id1);
			break;
		case 12:System.out.println("Exit....");
		}
		
	}
	
	
	//controller.insertProducts(new Products(1, "laptop", 45000, 5));
	//controller.insertProducts(new Products(2, "mobile", 35000, 7));
	//controller.insertCustomer(new Customers(1, "rahul", 23456));
	
//	OrderManagement order=new OrderManagement();
//	order.createTable();
	
}
}
