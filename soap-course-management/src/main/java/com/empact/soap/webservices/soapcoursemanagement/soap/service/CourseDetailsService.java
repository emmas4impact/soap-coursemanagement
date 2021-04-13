package com.empact.soap.webservices.soapcoursemanagement.soap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.empact.soap.webservices.soapcoursemanagement.soap.bean.Course;
import java.util.Iterator;


@Component
public class CourseDetailsService {
	public enum Status{
		SUCCESS, NOT_FOUND;
	}
	private static List<Course> courses = new ArrayList<>();
	
	static {
		Course course1 = new Course(1, "Spring", "Easy Steps");
		courses.add(course1);
		
		Course course2 = new Course(2, "Core Java", "Real life examples");
		courses.add(course2);
		
		Course course3 = new Course(3, "Hibernate", "WIth industrial made solution");
		courses.add(course3);
		
		Course course4 = new Course(4, "Spring MVC", "Learn the Model view controller in 10 ways");
		courses.add(course4);
	}
	
	//find course by id
	public Course findById(int id) {
		for(Course course : courses) {
			if(course.getId() == id) {
				return course;
			}
				
		}
		return null;
	}
	
	//courses
	public List<Course> findAll(){
		return courses;
	}
	
	public Status deleteById(int id) {
		
		Iterator<Course> iterator= courses.iterator();
		while(iterator.hasNext()) {
			Course course = iterator.next();
			if(course.getId() == id) {
				iterator.remove();
				return Status.SUCCESS;
			}
		}
		
		return Status.NOT_FOUND;
	}

}
