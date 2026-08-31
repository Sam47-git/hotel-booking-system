package com.sam.project.AirBnb.dto;

import com.sam.project.AirBnb.entities.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileUpdateRequestDTO {

    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
}
