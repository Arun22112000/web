package com.web.ourservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.ourservice.entity.NewsImage;
import lombok.Data;

import java.util.List;

@Data
public class NewsDto {
    private Long Id;
    private String tagLine;

    private String buttonName;
    private String mainText;
    private String subText;
    private String url;

    @JsonIgnore
    private List<NewsImage> newsImage;
}
