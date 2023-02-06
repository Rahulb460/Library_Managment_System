package com.example.LMSBackend.Service;

import com.example.LMSBackend.Converters.BookConvertor;
import com.example.LMSBackend.Models.Author;
import com.example.LMSBackend.Models.Book;
import com.example.LMSBackend.Repository.AuthorRepository;
import com.example.LMSBackend.Repository.BookRepository;
import com.example.LMSBackend.RequestDto.BookRequestDto;
import com.example.LMSBackend.ResponseDto.BookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;
    public String createBook(BookRequestDto bookRequestDto) {
        Book book = BookConvertor.convertDtoToEntity(bookRequestDto);

        // I need to set the author entity
        int authorId = bookRequestDto.getAuthorId();

        // getting the author entity
        Author author = authorRepository.findById(authorId).get();
        book.setAuthor(author);
        book.setAvailable(true);

        // That book List also needs to be updated
        List<Book> currentListOfBooks = author.getBooksWritten();
        currentListOfBooks.add(book);
        author.setBooksWritten(currentListOfBooks);

        // save the author
        authorRepository.save(author);

        // save the book
        // this function will automatically be called by the parent save

        return "successfully added book";
    }

    public List<BookResponseDto> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();

        for (Book book : bookList) {
            BookResponseDto bookResponseDto = BookConvertor.covertEntityToDto(book);

            bookResponseDtoList.add(bookResponseDto);
        }
        return bookResponseDtoList;
    }

    public BookResponseDto findBookByName(String name) {
        Book book = bookRepository.findBookByName(name);
        BookResponseDto bookResponseDto = BookConvertor.covertEntityToDto(book);
        return bookResponseDto;
    }
}
