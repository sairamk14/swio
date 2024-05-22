package com.dao;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.model.Student;
import com.model.StudentOtp;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;    

@Service
public class StudentDao {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	JavaMailSender mailSender;
	@Autowired
	StudentOtp studentOtp;
	
	public List<Student> getStudents(){
		return studentRepository.findAll();
	}
	
	public Student getStudentById(int stuId) {
		return studentRepository.findById(stuId).orElse(new Student(0,"Not Found","","","",null));	
	}
	
	public Student getStudentByName(String stuName) {
		return studentRepository.findByName(stuName);	
	}
	
	public void registerStudent(Student stu){
		BCryptPasswordEncoder enp = new BCryptPasswordEncoder();
	    String bcryptpw = enp.encode(stu.getPassword());
	    stu.setPassword(bcryptpw);
	    studentRepository.save(stu);
	}
	
	public void updateStudent(Student stu) {
		studentRepository.save(stu);
	}
	
	public void deleteStudentById(int stuId) {
		studentRepository.deleteById(stuId);
	}
	
	public Student stuLogin(String emailId,String password) {
		return studentRepository.stuLogin(emailId,password);
	}
		
	public Student getStudentByEmailId(String emailId) {
		return studentRepository.findByName(emailId);	
	}
	
	
	public Student getStudentByPhoneNo(String phoneNo) {
		return studentRepository.findByPhoneNo(phoneNo);
	}
	
	public void generateEmailOtp(Student stud){
		int otp = (int) (Math.random() * 900000) + 100000;		
		LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(1);
		studentOtp.setEmailOtp(otp);
		studentOtp.setEmailOtpExpiryTime(expiryTime);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(stud.getEmailId());
		message.setSubject("Your OTP Code");
		message.setText("Your OTP code is " + otp);

		mailSender.send(message);
	}
	
	public boolean validateEmailOtp(String emailId, int otp) {
		Student stud = studentRepository.findByEmail(emailId);
		if(stud != null){
			if(studentOtp.getEmailOtp() == otp && studentOtp.getEmailOtpExpiryTime().isAfter(LocalDateTime.now())){
				studentOtp.setEmailOtp(0);
				studentOtp.setEmailOtpExpiryTime(null);
				return true;
			}
		}
		return false;
	}
	
	
	public void generatePhoneOtp(Student stud) {
		int otp = (int) (Math.random() * 9000) + 1000;
		LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(1);
		studentOtp.setPhoneOtp(otp);
		studentOtp.setPhoneOtpExpiryTime(expiryTime);
		Message.creator(new PhoneNumber(stud.getPhoneNumber()), new PhoneNumber("+19014787757"), "Hello " + stud.getStuName() + ", Your Student OTP is : " + otp ).create();
	}	

	public boolean validatePhoneOtp(String phoneNo, int otp) {

		Student stud = studentRepository.findByPhoneNo(phoneNo);
		if(stud != null){
			if(studentOtp.getPhoneOtp() == otp && studentOtp.getPhoneOtpExpiryTime().isAfter(LocalDateTime.now())){
				studentOtp.setPhoneOtp(0);
				studentOtp.setPhoneOtpExpiryTime(null);
				return true;
			}
		}
		return false;
	}

}
