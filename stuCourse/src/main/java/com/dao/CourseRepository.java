package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

	@Query("from Course c where c.courseName = : cname")
	Course findByName(@Param("cname") String courseName);
}
