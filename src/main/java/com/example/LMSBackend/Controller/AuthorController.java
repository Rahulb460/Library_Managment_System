package com.example.LMSBackend.Controller;

import com.example.LMSBackend.Models.Author;
import com.example.LMSBackend.RequestDto.AuthorRequestDto;
import com.example.LMSBackend.ResponseDto.AuthorResponseDto;
import com.example.LMSBackend.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    // Create Author
    @PostMapping("/create")
    public ResponseEntity<String> createAuthor(@RequestBody()AuthorRequestDto authorRequestDto) {
        String result = authorService.createAuthor(authorRequestDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    // Find author by Name
    @GetMapping("/findBy")
    public ResponseEntity<List<AuthorResponseDto>> findByName(@RequestParam String name){
        return new ResponseEntity<>(authorService.findAuthorByName(name), HttpStatus.FOUND);
    }
}
