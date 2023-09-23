package org.example.newsmanager.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
public class NewsEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "news_id")
	private int id;
	@Column(name = "title")
	private String title;
	@Column(name = "brief")
	private String brief;
	@Column(name = "content")
	private String content;
	@Column(name = "publication_date")
	@Generated(GenerationTime.ALWAYS)
	private String publicationDate;
	@Column(name = "image_path")
	private String imagePath;
	@OneToMany(mappedBy = "newsEntity")
	private List<CommentEntity> comments;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "news_tags",
			joinColumns = @JoinColumn(name = "news_id"),
			inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<TagEntity> tags;
}