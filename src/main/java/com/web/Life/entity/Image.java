package com.web.life.entity;



import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "life_at_qb_image")
public class Image implements Serializable {

  private static final long serialVersionUID = 3280724094419154315L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_type")
    private String imageType;

    @Column(name = "image_size")
    private Long imageSize;


    @Column(name = "image_data")
    @Lob
    private byte[] data;




}