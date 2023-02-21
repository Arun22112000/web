package com.web.weare.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.weare.entity.FeedBackImage;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class FeedBackDto {
    private Long Id;

    private String tagLine;

    private String buttonName;
    @Column(columnDefinition="TEXT")
    private String mainText;
    @Column(columnDefinition="TEXT")

    private String subText;
    private String url;
    @JsonIgnore
    private List<FeedBackImage> feedBackImage;
}
