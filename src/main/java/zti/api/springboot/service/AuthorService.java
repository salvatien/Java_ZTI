package zti.api.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
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
    

    public Optional<Author> getAuthorById(Long authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new ResourceNotFoundException("Author with id " + authorId + " not found");
        }
        return authorRepository.findById(authorId);
    }

    public Author updateAuthorById(Long authorId, Author authorRequest) {
        if (!authorRepository.existsById(authorId)) {
            throw new ResourceNotFoundException("Author with id " + authorId + " not found");
        }
        Optional<Author> author = authorRepository.findById(authorId);

        if (!author.isPresent()) {
            throw new ResourceNotFoundException("Author with id " + authorId + " not found");
        }

        Author author1 = author.get();
        author1.setFirstName(authorRequest.getFirstName());
        author1.setLastName(authorRequest.getLastName());
        return authorRepository.save(author1);

    }

    public ResponseEntity<Object> deleteAuthorById(long authorId) {
        if (!authorRepository.existsById(authorId)) {
            throw new ResourceNotFoundException("Author with id " + authorId + " not found");
        }

        authorRepository.deleteById(authorId);

        return ResponseEntity.ok().build();

    }
    
}
