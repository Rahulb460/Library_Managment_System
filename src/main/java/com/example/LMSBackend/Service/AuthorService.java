package com.example.LMSBackend.Service;

import com.example.LMSBackend.Converters.AuthorConvertor;
import com.example.LMSBackend.Models.Author;
import com.example.LMSBackend.Repository.AuthorRepository;
import com.example.LMSBackend.RequestDto.AuthorRequestDto;
import com.example.LMSBackend.ResponseDto.AuthorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String createAuthor(AuthorRequestDto authorRequestDto) {
        try{
            Author author = AuthorConvertor.convertDtoToEntity(authorRequestDto);
            authorRepository.save(author);
        }
        catch (Exception e) {
            log.info("createAuthor has caused an error");
            return "Create author did not work";
        }
        return "Author created Successfully";
    }

    public List<AuthorResponseDto> findAuthorByName(String name) {
        List<Author> authors = authorRepository.findByName(name);
        List<AuthorResponseDto> authorResponseDtos = AuthorConvertor.convertEntityToDto(authors);
        return authorResponseDtos;
    }
}
