package com.library.app.ws.shared.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import com.library.app.ws.io.entity.BooksEntity;
import com.library.app.ws.io.entity.UserEntity;

import lombok.Data;

@Data
public class BorrowedBooksDto implements Serializable {

	private static final long serialVersionUID = 6620619358278787349L;

	private long id;
	private UserEntity user;
	private List<BooksEntity> book;
	private Integer borrowDuration;
	protected OffsetDateTime deadline;
	protected OffsetDateTime borrowedAt;
	protected OffsetDateTime returnedAt;
	private List<Integer> bookIds;
}
