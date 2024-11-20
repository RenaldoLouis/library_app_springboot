package com.library.app.ws.shared.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class BooksDto implements Serializable {
	private static final long serialVersionUID = 6620619358278787349L;
	private long id;
	private String name;
	private OffsetDateTime createdAt;
}
