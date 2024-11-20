package com.library.app.ws.model.response;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class UserResp {

	private String username;
	private String role;
	private String email;
	private String password;
	private OffsetDateTime createdAt;
	private Boolean isBorrow;

}
