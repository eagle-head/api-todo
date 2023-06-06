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

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

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

    public UserDto findUserById(Long id) {
        LOGGER.info("Attempting to find user with ID: {}", id);

        User user = getUserById(id);
        LOGGER.info("User retrieved successfully with ID: {}", id);

        // Convert User entity to UserDto, which should not include the password
        return userMapper.toUserDto(user);
    }

    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        LOGGER.info("Attempting to update user with ID: {}", id);

        // Fetch user from database
        User user = getUserById(id);

        // Update fields
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setEmail(userDto.email());

        // Save back to database
        User updatedUser = userRepository.save(user);
        LOGGER.info("User updated successfully with ID: {}", id);

        // Convert to UserDto and return
        return userMapper.toUserDto(updatedUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        LOGGER.info("Attempting to delete user with ID: {}", id);

        User user = getUserById(id);

        userRepository.delete(user);
        LOGGER.info("User deleted successfully with ID: {}", id);
    }

    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("User with ID: {} not found", id);
                    return new UserNotFoundException(id);
                });
    }
}
