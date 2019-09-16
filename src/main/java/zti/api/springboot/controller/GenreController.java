package zti.api.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import zti.api.springboot.service.*;
import zti.api.springboot.jpa.*;

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
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }

    @RequestMapping(value = "/genres/{genreId}", method = RequestMethod.GET)
    public Genre getGenreById(@PathVariable(value = "genreId") Long genreId) {
        try {
			return genreService.getGenreById(genreId).get();
		} catch (Exception e) {
			return new Genre();
		}
    }

    @RequestMapping(value = "/{modeId}/genres", method = RequestMethod.GET)
    public List<Genre> getGenresByModeId(@PathVariable(value = "modeId") Long modeId) {
        try {
			return modeService.getModeById(modeId).get().getGenres();
		} catch (Exception e) {
			return new ArrayList<Genre>();
		}
    }

}
