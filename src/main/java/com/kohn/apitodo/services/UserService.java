package com.kohn.apitodo.services;

import com.kohn.apitodo.dtos.UserCreateDto;
import com.kohn.apitodo.dtos.UserDto;
import com.kohn.apitodo.exceptions.EmailAlreadyExistsException;
import com.kohn.apitodo.exceptions.UserNotFoundException;
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
        LOGGER.info("Attempting to create user with Email: {}", userCreateDto.email());

        // Check if email already exists
        userRepository.findByEmail(userCreateDto.email())
                .ifPresent(user -> {
                    LOGGER.error("User with email: {} already exists", userCreateDto.email());
                    throw new EmailAlreadyExistsException(userCreateDto.email());
                });

        // Create the User entity
        User user = userMapper.toUser(userCreateDto);
        user.setPassword(userCreateDto.password());

        // Save the user
        User savedUser = userRepository.save(user);
        LOGGER.info("User created successfully with Email: {}", userCreateDto.email());

        // Convert the saved User entity to a UserDto and return
        return userMapper.toUserDto(savedUser);
    }

    public UserDto findUserById(Long userId) {
        LOGGER.info("Attempting to find user with ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    LOGGER.error("User with ID: {} not found", userId);
                    return new UserNotFoundException(userId);
                });

        LOGGER.info("User retrieved successfully with ID: {}", userId);

        // Convert User entity to UserDto, which should not include the password
        return userMapper.toUserDto(user);
    }

//    public UserDto updateUser(Long id, UserDto userDto) {
//        // TODO: Fetch user from database, update fields, save back to database, convert to UserDto and return
//        return null;
//    }
//
//    public void deleteUser(Long id) {
//        // TODO: Delete user from database
//    }
}
