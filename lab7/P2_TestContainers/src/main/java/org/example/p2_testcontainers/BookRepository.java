package org.example.p2_testcontainers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookById(Long id);

    Book findBookByTitle(String title);

    List<Book> findAll();

}