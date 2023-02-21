package com.web.Mcq.entity;


import lombok.Data;

import javax.persistence.*;

@Entity

@Table(name = "Security")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstName")
    private String userName;

    @Column(name = "emailId")
    private String EmailId;
    @Column(name = "phone")
    private  String PhoneNumber;


}
