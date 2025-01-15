package com.bms.dao;

import java.sql.SQLException;
import java.util.List;

import com.bms.module.Costumer;
import com.bms.module.Product;

public interface Bill {
	public String createTableAndDatabase() throws SQLException;
	public String insertProduct(Product product) throws SQLException;
	public String updateProduct(float price,int stock,int id) throws SQLException;
	public List<Product> viewProducts() throws SQLException;
	public String insertCostumer(Costumer costumer) throws SQLException;
	public String generateOrder(int id,int pid)throws SQLException ;
	public String searchProductByName(String name) throws SQLException;
	public List<Product> searchProductByCategory(String category) throws SQLException;
	public void salesReportByProduct(int proid) throws SQLException; 
	public String updateCostumer(int id,int phoneNumber) throws SQLException;
	public List<Costumer> viewCostumer() throws SQLException;
	public void bill(int orderid) throws SQLException;
}
