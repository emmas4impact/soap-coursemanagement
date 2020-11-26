package com.empact.soap.webservices.soapcoursemanagement.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.courses_in28minutes_com.courses.CourseDetails;
import https.courses_in28minutes_com.courses.GetCourseDetailsRequest;
import https.courses_in28minutes_com.courses.GetCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndpoints {
	//Getcoursedetailsrequest as input
	//getcoursedetailsresponse as output
	
	@PayloadRoot(namespace="https://courses.in28minutes.com/courses", localPart="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse 
	processCourseDeatilsRequest(@RequestPayload GetCourseDetailsRequest request) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(request.getId());
		courseDetails.setName("Learning Java Spring");
		courseDetails.setDescription("This is gonna be a great course!");
		
		response.setCourseDetails(courseDetails );
		return response;
	}

}
