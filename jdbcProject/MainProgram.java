package com.StudentManagementSystemsql;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainProgram {
public static void main(String[] args) throws SQLException, InvalidAgeException {
	StudentClassImp stu=new StudentClassImp();
	Scanner sc=new Scanner(System.in);
	do {
		System.out.println("1->add student");
		System.out.println("2->Display all students");
		System.out.println("3->update the student by id");
		System.out.println("4->Delete the student id");
		System.out.println("5-> Search by student id");
		System.out.println("6->Search by student name");
		System.out.println("7->Performance Analysis ");
		System.out.println("8->Exit");
		System.out.println("Enter the choice");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:System.out.println("Enter the id(numeric)");
		int id=0;
		try {
			id=sc.nextInt();
		}
		catch(InputMismatchException e) {
			System.out.println("Invalid entry it should be numeric");
		}	
		System.out.println("Enter the name");
		String name=sc.next();
		System.out.println("Enter the class");
		String classes=sc.next();
		System.out.println("Enter the marks(numeric) and less than or equal to 100");
		int marks=0;
		try {
			marks=sc.nextInt();
			if(marks>100) {
				throw new InvalidAgeException("Marks should be less than 100");
			}	
		}
		catch(InputMismatchException e) {
			System.out.println("Entry of value is not correct , it should be numeric");
		}
		System.out.println("Enter the age(numeric) and ");
		int age=sc.nextInt();
		try {
			stu.addStudent(new Student(id,name,classes,marks,age));
		}
		catch(Exception e) {
			e.getLocalizedMessage();
			System.out.println("Invalid entry of value");
		}
		break;
		case 2:for (Student string :stu.getAllStudent()) {
			System.out.println(string);
		}break;
		case 3:System.out.println("Enter the student id to update");
		int stuUp=sc.nextInt();
		System.out.println("Enter the marks to change it");
		int stuMarks=sc.nextInt();
		stu.UpdateById(stuUp, stuMarks);break;
		case 4:
			System.out.println("Enter the student id to delete it");
			int delId=sc.nextInt();
			stu.DeleteById(delId);break;
		case 5:
			System.out.println("Enter the student id to search for");
			int searchId=sc.nextInt();
			stu.searchById(searchId);break;
		case 6:
			System.out.println("Enter the student name to search for");
			String searchName=sc.next();
			stu.searchByName(searchName);break;
		case 7:
			stu.PerformanceInClass();break;
		case 8:
			System.out.println("Exit operation");
		}
		
	}
	while(true);
	
	
	
	
	
}
}
