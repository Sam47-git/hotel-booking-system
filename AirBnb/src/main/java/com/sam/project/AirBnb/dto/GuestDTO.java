package com.sam.project.AirBnb.dto;

import com.sam.project.AirBnb.entities.User;
import com.sam.project.AirBnb.entities.enums.Gender;
import lombok.Data;

@Data
public class GuestDTO {

    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}
