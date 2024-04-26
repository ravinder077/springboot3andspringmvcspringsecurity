package com.eclerx.basecode.security.model;

import java.util.List;

import lombok.Data;

@Data
public class LoginResponse {

	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<String> roles;
	public LoginResponse(String token, Long id, String username, String email,
			List<String> roles) {
		super();
		this.token = token;
		this.type = type;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
}
