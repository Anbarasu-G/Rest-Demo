package com.example.rest.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserRequest {
	
	@NotNull(message = "username is cannot be null")
    private String username;
	
	@NotNull(message = "email is cannot be null")
	@Email(regexp="^[a-zA-Z0-9._%+-]+@gmail\\.com$",
	message = "It should end with @gmail.com")
    private String email;
	
	@Pattern(regexp = "^(?=.*[A-Z)(?=.*[a-z])(?=.*\\d)(?=.*[@%#*!])[A-Za-z\\d@%#*!]{8,}$",
			message = "should contain at-least one Uppercase "
			+ "should contain at-least one Lowercase"
			+ "should contain at-least one Special Character(@,$,%,*)"
			+ "should contain at-least one Numeric digit and min length should be 8 digits")
			
    private String password;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}    
   
}
