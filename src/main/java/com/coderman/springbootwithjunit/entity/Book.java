package com.coderman.springbootwithjunit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "bookname")
    private String bookName;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "author")
    private String author;
    @Column(name = "aisle")
    private int aisle;

}
