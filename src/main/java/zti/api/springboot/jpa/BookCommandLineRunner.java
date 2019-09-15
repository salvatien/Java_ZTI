package zti.api.springboot.jpa;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import zti.api.springboot.service.AuthorService;
import zti.api.springboot.service.BookService;

@Component
public class BookCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory
			.getLogger(BookCommandLineRunner.class);

	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorService authorService;

	@Override
	public void run(String... args) throws Exception {
		Author author = new Author();
		author.setFirstName("mati");
		author.setLastName("gryzzli");
		authorService.createAuthor(author);
		
		Book book = new Book();
		
		book.setTitle("TESTTITLE");
	    book.setGenre("nie wiem lol");

		Book addedBook = bookService.createBook(new Long(597), book);
		
		Author authorro = addedBook.getAuthor();
		log.info(authorro.getLastName());
		log.info("finished");
//		Book book = new Book();
//		book.setAuthor(author);

//		for (Answer answer : repository.findAll()) {
//			log.info(answer.toString());
	}

}
	
