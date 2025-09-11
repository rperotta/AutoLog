package com.rperotta.autolog.service;

import com.rperotta.autolog.dto.UserCreationDTO;
import com.rperotta.autolog.dto.UserResponseDTO;
import com.rperotta.autolog.entity.User;
import com.rperotta.autolog.exception.UserNotFoundException;
import com.rperotta.autolog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDTO addUser(UserCreationDTO userCreationDTO) {
        User user = new User();
        user.setUsername(userCreationDTO.getUsername());
        user.setPassword(userCreationDTO.getPassword());
        user.setRole(userCreationDTO.getRole());
        user.setVehicles(new ArrayList<>());
        User savedUser = userRepository.save(user);

        return toUserResponseDTO(savedUser);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toUserResponseDTO)
                .toList();
    }

    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::toUserResponseDTO)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    public User getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        return dto;
    }
}

