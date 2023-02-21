package com.web.weare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "FeedBack")
@Getter
@Setter
public class Feedback extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String tagLine;

    private String buttonName;
    private String mainText;
    private String subText;
    private String url;



    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "imageDetails_id")

    private FeedBackImage feedBackImage;
}
