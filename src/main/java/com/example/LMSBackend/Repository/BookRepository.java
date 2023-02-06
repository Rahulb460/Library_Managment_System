package com.example.LMSBackend.Repository;

import com.example.LMSBackend.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findBookByName(String name);
}
