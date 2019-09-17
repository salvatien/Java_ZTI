package zti.api.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import zti.api.springboot.service.*;
import zti.api.springboot.jpa.*;
import zti.api.springboot.model.QuestionPojo;
import zti.api.springboot.model.QuestionsListPojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    GenreService genreService;


    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public QuestionsListPojo getQuestions() {
        
        List<QuestionPojo> questionPojos = new ArrayList<>();
        List<Question> questions =  questionService.getQuestions();
        for(Question question : questions) {
        	QuestionPojo questionPojo = new QuestionPojo(question);
        	questionPojos.add(questionPojo);
        }
        QuestionsListPojo list = new QuestionsListPojo();
        list.questions = questionPojos;
        return list;
    }
    
    @RequestMapping(value = "/questions/{questionId}", method = RequestMethod.GET)
    public QuestionPojo getQuestionById(@PathVariable(value = "questionId") Long questionId) {
        try {
			return new QuestionPojo(questionService.getQuestionById(questionId).get());
		} catch (Exception e) {
			return new QuestionPojo(null);
		}
    }
    
    //http://localhost:8080/questions/genresQuestions?genreIds=3,10&questionsAmount=3
    @RequestMapping("/questions/genresQuestions")
    public QuestionsListPojo GetNumberOfQuestionsFromGenres(@RequestParam List<Long> genreIds, int questionsAmount) 
    {
    	List<QuestionPojo> questionPojos = new ArrayList<>();

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
    	
    	List<Question> selectedQuestions = questions.stream().limit(questionsAmount).collect(Collectors.toList());
    	
        for(Question question : selectedQuestions) {
        	QuestionPojo questionPojo = new QuestionPojo(question);
        	questionPojos.add(questionPojo);
        }
        QuestionsListPojo list = new QuestionsListPojo();
        list.questions = questionPojos;
        return list;
    }
    

}
