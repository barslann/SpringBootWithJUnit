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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @SneakyThrows
    @Test
    public void updateBookTest(){
        Book book = buildABook();
        when(bookService.updateBook(any(),any())).thenReturn(updateABook());
        String bookJsonString = new ObjectMapper().writeValueAsString(updateABook());


        ResultActions resultActions = this.mockMvc.perform(put("/api/updateBook/" + book.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJsonString));

        resultActions.andDo(print());

        resultActions.andExpect(status().isOk());

        //this is going to be fail because updateABook() method create new book will new id.
        // but put request wont change the id.
//        resultActions.andExpect(content().json(bookJsonString));

        //this will work
        resultActions.andExpect(content().json("{\"id\":\"abc123\",\"bookName\":\"React\",\"isbn\":\"abc\",\"author\":\"coderman\",\"aisle\":123}"));
//        resultActions.andExpect(jsonPath("$.bookName").value(book.getBookName()));
    }

    @SneakyThrows
    @Test
    public void deleteBookControllerTest(){
        Book book = buildABook();
        doNothing().when(bookService).deleteBook(book.getId());
        ResultActions resultActions = this.mockMvc.
                perform(delete("/api/deleteBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"abc123\"}"))
                .andDo(print());

        resultActions.andExpect(status().isAccepted());

        resultActions.andExpect(content().string("Book is deleted"));

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

    private Book updateABook(){
        Book book = new Book();
        book.setId("abc123");
        book.setBookName("React");
        book.setAisle(123);
        book.setIsbn("abc");
        book.setAuthor("coderman");
        return book;
    }

}
