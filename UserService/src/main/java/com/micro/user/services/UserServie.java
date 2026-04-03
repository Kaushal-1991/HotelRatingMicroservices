package com.micro.user.services;

import java.util.List;
import java.util.Map;

import com.micro.user.dto.UserRequest;
import com.micro.user.dto.UserResponse;

public interface UserServie {

	//create User
	UserResponse saveUser(UserRequest request);
	
	//Get All User
	List<UserResponse> getAllUser();
	
	//Get User by id
	UserResponse getUser(String userId);
	
	//Update User
	UserResponse updateUser(UserRequest request,String userId);
	
	//Delete User
	Map<String, Object> deleteUser(String userId);
}
