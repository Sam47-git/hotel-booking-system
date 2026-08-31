package com.sam.project.AirBnb.service;

import com.sam.project.AirBnb.dto.GuestDTO;
import com.sam.project.AirBnb.dto.ProfileUpdateRequestDTO;
import com.sam.project.AirBnb.dto.UserDTO;
import com.sam.project.AirBnb.entities.User;
import org.jspecify.annotations.Nullable;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;

import java.util.List;

public interface UserService {

    User getUserById(Long userId);

    void updateProfile(ProfileUpdateRequestDTO profileUpdateRequestDto);

    UserDTO getMyProfile();
}
