package com.empact.soap.webservices.soapcoursemanagement.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode=FaultCode.CUSTOM, customFaultCode="{http://in28mintues.com/courses}001_COURSE_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 7383674824825116754L;

	public CourseNotFoundException(String message) {
		super(message);
	
	}

}