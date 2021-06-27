package com.coderman.springbootwithjunit.controller;

import com.coderman.springbootwithjunit.entity.Book;
import com.coderman.springbootwithjunit.model.ResponseModel;
import com.coderman.springbootwithjunit.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class BookControllerTest {

    @MockBean
    public BookService bookService;

    @Autowired
    public BookController bookController;

    @Test
    public void addBookTest(){
        Book book = buildABook();
        Mockito.when(bookService.checkBookAlreadyExist(book.getId())).thenReturn(false);

        ResponseEntity<ResponseModel> responseEntity = bookController.addBook(book);
        System.out.println(responseEntity.getStatusCode());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);

        ResponseModel responseModel = responseEntity.getBody();
        assertEquals(book.getId(),responseModel.getId());
        assertEquals("Success. Book is added",responseModel.getMessage());

    }

    private Book buildABook(){
        Book book = new Book();
        book.setId("abc123");
        book.setBookName("Spring");
        book.setAisle(123);
        book.setIsbn("abc");
        book.setAuthor("coderman");
        return book;
    }

}
