package com.library.app.ws.model.request;

import java.util.List;

import lombok.Data;

@Data
public class ReturnedBooksDetailsRequestModel {

	private Integer userId;
	private List<Integer> bookIds;

}
