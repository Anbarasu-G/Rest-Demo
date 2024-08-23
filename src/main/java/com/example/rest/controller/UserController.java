package com.example.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.requestdto.UserRequest;
import com.example.rest.responsedto.UserResponse;
import com.example.rest.service.UserService;
import com.example.rest.util.AppResponseBuilder;
import com.example.rest.util.ErrorStructure;
import com.example.rest.util.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class UserController {


	private UserService userService;
	private AppResponseBuilder responseBuilder;


	public UserController(UserService userService, AppResponseBuilder responseBuilder) {
		super();
		this.userService = userService;
		this.responseBuilder = responseBuilder;
	}

	@Operation(description = "This endpoint is used to save the data in the database",
			responses = {@ApiResponse(responseCode = "202",description = "User Created"),
					@ApiResponse(responseCode = "500",description = "Internal Server Error",
					content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
	})

	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@RequestBody   UserRequest userRequest) {
		UserResponse  response=userService.saveUser(userRequest);
		return responseBuilder.success(HttpStatus.CREATED, "user created", response);
	}

	@Operation(description = "This endpoint is used to retrieve the data in the data based on their Id's",
			responses = {
					@ApiResponse(responseCode = "303",description = "User Found"),
					@ApiResponse(responseCode = "404",description = "Failed to find the User",
					content = {
							@Content(schema = @Schema(anyOf = ErrorStructure.class))
					})
	})

	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findUser(@PathVariable int userId) {
		UserResponse userResponse = userService.findUser(userId);
		return responseBuilder.success(HttpStatus.FOUND, "user found", userResponse);
	}

	@Operation(description = "This endpoint is used to update the user details in the database",
			responses = {
					@ApiResponse(responseCode = "202",description = "User Updated"),
					@ApiResponse(responseCode = "500",description = "Internal Server Error",
					content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
	})

	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@RequestBody UserRequest userRequest,@PathVariable int userId) {
		UserResponse userResponse=userService.updateUser(userRequest,userId);
		return responseBuilder.success(HttpStatus.OK, "user updated", userResponse);			
	}

	@Operation(description = "This endpoint is used delete the user in the database based on their Id's",
			responses = {
					@ApiResponse(responseCode = "202",description = "User Deleted"),
					@ApiResponse(responseCode = "500",description = "Internal Server Error",
					content = {
							@Content(schema = @Schema(anyOf = RuntimeException.class))
					})
	})

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int userId) {
		UserResponse userResponse = userService.deleteUser(userId);
		return responseBuilder.success(HttpStatus.OK, "user deleted", userResponse);	
	}

	@Operation(description = "This endpoint is used to find all the User in the database",
			responses = {
					@ApiResponse(responseCode = "303",description = "Users Found"),
					@ApiResponse(responseCode = "404",description = "Users not Found",
					content = {
							@Content(schema =@Schema(anyOf = RuntimeException.class))
					})
	})

	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUser(){
		List<UserResponse> users = userService.findAllUser();
		return responseBuilder.success(HttpStatus.FOUND, "users found", users);
	}
}
