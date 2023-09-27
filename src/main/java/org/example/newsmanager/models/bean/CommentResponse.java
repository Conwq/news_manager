package org.example.newsmanager.models.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
	private int id;
	private String text;
	private String username;
	private String publicationDate;
}
