package com.micro.rating.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler
	public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex,HttpServletRequest request){
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", LocalDateTime.now());
		response.put("msg", ex.getMessage());
		response.put("stataus", false);
		response.put("httpstatus", HttpStatus.NOT_FOUND);
		response.put("path", request.getRequestURI());
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
	}
}
