package com.web.weare.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "FeedBack_image")
public class FeedBackImage {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_type")
    private String imageType;

    @Column(name = "image_data")
    @Lob
    private byte[] data;





}