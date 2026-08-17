package com.sam.project.AirBnb.service;

import com.sam.project.AirBnb.entities.User;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;

public interface UserService {

    User getUserById(Long userId);
}
