package com.StudentManagementSystemsql;

public class Student {
private int id;
private String name;
private String classes;
private int marks;
private int age;
public Student(int id, String name, String classes, int marks,int age) {
	super();
	this.id = id;
	this.name = name;
	this.classes = classes;
	this.marks = marks;
	this.setAge(age);
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getClasses() {
	return classes;
}
public void setClasses(String classes) {
	this.classes = classes;
}
public int getMarks() {
	return marks;
}
public void setMarks(int marks) {
	this.marks = marks;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
@Override
public String toString() {
	return "Student [id=" + id + ", name=" + name + ", classes=" + classes + ", marks="  +marks+", age=" + age + "]";
}


}
