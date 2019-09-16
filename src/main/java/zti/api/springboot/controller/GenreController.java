package zti.api.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import zti.api.springboot.service.*;
import zti.api.springboot.jpa.*;
import zti.api.springboot.model.GenrePojo;
import zti.api.springboot.model.ModePojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
public class GenreController {

    @Autowired
    GenreService genreService;
    @Autowired
    ModeService modeService;


    @RequestMapping(value = "/genres", method = RequestMethod.GET)
    public List<GenrePojo> getGenres() {
    	List<GenrePojo> genrePojos = new ArrayList<>();
        List<Genre> genres =  genreService.getGenres();
        for(Genre genre : genres) {
        	GenrePojo genrePojo = new GenrePojo(genre);
        	genrePojos.add(genrePojo);
        }
        return genrePojos;
    }

    @RequestMapping(value = "/genres/{genreId}", method = RequestMethod.GET)
    public GenrePojo getGenreById(@PathVariable(value = "genreId") Long genreId) {
        try {
			return new GenrePojo(genreService.getGenreById(genreId).get());
		} catch (Exception e) {
			return new GenrePojo(null);
		}
    }

    @RequestMapping(value = "/{modeId}/genres", method = RequestMethod.GET)
    public List<GenrePojo> getGenresByModeId(@PathVariable(value = "modeId") Long modeId) {
    	
    	
    	List<GenrePojo> genrePojos = new ArrayList<>();
        List<Genre> genres;
		try {
			genres = modeService.getModeById(modeId).get().getGenres();
	        for(Genre genre : genres) {
	        	GenrePojo genrePojo = new GenrePojo(genre);
	        	genrePojos.add(genrePojo);
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return genrePojos;
    	
    }

}
