package com.web.Mcq.service;

import com.web.Mcq.dto.UserDto;
import com.web.Mcq.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    String saveUser(UserDto userDto);

    String deleteUserById(Long id);

    List<UserDto> getAllUser();

    Optional<User> getUserById(Long id);
}
