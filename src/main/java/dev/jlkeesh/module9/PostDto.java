package dev.jlkeesh.module9;

import lombok.Data;

@Data
public class PostDto{
	private int id;
	private String title;
	private String body;
	private int userId;
}