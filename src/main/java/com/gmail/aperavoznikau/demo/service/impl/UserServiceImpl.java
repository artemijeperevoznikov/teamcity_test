package com.gmail.aperavoznikau.demo.service.impl;

import com.gmail.aperavoznikau.demo.repository.RoleRepository;
import com.gmail.aperavoznikau.demo.repository.UserRepository;
import com.gmail.aperavoznikau.demo.repository.model.Role;
import com.gmail.aperavoznikau.demo.repository.model.User;
import com.gmail.aperavoznikau.demo.service.UserService;
import com.gmail.aperavoznikau.demo.service.model.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.find(id);
        return convertToDTO(user);
    }


    @Override
    @Transactional
    public UserDTO add(UserDTO userDTO) {
        User user = convert(userDTO);
        userRepository.add(user);
        return convertToDTO(user);
    }

    @Override
    public boolean isUniqueUsername(String username) {
        UserDTO user = findByUsername(username);
        return user == null;
    }

    @Override
    @Transactional
    public UserDTO findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(this::convertToDTO).orElse(null);
    }

    @Override
    public boolean isValidUser(String username, String password) {
        UserDTO userDTO = findByUsername(username);
        if (userDTO != null) {
            return passwordEncoder.matches(password, userDTO.getPassword());
        } else {
            return false;
        }
    }

    private User convert(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Optional<Role> role = roleRepository.findByName(userDTO.getRole());
        role.ifPresent(user::setRole);
        return user;
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole().getName());
        return userDTO;
    }
}
