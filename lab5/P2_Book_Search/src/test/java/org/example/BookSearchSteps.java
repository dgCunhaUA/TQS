package org.example;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.platform.engine.Cucumber;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Cucumber
public class BookSearchSteps {

        Library library = new Library();
        List<Book> result = new ArrayList<>();

        @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
        public LocalDateTime iso8601Date(String year, String month, String day) {
                return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0, 0);
        }

        @Given("(a|another) book with the title {string}, written by {string}, published in {iso8601Date}")
        public void addNewBook(final String title, final String author, final LocalDateTime published) {
                Book book = new Book(title, author, published);
                library.addBook(book);
        }

        @When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
        public void searchBookByDateInterval (LocalDateTime from, LocalDateTime to) {
                result = library.findBooks(from, to);
        }

        @Then("{int} books should have been found")
        public void numberOfBooksFound (Integer arg0) {
                assertEquals(result.size(), arg0);
        }

        @And("Book {int} should have the title {string}")
        public void verifyBooksTitle(Integer arg0, String arg1) {
                assertEquals(result.get(arg0 - 1).getTitle(), arg1);
        }
}
