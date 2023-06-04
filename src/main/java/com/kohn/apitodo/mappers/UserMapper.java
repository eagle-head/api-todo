package com.kohn.apitodo.mappers;

import com.kohn.apitodo.dto.UserCreateDto;
import com.kohn.apitodo.dto.UserDto;
import com.kohn.apitodo.models.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public User toUser(UserCreateDto userCreateDto) {
        User user = new User();

        user.setFirstName(userCreateDto.firstName());
        user.setLastName(userCreateDto.lastName());
        user.setEmail(userCreateDto.email());

        return user;
    }

    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}

