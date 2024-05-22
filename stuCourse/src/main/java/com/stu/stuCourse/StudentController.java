package com.stu.stuCourse;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.StudentDao;
import com.model.Student;

@RestController
public class StudentController {
	
		@Autowired
		StudentDao studentDao;
		
		@GetMapping("getStudents")
		public List<Student> getStudents() {
			return studentDao.getStudents();
		}
		
		@GetMapping("getStudentById/{empId}")
		public Student getStudentById(@PathVariable("stuId") int studentId) {
			return studentDao.getStudentById(studentId);
		}
		
		@GetMapping("getStudentByName/{stuName}")
		public Student getStudentByName(@PathVariable("stuName") String studentName) {
			return studentDao.getStudentByName(studentName);
		}
		
		@GetMapping("getStudentByEmailid/{emailId}")
		public Student getStudentByEmailid(@PathVariable("emailId") String studentEmail) {
			return studentDao.getStudentByEmailId(studentEmail);
		}
		
		@PostMapping("registerStudent")
		public String registerStudent(@RequestBody Student student) {
			studentDao.registerStudent(student);
			return "Student Registered Successfully!!!";
		}
		
		@PutMapping("updateEmployee")
		public String updateStudent(@RequestBody Student student) {
			studentDao.updateStudent(student);
			return "Student Updated Successfully!!!";
		}
		
		@DeleteMapping("deleteEmployeeById/{empId}")
		public String deleteStudentById(@PathVariable("stuId") int studentId) {
			studentDao.deleteStudentById(studentId);
			return "Student Deleted Successfully!!!";
		}
		
		
		@GetMapping("stuLogin/{emailId}/{password}")
		public Student stuLogin(@PathVariable("emailId") String emailId, @PathVariable("password") String password) {
			Student emp = studentDao.getStudentByEmailId(emailId);
			if(emp != null){
				BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
				if(bcpe.matches(password, emp.getPassword())){
					return emp;
				}
			}
			return null;
		}

		@PostMapping("Emailotp/{emailId}")
		public String otp(@PathVariable("emailId") String emailId){
			Student emp = studentDao.getStudentByEmailId(emailId);
			if(emp != null){
				studentDao.generateEmailOtp(emp);
				return "OTP sent to " + emailId;
			}
			return "Email Not Found";
		}

		@PostMapping("PhoneOtp/{phoneNo}")
		public String otpPhoneNo(@PathVariable("phoneNo") String phoneNo){
			Student stud = studentDao.getStudentByPhoneNo(phoneNo);
			if(stud != null){
				studentDao.generatePhoneOtp(stud);
				return "OTP sent to : " + phoneNo;
			}
			return "PhoneNumber not Found";
		}
		
		@PostMapping("validateEmailOtp/{emailId}/{otp}")
		public String validateEmailOtp(@PathVariable("emailId") String emailId, @PathVariable("otp") int otp){
			if(studentDao.validateEmailOtp(emailId, otp)){
				return "OTP is valid !!!";
			}
			else {
				return "OTP is Invalid !!!";
			}
		}

		@PostMapping("validatePhoneOtp/{phoneNo}/{otp}")
		public String validatePhoneOtp(@PathVariable("phoneNo") String phoneNo, @PathVariable("otp") int otp){
			if(studentDao.validatePhoneOtp(phoneNo, otp)){
				return "OTP is valid !!!";
			}
			else {
				return "OTP is Invalid !!!";
			}
		}

	}



