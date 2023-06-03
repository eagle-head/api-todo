package com.kohn.apitodo.services;

import com.kohn.apitodo.dto.UserCreateDto;
import com.kohn.apitodo.dto.UserDto;
import com.kohn.apitodo.exceptions.EmailAlreadyExistsException;
import com.kohn.apitodo.mappers.UserMapper;
import com.kohn.apitodo.models.User;
import com.kohn.apitodo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserDto createUser(UserCreateDto userCreateDto) {
        LOGGER.info("Attempting to create user with Email: {}", userCreateDto.getEmail());

        // Check if email already exists
        userRepository.findByEmail(userCreateDto.getEmail())
                .ifPresent(user -> {
                    LOGGER.error("User with email: {} already exists", userCreateDto.getEmail());
                    throw new EmailAlreadyExistsException(userCreateDto.getEmail());
                });

        // Create the User entity
        User user = userMapper.toUser(userCreateDto);
        user.setPassword(userCreateDto.getPassword());

        // Save the user
        User savedUser = userRepository.save(user);
        LOGGER.info("User created successfully with Email: {}", userCreateDto.getEmail());

        // Convert the saved User entity to a UserDto and return
        return userMapper.toUserDto(savedUser);
    }

//    public UserDto getUser(Long id) {
//        // TODO: Fetch user from database, convert to UserDto and return
//        return null;
//    }
//
//    public UserDto updateUser(Long id, UserDto userDto) {
//        // TODO: Fetch user from database, update fields, save back to database, convert to UserDto and return
//        return null;
//    }
//
//    public void deleteUser(Long id) {
//        // TODO: Delete user from database
//    }
}
