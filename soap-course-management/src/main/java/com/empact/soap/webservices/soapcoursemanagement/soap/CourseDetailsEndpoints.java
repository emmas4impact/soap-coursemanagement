package com.empact.soap.webservices.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.empact.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.empact.soap.webservices.soapcoursemanagement.soap.exception.CourseNotFoundException;
import com.empact.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;
import com.empact.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService.Status;

import https.courses_in28minutes_com.courses.CourseDetails;
import https.courses_in28minutes_com.courses.DeleteCourseDetailsRequest;
import https.courses_in28minutes_com.courses.DeleteCourseDetailsResponse;
import https.courses_in28minutes_com.courses.GetAllCourseDetailsRequest;
import https.courses_in28minutes_com.courses.GetAllCourseDetailsResponse;
import https.courses_in28minutes_com.courses.GetCourseDetailsRequest;
import https.courses_in28minutes_com.courses.GetCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndpoints {
	
	@Autowired
	CourseDetailsService service;
	//Getcoursedetailsrequest as input
	//getcoursedetailsresponse as output
	
	@PayloadRoot(namespace="https://courses.in28minutes.com/courses", localPart="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse 
	processCourseDeatilsRequest(@RequestPayload GetCourseDetailsRequest request) {
		Course course  = service.findById(request.getId());
		
		if(course==null)
			throw new CourseNotFoundException("Invalid Course Id "+ request.getId());
		return mapCourseDetails(course);
	}
	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		
		response.setCourseDetails(mapCourse(course) );
		return response;
	}
	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for(Course course:courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
		

		return response;
	}
	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course .getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
	
	@PayloadRoot(namespace="https://courses.in28minutes.com/courses", localPart="GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse 
	processAllCourseDeatilsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
		List<Course> courses  = service.findAll();
		
		return mapAllCourseDetails(courses);
	}
	
	@PayloadRoot(namespace="https://courses.in28minutes.com/courses", localPart="DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse 
	deleteCourseDeatilsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
		Status status = service.deleteById(request.getId());
		
		DeleteCourseDetailsResponse  response = new DeleteCourseDetailsResponse(); 
		response.setStatus(mapStatus(status));
		return response;
	}
	private https.courses_in28minutes_com.courses.Status mapStatus(Status status) {
		// TODO Auto-generated method stub
		if(status== Status.NOT_FOUND)
			return https.courses_in28minutes_com.courses.Status.NOT_FOUND;
		return https.courses_in28minutes_com.courses.Status.SUCCESS;
	}

}
