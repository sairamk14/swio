package com.stu.stuCourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.CourseDao;
import com.model.Course;

@RestController
public class CourseController {

	@Autowired
	CourseDao courseDao;
	
	@GetMapping("getCourses")
	public List<Course> getCourses() {
		return courseDao.getCourses();
	}
	
	@GetMapping("getCourseById/{courseId}")
	public Course getDepartmentById(@PathVariable("courseId") int courseId) {
		return courseDao.getCourseById(courseId);
	}
	
	@GetMapping("getCourseByName/{deptName}")
	public Course getCourseByName(@PathVariable("courseName") String courseName) {
		return courseDao.getCourseByName(courseName);
	}
	
	@PostMapping("registerCourse")
	public String registerCourse(@RequestBody Course course) {
		courseDao.registerCourse(course);
		return "Course Registered Successfully!!!";
	}
	
	@PutMapping("updateCourse")
	public String updateCourse(@RequestBody Course course) {
		courseDao.updateCourse(course);
		return "Course Updated Successfully!!!";
	}
	
	@DeleteMapping("deleteCourseById/{courseId}")
	public String deleteCourseById(@PathVariable("courseId") int courseId) {
		courseDao.deleteCourseById(courseId);
		return "Course Deleted Successfully!!!";
	}
}
