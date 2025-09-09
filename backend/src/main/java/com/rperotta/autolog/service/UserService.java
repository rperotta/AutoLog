package com.rperotta.autolog.service;

import com.rperotta.autolog.dto.UserCreationDTO;
import com.rperotta.autolog.dto.UserResponseDTO;
import com.rperotta.autolog.entity.User;
import com.rperotta.autolog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO addUser(UserCreationDTO userCreationDTO) {
        User user = new User();
        user.setUsername(userCreationDTO.getUsername());
        user.setPassword(userCreationDTO.getPassword());
        user.setRole(userCreationDTO.getRole());
        user.setVehicles(new ArrayList<>());
        User savedUser = userRepository.save(user);

        // 3. Mappa l'entità salvata nel DTO di risposta
        UserResponseDTO responseDto = new UserResponseDTO();
        responseDto.setId(savedUser.getId());
        responseDto.setUsername(savedUser.getUsername());
        responseDto.setRole(savedUser.getRole());

        // 4. Restituisci il DTO di risposta
        return responseDto;
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDTO> filteredUserList = new ArrayList<>();
        for(User u : userList){
            UserResponseDTO uRDTO = new UserResponseDTO();
            uRDTO.setId(u.getId());
            uRDTO.setUsername(u.getUsername());
            uRDTO.setRole(u.getRole());
            filteredUserList.add(uRDTO);
        }
        return filteredUserList;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

