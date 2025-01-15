package com.bms.service;

import java.sql.SQLException;
import java.util.List;

import com.bms.dao.BillDao;
import com.bms.module.Costumer;
import com.bms.module.Product;

public class BillService {
BillDao dao=new BillDao();
public String createTableAndDatabase() throws SQLException {
	return dao.createTableAndDatabase();
}
public String insertProduct(Product product) throws SQLException {
	return dao.insertProduct(product);
}
public String updateProduct(float price,int stock,int id) throws SQLException{
	return dao.updateProduct(price, stock, id);
}
public List<Product> viewProducts() throws SQLException{
	return dao.viewProducts();
}
public String insertCostumer(Costumer costumer) throws SQLException {
	return dao.insertCostumer(costumer);
}
public String generateOrder(int id,int pid) throws SQLException {
	return dao.generateOrder(id, pid);
}
public String searchProductByName(String name) throws SQLException {
	return dao.searchProductByName(name);
}
public List<Product> searchProductByCategory(String category) throws SQLException{
	return dao.searchProductByCategory(category);
}
public void salesReportByProduct(int proid) throws SQLException{
	dao.salesReportByProduct(proid);
}
public String updateCostumer(int id, int phoneNumber) throws SQLException{
	return dao.updateCostumer(id, phoneNumber);
}
public List<Costumer> viewCostumer() throws SQLException{
	return dao.viewCostumer();
}
public void bill(int orderid) throws SQLException{
	dao.bill(orderid);
}
}
