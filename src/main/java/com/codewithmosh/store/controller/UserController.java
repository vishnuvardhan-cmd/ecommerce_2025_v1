package com.codewithmosh.store.controller;

import com.codewithmosh.store.dto.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    @GetMapping
    public List<UserDto> getAllUsers(){
        return userRepository.findAll()
                .stream().map(user->new UserDto(user.getId(),user.getName(),user.getEmail()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable  Long id){
        var user=userRepository.findById(id).orElse(null);
        if(user==null){
           return ResponseEntity.notFound().build();
        }
        var newUser=new UserDto(user.getId(),user.getName(),user.getEmail());
        return ResponseEntity.ok(newUser);
    }
}
