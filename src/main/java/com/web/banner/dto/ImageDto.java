package com.web.banner.dto;


import lombok.Data;

import javax.persistence.Lob;

@Data
public class ImageDto {
    private int id;
    private String fileName;
    @Lob
    private byte[] data;
}
