package zti.api.springboot.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zti.api.springboot.jpa.GenreRepository;
import zti.api.springboot.jpa.Mode;
import zti.api.springboot.jpa.ModeRepository;
import zti.api.springboot.jpa.Genre;

@Service
public class GenreService {
	@Autowired
	private GenreRepository repository;
	
	@Autowired
	private ModeRepository modeRepository;
	
	public List<Genre> getGenres() {
        return repository.findAll();
    }
	
	public Optional<Genre> getGenreById(Long id) throws Exception {
		if (!repository.existsById(id)) {
		        throw new Exception("Not found");
		}
		return repository.findById(id);
	}
	
	public Genre createGenre(Long modeId, Genre genre) throws Exception {
    	
        Set<Genre> genres = new HashSet<>();
        Mode mode1 = new Mode();

        Optional<Mode> byId = modeRepository.findById(modeId);
        if (!byId.isPresent()) {
            throw new Exception("Not found");
        }
        
        Mode mode = byId.get();

        genre.setMode(mode);

        Genre genre1 = repository.save(genre);
        genres.add(genre1);
        
        mode1.setGenres(genres);
        
        return genre1;

    }

    public Genre createGenre(Genre genre) {
        return repository.save(genre);
    }
    
}
