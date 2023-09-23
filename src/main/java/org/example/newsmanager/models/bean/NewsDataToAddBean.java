package org.example.newsmanager.models.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class NewsDataToAddBean {
	private String title;
	private String brief;
	private String content;
	private List<Integer> tags;
}