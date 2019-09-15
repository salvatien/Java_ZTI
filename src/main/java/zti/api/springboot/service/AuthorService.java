package zti.api.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zti.api.springboot.jpa.Author;
import zti.api.springboot.jpa.AuthorDao;

@Service
public class AuthorService {
	@Autowired
	private AuthorDao authorRepository;
	
	public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }
    
}
