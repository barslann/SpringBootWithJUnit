package com.coderman.springbootwithjunit.controller;

import com.coderman.springbootwithjunit.entity.Book;
import com.coderman.springbootwithjunit.model.ResponseModel;
import com.coderman.springbootwithjunit.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @MockBean
    public BookService bookService;

    @Autowired
    public BookController bookController;

    // invoke endpoints for testing
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addBookTestWithMockito(){
        Book book = buildABook();
        Mockito.when(bookService.checkBookAlreadyExist(book.getId())).thenReturn(false);

        ResponseEntity<ResponseModel> responseEntity = bookController.addBook(book);
        System.out.println(responseEntity.getStatusCode());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);

        ResponseModel responseModel = responseEntity.getBody();
        assertEquals(book.getId(),responseModel.getId());
        assertEquals("Success. Book is added",responseModel.getMessage());

    }


    @SneakyThrows
    @Test
    public void addBookTestWithMockMVC(){
        Book book = buildABook();
        Mockito.when(bookService.checkBookAlreadyExist(book.getId())).thenReturn(false);
        String bookJsonString = new ObjectMapper().writeValueAsString(book);

        ResultActions resultActions = this.mockMvc.perform(
                post("/api/addbook")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(bookJsonString));

        // print :))
        resultActions.andDo(print());

        // Then
        //Check status code
        resultActions
                .andExpect(status().isCreated());

        // Check if the id the same.
        resultActions.andExpect(jsonPath("$.id").value(book.getId()));

    }

    @Test
    public void getBookByAuthorTest() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(buildABook());
        books.add(buildABook());

        when(bookService.getBookByAuthorName(any())).thenReturn(books);
        ResultActions resultActions = this.mockMvc.
                perform(get("/api/getBooks/author").param("authorname","coderman"));

        resultActions.andDo(print());

        resultActions.andExpect(status().isOk());

        resultActions.andExpect(jsonPath("$.length()").value(String.valueOf(2)));

        resultActions.andExpect(jsonPath("$.[0].id").value("abc123"));


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
