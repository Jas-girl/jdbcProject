package com.bms.main;

import java.sql.SQLException;

import com.bms.controller.BillController;

public class MainClass {
public static void main(String[] args) throws SQLException {
	BillController controller=new BillController();
	controller.showMenu();
}
}
