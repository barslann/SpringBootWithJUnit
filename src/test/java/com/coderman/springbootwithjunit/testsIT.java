package com.coderman.springbootwithjunit;

import com.coderman.springbootwithjunit.entity.Book;
import org.apache.tomcat.jni.Library;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;


@SpringBootTest
public class testsIT {
    //TestRestTemplate comes with default - RestAssured can also be used.

    @Test
    public void getAuthorNameBooksTest() throws JSONException {
        String expected = "[{\"id\":\"test123\",\"bookName\":\"spring\",\"isbn\":\"test\",\"author\":\"coderman\",\"aisle\":123},{\"id\":\"test124\",\"bookName\":\"react\",\"isbn\":\"test\",\"author\":\"coderman\",\"aisle\":124},{\"id\":\"test125\",\"bookName\":\"selenium\",\"isbn\":\"test\",\"author\":\"coderman\",\"aisle\":125},{\"id\":\"abc123\",\"bookName\":\"Spring\",\"isbn\":\"abc\",\"author\":\"coderman\",\"aisle\":123}]\n";
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:9090/api/getBooks/author?authorname=coderman",String.class);
        System.out.println("Status code: " + responseEntity.getStatusCode());
        System.out.println("Body: " + responseEntity.getBody());
        JSONAssert.assertEquals(expected,responseEntity.getBody(),false);
    }

    @Test
    public void addBookIntegrationTest()  {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Book> request = new HttpEntity<Book>(buildABook(),headers);
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("http://localhost:9090/api/addbook",request,String.class);
        System.out.println("Status code: " + responseEntity.getStatusCode());
        System.out.println("Body: " + responseEntity.getBody());
        Assertions.assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        Assertions.assertEquals(buildABook().getId(),responseEntity.getHeaders().get("unique").get(0));
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
