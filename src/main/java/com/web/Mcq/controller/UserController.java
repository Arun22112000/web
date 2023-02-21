package com.web.Mcq.controller;

import com.web.Mcq.dto.UserDto;
import com.web.Mcq.entity.User;
import com.web.Mcq.service.UserServiceImpl;
import com.web.Mcq.utils.UserConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = UserConstants.API)
@CrossOrigin(origins = "*")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping(value = UserConstants.POST_User, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addEmployee(@RequestBody UserDto userDto) {
        String serviceResponse = userService.saveUser(userDto);
        if (serviceResponse != null) {
            return ResponseEntity.ok(serviceResponse);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getUserById")
    public Optional<User> getOffers(Long id){
        return userService.getUserById(id);
    }

    @GetMapping(value = "/getAllUser")
    public List<UserDto> getAllUser() {
        return userService.getAllUser();
    }

    @DeleteMapping("/deleteUserById")
    public String deleteUserById(@RequestParam("id") Long id) {
        return userService.deleteUserById(id);
    }
}
