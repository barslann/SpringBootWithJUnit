package com.coderman.springbootwithjunit.controller;

import com.coderman.springbootwithjunit.entity.Book;
import com.coderman.springbootwithjunit.model.ResponseModel;
import com.coderman.springbootwithjunit.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class BookController {

    public final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addbook")
    public ResponseEntity<ResponseModel> addBook(@RequestBody Book book){
        book.setId(book.getIsbn() + book.getAisle());
        if(!bookService.checkBookAlreadyExist(book.getId())){
            bookService.addBook(book);

            ResponseModel responseModel = new ResponseModel(book.getId(),"Success. Book is added");

            HttpHeaders headers = new HttpHeaders();
            headers.add("unique", book.getId());

            return new ResponseEntity<>(responseModel,headers, HttpStatus.CREATED);
        }else{
            ResponseModel responseModel = new ResponseModel(book.getId(),"Book already exist!");
            return new ResponseEntity<>(responseModel,HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("getBooks/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") String id){
        try{
            Book book = bookService.getBookById(id);
            return new ResponseEntity<>(book,HttpStatus.OK);
        }catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getBooks/author")
    public ResponseEntity<List<Book>> getBookByAuthor(@RequestParam("authorname") String authorName){
        try{
            List<Book> libraries = bookService.getBookByAuthorName(authorName);
            return new ResponseEntity<>(libraries,HttpStatus.OK);
        }catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("updateBook")
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        book = bookService.updateBook(book);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }


    @PutMapping("updateBook/{id}")
    public ResponseEntity<Book> updateBook2(@PathVariable("id")String id,@RequestBody Book book){
        book = bookService.updateBook(id,book);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @DeleteMapping ("deleteBook")
    public ResponseEntity<String> deleteBook(@RequestBody Book book){
        bookService.deleteBook(book.getId());
        return new ResponseEntity<>("Book is deleted",HttpStatus.ACCEPTED);
    }

}
