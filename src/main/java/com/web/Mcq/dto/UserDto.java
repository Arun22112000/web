package com.web.Mcq.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDto {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    @NotBlank(message="Please enter your first name")
    private String userName;

    @NotNull
    @NotBlank(message="Please enter your  emailId")
    private String EmailId;
    @NotNull
    @NotBlank(message="Please enter your phoneNumber")
    private  String PhoneNumber;
}
