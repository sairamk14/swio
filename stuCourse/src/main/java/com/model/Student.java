package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Student {
	
     @Id@GeneratedValue
     private int stuId;
     private String stuName;
     private String gender;
     private String emailId;
     private String password;
     private String phoneNumber;
     
     @ManyToOne
     @JoinColumn(name="courseId")
     Course course;
     
	public Student() {
		super();
	}

	public Student(int stuId, String stuName, String gender, String emailId, String password, Course course) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.gender = gender;
		this.emailId = emailId;
		this.password = password;
		this.course = course;
	}

	public int getStuId() {
		return stuId;
	}

	public void setStuId(int stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Student [stuId=" + stuId + ", stuName=" + stuName + ", gender=" + gender + ", emailId=" + emailId
				+ ", password=" + password + ", course=" + course + "]";
	}
     
	
     
}
