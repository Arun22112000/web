package com.web.ourservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.utils.BaseEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "News")
@Getter
@Setter
public class News extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String tagLine;
    @Column(columnDefinition="TEXT")
    private String mainText;
    @Column(columnDefinition="TEXT")
    private String subText;
    private String url;

    private String buttonName;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "imageDetails_id")
    @JsonIgnore
    private NewsImage newsImage;
}
