package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query("from Student s where s.stuName = :sname")
    Student findByName(@Param("sname") String studentname);
	
	@Query("from Student s where s.emailId = :semail")
    Student findByEmail(@Param("semail") String studentEmail);

	@Query("from Student s where s.emailId = :emailId and s.password = :password")
    Student stuLogin(@Param("emailId") String emailId, @Param("password") String password);

	@Query("from Student s where s.phoneNo = :pNo")
	Student findByPhoneNo(@Param("pNo") String phoneNo);

}
