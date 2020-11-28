package com.empact.soap.webservices.soapcoursemanagement.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.empact.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.empact.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;

import https.courses_in28minutes_com.courses.CourseDetails;
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
		return mapCourse(course);
	}
	private GetCourseDetailsResponse mapCourse(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		
		
		
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course .getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		
		response.setCourseDetails(courseDetails );
		return response;
	}

}
