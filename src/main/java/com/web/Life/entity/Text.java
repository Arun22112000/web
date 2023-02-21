package com.web.life.entity;


import com.web.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "life_at_qb_text")
public class Text   extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private String tagLine;

	private String buttonName;

	@Column(columnDefinition="TEXT")
	private String textMessage;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "imageDetails_id")
	private Image image ;

}

