package com.bms.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sql.RowSet;

import com.bms.module.Costumer;
import com.bms.module.Product;

public class BillDao implements Bill {
	String useDb = "use billDb";
	Scanner sc=new Scanner(System.in);
	List<Product> list = new ArrayList<Product>();

	public String createTableAndDatabase() throws SQLException {
		String db = "create database if not exists billDb";

		String query = "create table if not exists product(productId int primary key,"
				+ "name varchar(30),category varchar(30),price decimal(10,2),stock int)";
		String query1 = "create table if not exists costumer(cId int primary key,"
				+ "name varchar (30),phoneNumber int)";
		String order="create table if not exists orders(orderid int primary key ,"
				+ "cid int,"
				+ "orderDate date,"
				+ "totalAmt decimal(10,2),"
				+ "foreign key (cid) references costumer(cid))";
		String order_details="create table if not exists orderDetails(order_detailid int primary key,"
				+ "orderid int,productId int,quantity int,subtotal decimal(10,2), foreign key (orderid) references orders("
				+ "orderid),foreign key (productId) references product(productId) )";
		Statement stm = UtilityClass.getConnection().createStatement();
		stm.execute(db);
		stm.execute(useDb);
		stm.execute(query);
		stm.execute(query1);
		stm.execute(order);
		stm.execute(order_details);
		stm.close();
		System.out.println("Table product is  created");
		System.out.println("Table customer is created ");
		System.out.println("Table order is created");
		return "Tables are created";

	}

	public String insertProduct(Product product) throws SQLException {
		String query = "insert into product(productId,name,category,price,stock)values(?,?,?,?,?)";
		PreparedStatement pstm = UtilityClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		pstm.setInt(1, product.getProductId());
		pstm.setString(2, product.getName());
		pstm.setString(3, product.getCategory());
		pstm.setFloat(4, product.getPrice());
		pstm.setInt(5, product.getStock());
		pstm.execute();
		pstm.close();
		return "product is inserted";
	}

	public String updateProduct(float price, int stock, int id) throws SQLException {
		String query = "update product set price=?,stock=? where productId=?";
		PreparedStatement pstm = UtilityClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		pstm.setFloat(1, price);
		pstm.setInt(2, stock);
		pstm.setInt(3, id);
		int rowAffected = pstm.executeUpdate();
		if (rowAffected > 0) {
			return "Product details are updated";
		}
		return "Product details are not updated";

	}

	public List<Product> viewProducts() throws SQLException {

		String query = "select * from product";
		PreparedStatement pstm = UtilityClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			list.add(new Product(rs.getInt("productId"), rs.getString("name"), rs.getString("category"),
					rs.getFloat("price"), rs.getInt("stock")));
		}
		return list;
	}

	@Override
	public String insertCostumer(Costumer costumer) throws SQLException {
		//String query1 = "create table if not exists costumer(cId int primary key,"
				//+ "name varchar (30),phoneNumber int";
		String query="insert into costumer(cid,name,phoneNumber)values(?,?,?)";
		PreparedStatement pstm=UtilityClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		pstm.setInt(1, costumer.getCid());
		pstm.setString(2, costumer.getName());
		pstm.setInt(3, costumer.getPhone());
		pstm.execute();
		return "Costumer is added";
	}

	@Override
	public String generateOrder(int id,int pid)throws SQLException  {
     
		boolean isPerson=false;
		boolean isProduct=false;
		//float totalAmt=0;
		int oid=0;
		int qty=0;
		int stock=0;
		float price=0;
		//Product product=new Product();
		PreparedStatement pstm=null;
		String query="select * from costumer where cid=?";
		pstm=UtilityClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		pstm.setInt(1, id);
		ResultSet rs=pstm.executeQuery();
		if(rs.next()) {
			isPerson=true;
			System.out.println("Costumer id:"+id+" is found");
		}
		else {
			isPerson=false;
			return "Costumer id:"+id+" is not found";
		}
		
		String query1="select * from product where productId=?";
		PreparedStatement pstm1=UtilityClass.getConnection().prepareStatement(query1);
		pstm1.setInt(1,pid);
		pstm1.execute(useDb);
		ResultSet rs1=pstm1.executeQuery();
		if(rs1.next()) {
			System.out.println("Enter the quantity");
			qty=sc.nextInt();
			stock = rs1.getInt("stock");
			price=rs1.getFloat("price");
			if(qty<stock) {
				
				isProduct=true;
				System.out.println("Product id:"+pid+"is found");
			}
			
		}
		else {
			isProduct=false;
			return "Product id:"+pid+"is not found";
		}
		
		if(isPerson && isProduct) {
			
			String order_insert="insert into orders(orderid,cid,orderDate,totalAmt)values(?,?,?,?)";
			pstm=UtilityClass.getConnection().prepareStatement(order_insert);
			pstm.execute(useDb);
			System.out.println("Enter the order id");
			oid=sc.nextInt();
			pstm.setInt(1,oid);
			pstm.setInt(2,id);
			pstm.setDate(3,new Date(System.currentTimeMillis()));
			pstm.setFloat(4,(qty*price));
			pstm.execute();
			System.out.println("Order is placed");
			String order_details="insert into orderDetails(order_detailid,orderid,productId,quantity,subtotal)values(?,?,?,?,?)";
			pstm=UtilityClass.getConnection().prepareStatement(order_details);
			pstm.execute(useDb);
			System.out.println("Enter the order detail id");
			int odid=sc.nextInt();
			pstm.setInt(1, odid);
			pstm.setInt(2, oid);
			pstm.setInt(3, pid);
			pstm.setInt(4, qty);
			pstm.setFloat(5,price);
			int affected=pstm.executeUpdate();
			if(affected>0) {
				System.out.println("Order details are placed");
			}
			else {
				System.out.println("Order details are not placed");
			}
			
			String updateStockQuery = "UPDATE product SET stock = (stock - ?) WHERE productId = ?";
            pstm = UtilityClass.getConnection().prepareStatement(updateStockQuery);
            pstm.execute(useDb);
            pstm.setInt(1, pid);
            pstm.setInt(2, (stock - qty));
            
            pstm.executeUpdate();

           
			
		}
		return "Order placed successfully!";
	}

	@Override
	public String searchProductByName(String name) throws SQLException {
		String query="select * from product where name=?";
		PreparedStatement pstm=UtilityClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		pstm.setString(1, name);
		ResultSet rs=pstm.executeQuery();
		if(rs.next()) {
			System.out.println(rs.getInt("productId")+" "+rs.getString("name")+" "+rs.getString("category")+" "+rs.getFloat("price")+" "+rs.getInt("stock"));
		    return "Product is found";
		}
		return "Product is not found";
	}

	@Override
	public List<Product> searchProductByCategory(String category) throws SQLException {
		List<Product> list=new ArrayList<Product>();
		String query="select * from product where category=?";
		PreparedStatement pstm=UtilityClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		pstm.setString(1, category);
		ResultSet rs=pstm.executeQuery();
		while(rs.next()) {
			list.add(new Product(rs.getInt("productId"),rs.getString("name"),rs.getString("category"),rs.getFloat("price"),rs.getInt("stock")));
			//System.out.println(rs.getInt("productId")+" "+rs.getString("name")+" "+rs.getString("category")+" "+rs.getFloat("price")+" "+rs.getInt("stock"));
		}
		if(list.isEmpty()) {
			System.out.println("products are not found in these category");
		}
		return list;
	}

	@Override
	public void salesReportByProduct(int proid) throws SQLException {
		String query="select * from orderDetails where productId=? ";
		PreparedStatement pstm=UtilityClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		pstm.setInt(1, proid);
		ResultSet rs=pstm.executeQuery();
		//String order_details="insert into orderDetails(order_detailid,orderid,productId,quantity,subtotal)values(?,?,?,?,?)";
		
		while(rs.next()) {
			System.out.println("OrderDetailid: "+rs.getInt("order_detailid")+"\t OrderId: "+rs.getInt("orderid")+" \t ProductId: "+rs.getInt("productId")+" \t Quantity: "+
		rs.getInt("quantity")+" Subtotal: "+rs.getFloat("subtotal"));
		}
		
		
	}

	@Override
	public String updateCostumer(int id, int phoneNumber) throws SQLException {
		String query="update costumer set phoneNumber=? where cId=?";
		PreparedStatement pstm=UtilityClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		pstm.setInt(1, id);
		pstm.setInt(2, phoneNumber);
		int rowAffected=pstm.executeUpdate();
		if(rowAffected>0) {
			return"updation successfull";
		}
		return "no updation";
	}

	@Override
	public List<Costumer> viewCostumer() throws SQLException {
		List<Costumer> list=new ArrayList<Costumer>();
		String query="select * from costumer";
		PreparedStatement pstm=UtilityClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		ResultSet rs=pstm.executeQuery();
		while(rs.next()) {
			list.add(new Costumer(rs.getInt("cId"),rs.getString("name"),rs.getInt("phoneNumber")));
			//System.out.println(rs.getInt("cId")+" "+rs.getString("name")+" "+rs.getInt("phoneNumber"));
		}
		return list;
	}

	@Override
	public void bill(int orderid) throws SQLException {
		String query="select * from orders where orderid=?";
		PreparedStatement pstm=UtilityClass.getConnection().prepareStatement(query);
		pstm.execute(useDb);
		pstm.setInt(1, orderid);
		ResultSet rs=pstm.executeQuery();
		while(rs.next()) {
			System.out.println("Order id: "+rs.getInt(1)+"\tCostumer id: "+rs.getInt(2)+"\t order date: "+rs.getDate(3)+"\t Total Salary: "+rs.getFloat(4));
		}
		String query1="select * from orderDetails where orderid=? ";
		PreparedStatement pstm1=UtilityClass.getConnection().prepareStatement(query1);
		pstm1.execute(useDb);
		pstm1.setInt(1, orderid);
		ResultSet rs1=pstm1.executeQuery();
		while(rs1.next()) {
//			try {
				System.out.println("OrderDetailid: "+rs1.getInt(1)+"\t OrderId: "+rs1.getInt(2)+" \t ProductId: "+rs1.getInt(3)+" \t Quantity: "+
						rs1.getInt(4)+" Subtotal: "+rs1.getFloat(5));
//			}
//			catch(Exception e) {
//				System.out.println("NO ORDER DETAILS");
//			}
			
		}
		
		
		
	}
	
}
