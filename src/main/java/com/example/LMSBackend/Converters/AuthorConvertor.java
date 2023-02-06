package com.example.LMSBackend.Converters;

import com.example.LMSBackend.Models.Author;
import com.example.LMSBackend.RequestDto.AuthorRequestDto;
import com.example.LMSBackend.ResponseDto.AuthorResponseDto;
import com.example.LMSBackend.ResponseDto.BookResponseDto;

import java.util.ArrayList;
import java.util.List;

public class AuthorConvertor {

    public static Author convertDtoToEntity(AuthorRequestDto authorRequestDto) {
        Author author = Author.builder().
                name(authorRequestDto.getName())
                .age(authorRequestDto.getAge())
                .country(authorRequestDto.getCountry())
                .email(authorRequestDto.getEmail()).build();

        return author;
    }

    public static List<AuthorResponseDto> convertEntityToDto(List<Author> authors) {
        List<AuthorResponseDto> authorResponseDtoList = new ArrayList<>();

        for(Author author : authors) {
            AuthorResponseDto authorResponseDto = AuthorResponseDto.builder().id(author.getId()).name(author.getName())
                    .age(author.getAge()).country(author.getCountry()).email(author.getEmail()).build();

            // try yourself on how to add the BookResponseDto
            authorResponseDtoList.add(authorResponseDto);
        }
        return authorResponseDtoList;
    }
}
