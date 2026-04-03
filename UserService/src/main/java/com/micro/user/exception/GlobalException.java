package com.micro.user.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.micro.user.dto.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler()
	public ResponseEntity<ApiResponse> handleResorceNOtFoundException(ResourceNotFoundException ex,
			HttpServletRequest request) {
		ApiResponse response = ApiResponse.builder().timestamp(LocalDateTime.now()).msg(ex.getMessage()).status(false)
				.httpStatus(HttpStatus.NOT_FOUND).path(request.getRequestURI()).build();
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}
}