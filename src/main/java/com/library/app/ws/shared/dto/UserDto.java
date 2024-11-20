package com.library.app.ws.shared.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto implements Serializable {
	private static final long serialVersionUID = 6620619358278787349L;
	private long id;
	private String username;
	private String role;
	private String email;
	private String password;
	private OffsetDateTime createdAt;
	private Boolean isBorrow;
}
