package com.coderman.springbootwithjunit.repo;

import com.coderman.springbootwithjunit.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book,String> {
    List<Book> findByAuthorIs(String authorName);

    //It can be used to update data with field
//    @Modifying
//    @Query("update User u set u.firstname = ?1, u.lastname = ?2 where u.id = ?3")
//    void setUserInfoById(String firstname, String lastname, Integer userId);
}
