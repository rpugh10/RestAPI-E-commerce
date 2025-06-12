package com.codewithmosh.store.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users") // Makes it so you don't need to add /users endpoint in @GetMapping
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public Iterable<UserDto> getAllUsers()
    {
        return userRepository.findAll()
        .stream()
        .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
        .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) //Tells Spring to pull the id value from the url
    {
       var user = userRepository.findById(id).orElse(null);
       if(user == null)
       {
            
           return ResponseEntity.notFound().build();
       }
       
       var userDto = new UserDto(user.getId(), user.getName(), user.getEmail());

       return ResponseEntity.ok(userDto);
    }
}
