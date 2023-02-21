package com.web.life.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.life.entity.Image;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TextDto {

    private long Id;
    private String tagLine;

    private String buttonName;

    private String textMessage;
@JsonIgnore
    private Image image;
}
