package com.library.app.ws.model.response;

import lombok.Data;

@Data
public class GetBorrowBookResp {
	private String username;
	private String bookname;
	private Boolean isLate;
}
