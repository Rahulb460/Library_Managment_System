package com.example.LMSBackend.Converters;

import com.example.LMSBackend.Models.Book;
import com.example.LMSBackend.RequestDto.BookRequestDto;
import com.example.LMSBackend.ResponseDto.BookResponseDto;

public class BookConvertor {

    public static Book convertDtoToEntity(BookRequestDto bookRequestDto) {
        Book book = Book.builder().name(bookRequestDto.getName()).
                genre(bookRequestDto.getGenre()).build();

        return book;
    }

    public static BookResponseDto covertEntityToDto(Book book){
        BookResponseDto bookResponseDto = BookResponseDto.builder().name(book.getName()).
                genre(book.getGenre()).author(book.getAuthor().getName()).build();

        return bookResponseDto;
    }
}
