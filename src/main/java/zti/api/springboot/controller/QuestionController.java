package zti.api.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import zti.api.springboot.service.*;
import zti.api.springboot.jpa.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    GenreService genreService;


    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public List<Question> getQuestions() {
        return questionService.getQuestions();
    }
    
    @RequestMapping(value = "/questions/{questionId}", method = RequestMethod.GET)
    public Question getQuestionById(@PathVariable(value = "questionId") Long questionId) {
        try {
			return questionService.getQuestionById(questionId).get();
		} catch (Exception e) {
			return new Question();
		}
    }
    
    //http://localhost:8080/questions/genresQuestions?genreIds=3,10&questionsAmount=3
    @RequestMapping("/questions/genresQuestions")
    public List<Question> GetNumberOfQuestionsFromGenres(@RequestParam List<Long> genreIds, int questionsAmount) 
    {
    	List<Question> questions = new ArrayList<Question>();
    	for(Long genreId : genreIds){
    		Genre genre;
			try {
				genre = genreService.getGenreById(genreId).get();
	    		questions.addAll(genre.getQuestions());
			} catch (Exception e) {
				continue;
			}
    		
    	}
    	Collections.shuffle(questions); 
    	
    	return questions.stream().limit(questionsAmount).collect(Collectors.toList());
    }
    

}
