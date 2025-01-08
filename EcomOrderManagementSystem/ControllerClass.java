package com.EcommereceOrderManagementSystemsql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ControllerClass {
	Scanner sc = new Scanner(System.in);
	Statement stm = null;
	Products product = new Products();

	public ControllerClass() throws SQLException {
		String database = "create database if not exists ecommDb";
		stm = ConnectionClass.getConnection().createStatement();
		stm.execute(database);
		System.out.println("Database is executed");
		String useDb = "use ecommDb";
		stm.execute(useDb);
		System.out.println("Database is used");
	}

	void createTableProducts() throws SQLException {
		String query = "create table if not exists products(productId int primary key,productName varchar(30),price int,stock int)";
		stm.execute(query);
		System.out.println("Product product table is created");
	}

	void createTableCustomers() throws SQLException {
		String query = "create table if not exists customers(custId int primary key,username varchar(30),phoneNumber int) ";
		stm.execute(query);
		System.out.println("Product customer table is created");
	}

	void insertProducts(Products product) throws SQLException {
		String useDb = "use ecommDb";
		String query = "insert into products(productId,productName,price,stock)values(?,?,?,?)";
		PreparedStatement pstm = ConnectionClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		pstm.setInt(1, product.getProductId());
		pstm.setString(2, product.getProductName());
		pstm.setInt(3, product.getPrice());
		pstm.setInt(4, product.getStock());
		pstm.executeUpdate();
		System.out.println("product is added successfully");
		pstm.close();

	}

	void insertCustomer(Customers cust) throws SQLException {
		String useDb = "use ecommDb";
		String query = "insert into customers(custId,username,phoneNumber)values(?,?,?)";
		PreparedStatement pstm = ConnectionClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		pstm.setInt(1, cust.getCustId());
		pstm.setString(2, cust.getName());
		pstm.setInt(3, cust.getPhoneNumber());
		pstm.execute();
		System.out.println("Customer is added");
		pstm.close();

	}

	void createOrderTable() throws SQLException {
		String useDb = "use ecommDb";
		String query = "create table if not exists orders(id int primary key,productId int, customerId int,status varchar(20),orderdate date,quantity int,"
				+ "foreign key (productId) references products(productid),foreign key (customerId) references customers(custId))";
		stm = ConnectionClass.getConnection().createStatement();
		stm.execute(useDb);
		stm.execute(query);
		System.out.println("Order table is made");
	}

	void placeOrder(int prodid, int qty, int custId) throws SQLException {
		createOrderTable();
		int stockQty = 0;
		int user = 0;
		String useDb = "use ecommDb";
		String query = "select * from products where productId=?";
		String custQuery = "select * from customers where custId=?";

		PreparedStatement pstm = ConnectionClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		pstm.setInt(1, prodid);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			stockQty = rs.getInt("stock");
		} else {
			System.out.println("Product id is not available");
			return;
		}
		if (stockQty < qty) {
			System.out.println("Stock is not available");
			return;
		} else {
			System.out.println("Stock is available");

		}
		pstm.close();
		PreparedStatement pstm1 = ConnectionClass.getConnection().prepareStatement(custQuery);
		pstm1.execute(useDb);
		pstm1.setInt(1, custId);
		ResultSet rs1 = pstm1.executeQuery();
		if (rs1.next()) {
			user = custId;
			System.out.println("Customer id is available in customer table");
		} else {
			System.out.println("Customer id is not available in customer table");
			return;
		}
		String order = "insert into orders(id,productId,customerId,status,orderdate,quantity)values(?,?,?,?,?,?)";
		PreparedStatement pstm2 = ConnectionClass.getConnection().prepareStatement(order);
		pstm2.execute(useDb);
		System.out.println("Enter the orderId");
		int orderId = sc.nextInt();
		pstm2.setInt(1, orderId);
		pstm2.setInt(2, prodid);
		pstm2.setInt(3, user);
		pstm2.setString(4, "pending");
		pstm2.setDate(5, new java.sql.Date(System.currentTimeMillis()));
		pstm2.setInt(6, qty);
		pstm2.execute();
		System.out.println("Order is added and is processed.....");

		String updateStock = "update products set stock=? where productId=?";
		PreparedStatement pstm3 = ConnectionClass.getConnection().prepareStatement(updateStock);
		pstm3.execute(useDb);
		pstm3.setInt(1, stockQty - qty);
		pstm3.setInt(2, prodid);
		int rowAffected = pstm3.executeUpdate();
		if (rowAffected > 0) {
			System.out.println("Updation successfully.....");
		} else {
			System.out.println("Updation is not done....");
		}

	}
	void viewAll() throws SQLException {
		String useDb = "use ecommDb";
		String query="select * from orders";
		PreparedStatement pstm=ConnectionClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		ResultSet rs=pstm.executeQuery();
		while(rs.next()) {
			System.out.println("***********Orders***************");
			System.out.println("\tOrder id:"+rs.getInt("id"));
			System.out.println("Product Id:"+rs.getInt("productId"));
			System.out.println("Customer Id:"+rs.getInt("customerId"));
			System.out.println("Status:"+rs.getString("status"));
			System.out.println("OrderDate:"+rs.getDate("orderdate"));
			System.out.println("Quatity:"+rs.getInt("quantity"));
		}
		pstm.execute();
		
	}
	void updateOrder(int orderid,String status) throws SQLException {
		String useDb = "use ecommDb";
		String query="update orders set status=? where id=?";
		PreparedStatement pstm=ConnectionClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		pstm.setString(1, status);
		pstm.setInt(2, orderid);
		int rowsAffected=pstm.executeUpdate();
		if(rowsAffected>0) {
			System.out.println("updation successfully......");
		}
		else {
			System.out.println("updation is not successfully......");
		}
		
		
	}
	void salesInfo() throws SQLException {
		String useDb = "use ecommDb";
	    String query = "SELECT products.productName AS ProductName, "
	                 + "products.price AS Price, "
	                 + "orders.quantity AS Qty, "
	                 + "SUM(products.price * orders.quantity) AS TotalPrice "
	                 + "FROM orders "
	                 + "INNER JOIN products ON orders.productId = products.productId "
	                 + "GROUP BY products.productId, products.productName, products.price, orders.quantity;";
	    
	    
	     PreparedStatement pstm=ConnectionClass.getConnection().prepareStatement(query);
	    		
	         pstm.execute(useDb);
	    	ResultSet rs = pstm.executeQuery();
	    
	    {

	        System.out.println("ProductName \t Price \t Qty \t TotalPrice");
	        
	        while (rs.next()) {
	            System.out.println(
	                rs.getString("ProductName") + "\t\t" +
	                rs.getInt("Price") + "\t\t" +
	                rs.getInt("Qty") + "\t\t" +
	                rs.getInt("TotalPrice")
	            );
	        }
	    }
	}

	void viewProducts() throws SQLException {
		String useDb = "use ecommDb";
		String query="select * from products";
		PreparedStatement pstm=ConnectionClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		ResultSet rs=pstm.executeQuery();
		while(rs.next()) {
			System.out.println("ProductId\tProductName\tPrice\tStock");
			System.out.println(rs.getInt("productId")+"\t\t"+rs.getString("productName")+"\t\t"+rs.getInt("price")+"\t\t"+rs.getInt("stock"));
		}
		
	}
	void viewCustomer() throws SQLException {
		String useDb = "use ecommDb";
		String query="select * from customers";
		PreparedStatement pstm=ConnectionClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		ResultSet rs=pstm.executeQuery();
		while(rs.next()) {
			//custId,username,phoneNumber
			System.out.println("custId\tusername\tphoneNumber");
			System.out.println(rs.getInt("custId")+"\t\t"+rs.getString("username")+"\t\t"+rs.getInt("phoneNumber"));
		}
	}
	void updateProduct(int qty,int id) throws SQLException {
		String useDb = "use ecommDb";
		String query="update products set stock=? where productId=?";
		PreparedStatement pstm=ConnectionClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		pstm.setInt(1, qty);
		pstm.setInt(2, id);
		int rows=pstm.executeUpdate();
		if(rows>0) {
			System.out.println("Updation is successfully");
		}
		else {
			System.out.println("Updation is not successfully");
		}
	}
	void clearOrder() throws SQLException {
		String useDb = "use ecommDb";
		String query="truncate table orders";
		Statement stm=ConnectionClass.getConnection().createStatement();
		stm.execute(useDb);
		stm.execute(query);
		stm.close();
		
	}

}
