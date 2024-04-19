package com.arnaud.api.controller;

import com.arnaud.api.dto.UsersDto;
import com.arnaud.api.entity.Users;
import com.arnaud.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<UsersDto> save(@RequestBody Users users) {
        UsersDto savedUser = userService.save(users);
        return ResponseEntity.ok(savedUser);
    }
    @GetMapping("/id/{uuid}")
    public ResponseEntity<UsersDto> findById(@PathVariable UUID uuid) {
       UsersDto usersDto= userService.findUsersById(uuid);
        return ResponseEntity.ok(usersDto);
    }

}
