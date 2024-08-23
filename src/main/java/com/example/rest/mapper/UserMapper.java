package com.example.rest.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.rest.model.User;
import com.example.rest.requestdto.UserRequest;
import com.example.rest.responsedto.UserResponse;

@Component
public class UserMapper {

	public User mapToUserEntity(UserRequest request,User user) {
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());

		return user;
	}


	public UserResponse mapToUserResponse(User user) {
		UserResponse response=new  UserResponse();

		response.setUserId(user.getUserId());
		response.setUsername(user.getUsername());
		response.setEmail(user.getEmail());

		return response;
	}


	public List<UserResponse> mapToUserResponse(List<User> users) {
		List<UserResponse> responses=new ArrayList<UserResponse>();
		for (User user:users) {
             UserResponse response=new UserResponse();
             
             response.setUserId(user.getUserId());
             response.setUsername(user.getUsername());
             response.setEmail(user.getEmail());
             responses.add(response);
		}
		return responses;
	}
}
