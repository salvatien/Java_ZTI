package zti.api.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import zti.api.springboot.service.*;
import zti.api.springboot.jpa.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
public class AuthorBookController {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;


    @RequestMapping(value = "/getAllAuthors", method = RequestMethod.GET)
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    //
    @RequestMapping(value = "/author", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Author createAuthor(@RequestBody Author author) {
        return authorService.createAuthor(author);
    }


    @RequestMapping(value = "/author/{authorId}", method = RequestMethod.GET)
    public Optional<Author> getAuthorById(@PathVariable(value = "authorId") Long authorId) {
        return authorService.getAuthorById(authorId);
    }

    @RequestMapping(value = "/author", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Author updateAuthor(@PathVariable(value = "authorId") Long authorId, @RequestBody Author author) {
        return authorService.updateAuthorById(authorId, author);
    }

    @RequestMapping(value = "/author/{authorId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAuthorById(@PathVariable(value = "authorId") long authorId) {
        return authorService.deleteAuthorById(authorId);
    }

    @RequestMapping(value = "/getAllBooks", method = RequestMethod.GET)
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }


    //
    @RequestMapping(value = "/{authorId}/book", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Book createBook(@PathVariable(value = "authorId") Long authorId, @RequestBody Book book) {
        return bookService.createBook(authorId, book);
    }

    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
    public Optional<Book> getBookById(@PathVariable(value = "bookId") Long bookId) {
        return bookService.getBookById(bookId);
    }


    @RequestMapping(value = "/book", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Book updateBook(@PathVariable(value = "bookId") Long bookId, @RequestBody Book book) {
        return bookService.updateBookById(bookId, book);
    }

    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBookById(@PathVariable(value = "bookId") long bookId) {
        return bookService.deleteBookById(bookId);
    }


}
