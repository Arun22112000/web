package com.web.ourservice.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "News_image")
public class NewsImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String imageName;


    private String imageType;


    private Long imageSize;


    @Lob
    private byte[] data;



}