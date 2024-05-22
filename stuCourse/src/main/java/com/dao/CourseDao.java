package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Course;

@Service
public class CourseDao {

	@Autowired
	CourseRepository courseRepository;
	
	public List<Course> getCourses() {
		return courseRepository.findAll();
	}
	
	public Course getCourseById(int courseId){
		return courseRepository.findById(courseId).orElse(new Course(0,"Not Found",0.0));
	}
	
	public Course getCourseByName(String courseName){
		return courseRepository.findByName(courseName);
	}
	
	public void registerCourse(Course cour){
		courseRepository.save(cour);
	}
	
	public void updateCourse(Course cour){
		courseRepository.save(cour);
	}
	
	public void deleteCourseById(int courseId){
		courseRepository.deleteById(courseId);
	}
	
}
