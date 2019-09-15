package zti.api.springboot.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import zti.api.springboot.jpa.Author;
import zti.api.springboot.jpa.AuthorDao;
import zti.api.springboot.jpa.Book;
import zti.api.springboot.jpa.BookDao;

@Service
public class BookService {
@Autowired
	private AuthorDao authorRepository;
@Autowired
	private BookDao bookDao;
	


public List<Book> getAllBooks() {
    return bookDao.findAll();
}


public Optional<Book> getBookById(Long bookId) {
    if (!bookDao.existsById(bookId)) {
        throw new ResourceNotFoundException("Book with id " + bookId + " not found");
    }
    return bookDao.findById(bookId);
}


public Book createBook(Long authorId, Book book) {
    Set<Book> books = new HashSet<>();
    Author author1 = new Author();

    Optional<Author> byId = authorRepository.findById(authorId);
    if (!byId.isPresent()) {
        throw new ResourceNotFoundException("Author with id " + authorId + " does not exist");
    }
    Author author = byId.get();

    //tie Author to Book
    book.setAuthor(author);

    Book book1 = bookDao.save(book);
    //tie Book to Author
    books.add(book1);
    author1.setBooks(books);
    //author.setBooks(books);
    return book1;

}

    
}
