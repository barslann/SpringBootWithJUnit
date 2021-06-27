package com.coderman.springbootwithjunit.service;

import com.coderman.springbootwithjunit.entity.Book;
import com.coderman.springbootwithjunit.repo.BookRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    public final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void addBook(Book book){
        bookRepo.save(book);
    }

    public boolean checkBookAlreadyExist(String id) {
        return bookRepo.findById(id).isPresent();
    }

    public Book getBookById(String id) {
        return bookRepo.findById(id).get();
    }

    public List<Book> getBookByAuthorName(String authorName) {
        return bookRepo.findByAuthorIs(authorName);
    }


    public Book updateBook(Book book) {
        Book existingBook = bookRepo.findById(book.getId()).orElseThrow(NoSuchElementException::new);
        if (existingBook != null) book.setId(existingBook.getId());
        bookRepo.save(book);
        return book;
    }

    public Book updateBook(String id,Book book) {
        book.setId(id);
        bookRepo.save(book);
        return book;
    }

    public void deleteBook(String id) {
        bookRepo.deleteById(id);
    }
}
