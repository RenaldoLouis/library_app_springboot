package com.library.app.ws.model.request;

import java.util.List;

import lombok.Data;

@Data
public class BorrowedBooksDetailsRequestModel {

	private Integer userId;
	private List<Integer> bookIds;
	private Integer borrowDuration;
}
