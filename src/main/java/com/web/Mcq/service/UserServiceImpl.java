package com.web.Mcq.service;


import com.web.Mcq.dto.UserDto;
import com.web.Mcq.entity.User;
import com.web.Mcq.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;


import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.web.Mcq.utils.UserConstants.*;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        if (ObjectUtils.isEmpty(userDto.getUserName())) {
            logger.error(NO_FIRST_NAME);
            return NO_FIRST_NAME;
        } else {
            Pattern namePattern = Pattern.compile(NAME_REGEX, Pattern.CASE_INSENSITIVE);
            Matcher firstNameMatcher = namePattern.matcher(userDto.getUserName());
            boolean validFirstName = firstNameMatcher.find();
            if (!validFirstName) {
                logger.error(INVALID_FIRST_NAME, userDto.getUserName());
                return INVALID_FIRST_NAME;
            }
        }
        if (ObjectUtils.isEmpty(userDto.getPhoneNumber())) {
            logger.error(NO_PhoneNumber);
            return NO_PhoneNumber;
        } else {
            Pattern numberPattern = Pattern.compile(phoneNumber);
            Matcher ageMatcher = numberPattern.matcher(userDto.getPhoneNumber());
            boolean validFirstName = ageMatcher.find();
            if (!validFirstName) {
                logger.error(INVALID_PhoneNumber, userDto.getPhoneNumber());
                return INVALID_PhoneNumber;
            }
        }
        if (ObjectUtils.isEmpty(userDto.getPhoneNumber())) {
            logger.error(NO_EmailId);
            return NO_PhoneNumber;
        } else {
            Pattern numberPattern = Pattern.compile(Email_Id);
            Matcher ageMatcher = numberPattern.matcher(userDto.getEmailId());
            boolean validFirstName = ageMatcher.find();
            if (!validFirstName) {
                logger.error(INVALID_EmailId, userDto.getEmailId());
                return INVALID_EmailId;
            }
        }
        userRepository.save(user);
        return "ok";
    }

    @Override
    public String deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
            return "Deleted successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<UserDto> getAllUser() {
        Iterable<User> iterable= userRepository.findAll();
        List<UserDto>userDtos= StreamSupport.stream(iterable.spliterator(), false)
                .map(user -> {
                    UserDto userDto=new UserDto();
                    BeanUtils.copyProperties(user,userDto);
                    return userDto;
                }).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

}
