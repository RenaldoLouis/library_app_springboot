package com.library.app.ws.model.response;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Data;

@Data
public class BorrowBookResp {
	private String username;
	private List<String> bookname;
	private OffsetDateTime deadline;
}
