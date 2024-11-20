package com.library.app.ws.model.response;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class BooksResp {
	private String name;
	private OffsetDateTime createdAt;
}
