package com.example.LMSBackend.Controller;

import com.example.LMSBackend.Repository.BookRepository;
import com.example.LMSBackend.RequestDto.BookRequestDto;
import com.example.LMSBackend.ResponseDto.BookResponseDto;
import com.example.LMSBackend.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    // Add a book
    @PostMapping("/createBook")
    public ResponseEntity<String> createBook(@RequestBody() BookRequestDto bookRequestDto ) {
        String result = bookService.createBook(bookRequestDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // Find book by name
    @GetMapping("/find_book")
    public ResponseEntity<BookResponseDto> findBookByName(@RequestParam String name) {
        BookResponseDto bookResponseDto = bookService.findBookByName(name);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.FOUND);
    }

    // Get all Books
    @GetMapping("/get_all_books")
    public ResponseEntity<List<BookResponseDto>> getAllBooks(){
        List<BookResponseDto> bookResponseDtoList = bookService.getAllBooks();
        return new ResponseEntity<>(bookResponseDtoList, HttpStatus.FOUND);
    }
}
