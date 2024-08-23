package com.example.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.rest.exceptions.UserNotFoundByIdException;
import com.example.rest.mapper.UserMapper;
import com.example.rest.model.User;
import com.example.rest.repository.UserRepo;
import com.example.rest.requestdto.UserRequest;
import com.example.rest.responsedto.UserResponse;


@Service
public class UserService {
	private UserRepo userRepo;
	private UserMapper userMapper;


	public UserService(UserRepo userRepo,UserMapper userMapper) {
		super();
		this.userRepo = userRepo;
		this.userMapper=userMapper;
	} 

	public UserResponse saveUser(UserRequest userRequest) {
		User user=userMapper.mapToUserEntity(userRequest,new User());
		user=userRepo.save(user);
		return userMapper.mapToUserResponse(user); 
	}

	public UserResponse findUser(int userId) {
		return	userRepo.findById(userId)
				.map(user -> userMapper.mapToUserResponse(user))
				.orElseThrow(() -> new UserNotFoundByIdException("User Not Found"));
	}

	public UserResponse updateUser(UserRequest userRequest,int userId) {	
		return userRepo.findById(userId)
				.map(exUser -> {
					userMapper.mapToUserEntity(userRequest, exUser);
					exUser = userRepo.save(exUser);
					return userMapper.mapToUserResponse(exUser);

				}).orElseThrow(() -> new UserNotFoundByIdException("Failed to Update"));

	}

	public UserResponse deleteUser(int userId) {
		return userRepo.findById(userId)
				.map(user -> {
					userRepo.delete(user);
					return	userMapper.mapToUserResponse(user);
				}
						)
				.orElseThrow(() -> new UserNotFoundByIdException("Failed To Delete"));


		//		Optional<User> optional = userRepo.findById(userId);
		//		if (optional.isPresent()) {
		//			User user=optional.get();
		//			userRepo.delete(user);
		//			return userMapper.mapToUserResponse(user);
		//		}
		//		else
		//			throw new UserNotFoundByIdException("Failed to delete user");
		//	}
	}
	public List<UserResponse> findAllUser() {
		//		List<UserResponse> responses=new ArrayList<>();
		//		userRepo.findAll()
		//		.forEach(user -> responses.add(userMapper.mapToUserResponse(user)));
		//		return responses;

		return	userRepo.findAll()
				.stream()
				.filter(user -> user.getUsername()!=null)
				.map(user -> userMapper.mapToUserResponse(user))
				.toList();
	}
}      
