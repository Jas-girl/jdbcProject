package com.StudentManagementSystemsql;

import java.sql.SQLException;
import java.util.List;

public interface StudentInterface {
void addStudent(Student student) throws SQLException;
List<Student> getAllStudent() throws SQLException;
void UpdateById(int id,int marks) throws SQLException;
void DeleteById(int id) throws SQLException;
void searchById(int id) throws SQLException;
void searchByName(String name) throws SQLException;
void PerformanceInClass() throws SQLException;
void CloseConnection() throws SQLException;
}
