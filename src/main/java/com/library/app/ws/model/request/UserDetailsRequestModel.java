package com.library.app.ws.model.request;

import lombok.Data;

@Data
public class UserDetailsRequestModel {

	private String username;

	private String email;

	private String password;

	private String role;

}
