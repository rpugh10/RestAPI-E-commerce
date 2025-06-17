package com.codewithmosh.store.controller;


import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users") // Makes it so you don't need to add /users endpoint in @GetMapping
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public Iterable<UserDto> getAllUsers(@RequestParam(required = false, defaultValue = "", name ="sort") String sort) //used to extract query parameters from the URL of an HTTP request.
    {
       if(!Set.of("name" , "email").contains(sort))
       sort = "name";

        return userRepository.findAll(Sort.by(sort))
        .stream()
        .map(userMapper::toDto)
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

       return ResponseEntity.ok(userMapper.toDto(user));
    }
}
