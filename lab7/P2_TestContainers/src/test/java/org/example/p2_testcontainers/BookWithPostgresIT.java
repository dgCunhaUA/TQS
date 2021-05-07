package org.example.p2_testcontainers;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@SpringBootTest
class BookWithPostgresIT {

    @Autowired
    BookRepository bookRepository;

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("test")
            .withUsername("duke")
            .withPassword("password");


    // requires Spring Boot >= 2.2.6
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Test
    void contextLoads() {
        Book book = new Book(1L, "Think Like a Monk");
        bookRepository.save(book);

        System.out.println("Context loads!");
    }

    @Test
    @Order(1)
    void testCreateBook() {
        Book book = new Book(1L, "Think Like a Monk");
        bookRepository.save(book);

        Book book2 = bookRepository.findBookByTitle("Think Like a Monk");

        if(book2.getTitle().equals(book.getTitle()))
            System.out.println("Book Created and saved!");
    }

    @Test
    @Order(2)
    void testGetBook() {
        Book book = bookRepository.findBookByTitle("Think Like a Monk");

        System.out.println(book.getTitle());
        assert book != null;
        System.out.println("Book Found!");
    }


    @Test
    @Order(3)
    void testUpdateBook() {
        Book book = bookRepository.findBookByTitle("Think Like a Monk");
        book.setTitle("Other book");
        bookRepository.save(book);

        System.out.println("Book updated!");
    }


    @Test
    @Order(4)
    void testDeleteBook() {
        Book book = bookRepository.findBookById(2L);
        bookRepository.delete(book);

        System.out.println("Book deleted!");
    }
}
