package com.micro.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.user.dto.UserRequest;
import com.micro.user.dto.UserResponse;
import com.micro.user.services.UserServie;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
	@Autowired
	private UserServie userServie;
	
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
		UserResponse response = userServie.saveUser(request);
		return new ResponseEntity<UserResponse>(response,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUser(){
		List<UserResponse> response = userServie.getAllUser();
		return  ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserResponse> getUser(@PathVariable(name="userId") String userId){
		UserResponse response = userServie.getUser(userId);
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable(name="userId") String userId,@RequestBody UserRequest request){
		UserResponse response = userServie.updateUser(request, userId);
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Map<String,Object>> deleteUser(@PathVariable(name="userId") String userId){
		Map<String,Object> response = userServie.deleteUser(userId);
		return ResponseEntity.ok().body(response);
	}

}
