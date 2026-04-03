package com.micro.user.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {
	private LocalDateTime timestamp;
    private String msg;
    private boolean status;
    private HttpStatus httpStatus;
    private String path;
}
