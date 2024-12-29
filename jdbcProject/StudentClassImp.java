package com.StudentManagementSystemsql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentClassImp implements StudentInterface {
	List<Student> stu=new ArrayList<Student>();
    public StudentClassImp() throws SQLException {
		String query="create database if not exists studentDb";
		String query1="use studentDb";
		String query2="CREATE TABLE IF NOT EXISTS student (id INT PRIMARY KEY, name VARCHAR(20), classes VARCHAR(30), marks INT, age INT)";
		Statement stm=UtilityClass.getConnection().createStatement();
		stm.execute(query);
		System.out.println("Datbase is created....");
		stm.execute(query1);
		System.out.println("Use of database");
		stm.execute(query2);
		System.out.println("Table is created");
	}
	@Override
	public void addStudent(Student student) throws SQLException {
		String query="insert into student(id,name,classes,marks,age)values(?,?,?,?,?)";
		PreparedStatement pstmt=UtilityClass.getConnection().prepareStatement(query);
		pstmt.setInt(1, student.getId());
		pstmt.setString(2, student.getName());
		pstmt.setString(3, student.getClasses());
		pstmt.setInt(4, student.getMarks());
		pstmt.setInt(5, student.getAge());
		pstmt.executeUpdate();
		System.out.println("student is added");
		pstmt.close();
		
		
	}

	@Override
	public List<Student> getAllStudent() throws SQLException {
	    String query = "SELECT * FROM student";
	    ArrayList<Student> student=new ArrayList<Student>();
	    try (PreparedStatement pstmt = UtilityClass.getConnection().prepareStatement(query);
	         ResultSet rs = pstmt.executeQuery()) {
	        
	        while (rs.next()) {
	            // Create and add a new Student object to the list
	            student.add(new Student(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getString("classes"),
	                rs.getInt("marks"),
	                rs.getInt("age")
	            ));
	        }
	    } catch (SQLException e) {
	        System.err.println("Error while fetching student data: " + e.getMessage());
	        throw e; // Rethrow exception to handle it at a higher level
	    }
	    
	    return student;
	}

	@Override
	public void UpdateById(int id,int marks) throws SQLException {
		String query="update student set marks=? where id=?";
		PreparedStatement pstm=UtilityClass.getConnection().prepareStatement(query);
		pstm.setInt(1, marks);
		pstm.setInt(2, id);
		int n=pstm.executeUpdate();
		if(n>0) {
			System.out.println("Updation done in the database");
		}
		else {
			System.out.println("Updation is not done in the database");
		}
		pstm.close();
	}

	@Override
	public void DeleteById(int id) throws SQLException {
		String query="delete from student where id=?";
		PreparedStatement pstm=UtilityClass.getConnection().prepareStatement(query);
		pstm.setInt(1, id);
		int n=pstm.executeUpdate();
		if(n>0) {
			System.out.println("student id"+id+" is removed from the database");
		}
		else {
			System.out.println("student id"+id+" is not removed from the database");
		}
	}

	@Override
	public void searchById(int id) throws SQLException {
		String query="select * from student where id=?";
		PreparedStatement pstm=UtilityClass.getConnection().prepareStatement(query);
		pstm.setInt(1, id);
		ResultSet rs=pstm.executeQuery();
		if(rs.next()) {
			System.out.println("*********************************************");
			System.out.println("Student id:"+rs.getInt("id"));
			System.out.println("Student Name:"+rs.getString("name"));
			System.out.println("Student Classes:"+rs.getString("classes"));
			System.out.println("Student Marks:"+rs.getInt("marks"));
			System.out.println("Student age:"+rs.getInt("age"));
			
		}
		
		
	}

	@Override
	public void searchByName(String name) throws SQLException {
		String query="select * from student where name=?";
		PreparedStatement pstm=UtilityClass.getConnection().prepareStatement(query);
		pstm.setString(1,name);
		ResultSet rs=pstm.executeQuery();
		if(rs.next()) {
			System.out.println("*********************************************");
			System.out.println("Student id:"+rs.getInt("id"));
			System.out.println("Student Name:"+rs.getString("name"));
			System.out.println("Student Classes:"+rs.getString("classes"));
			System.out.println("Student Marks:"+rs.getInt("marks"));
			System.out.println("Student age:"+rs.getInt("age"));
			
		}
		
		
	}

	@Override
	public void PerformanceInClass() throws SQLException {
		String query="select sum(marks) as TotalMarks,classes from student  group by classes";
		PreparedStatement pstm=UtilityClass.getConnection().prepareStatement(query);
		ResultSet rs=pstm.executeQuery();
		while(rs.next()) {
			System.out.println("*********************************************");
			System.out.println("Student Classes:"+rs.getString("classes"));
			System.out.println("Student Marks:"+rs.getInt("TotalMarks"));
		}
		
	}

	@Override
	public void CloseConnection() throws SQLException {
		UtilityClass.getConnection().close();
		
	}

}
