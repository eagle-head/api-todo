package com.kohn.apitodo.mappers;

import com.kohn.apitodo.dto.UserCreateDto;
import com.kohn.apitodo.dto.UserDto;
import com.kohn.apitodo.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserCreateDto userCreateDto) {
        User user = new User();

        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setEmail(userCreateDto.getEmail());

        return user;
    }

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
