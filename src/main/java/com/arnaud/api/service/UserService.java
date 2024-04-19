package com.arnaud.api.service;

import com.arnaud.api.dto.UsersDto;
import com.arnaud.api.entity.Role;
import com.arnaud.api.entity.Users;
import com.arnaud.api.exception.RoleExecption;
import com.arnaud.api.exception.UserNotFondException;
import com.arnaud.api.mapper.UsersMapper;
import com.arnaud.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService  {

    private final UserRepository userRepository;

    private final UsersMapper usersMapper = UsersMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public UsersDto save(Users users) {
        try {
            if (users.getPassword().startsWith("adm_")) {
                users.setRole(Role.ADMIN);
            } else {
                users.setRole(Role.USER);
            }

            if (users.getUsername().length() > 11) {
                throw new RoleExecption("Username is too long");
            }

            Users userSaved = userRepository.save(users);
            userSaved.setPassword(passwordEncoder.encode(userSaved.getPassword()));
            return usersMapper.userToUserDto(userSaved);
        } catch (RoleExecption e) {
            // Log the exception or handle it as needed
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<UsersDto> findAll() {
        return userRepository.findAll().stream().map(usersMapper::userToUserDto).collect(Collectors.toList());
    }
    public UsersDto findUsersById(UUID id) {
        return usersMapper.userToUserDto(userRepository.findById(id).orElseThrow(()-> new UserNotFondException("User not found")));
    }
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
