package org.example.newsmanager.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tags")
@Data
@NoArgsConstructor
public class TagEntity implements Serializable {
	@Column(name = "tag_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name")
	private String name;
	@ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
	private List<NewsEntity> news;
}