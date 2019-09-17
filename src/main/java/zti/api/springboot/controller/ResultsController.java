package zti.api.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import zti.api.springboot.service.*;
import zti.api.springboot.jpa.*;
import zti.api.springboot.model.AnsweredQuestionPojo;
import zti.api.springboot.model.AnsweredQuestionsListPojo;
import zti.api.springboot.model.UserAnswerPojo;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
public class ResultsController {

    @Autowired
    QuestionService questionService;

    @CrossOrigin
    @RequestMapping(value = "/results", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public AnsweredQuestionsListPojo SubmitQuizAndGetResults(@RequestBody List<UserAnswerPojo> userAnswers) {
    	List<AnsweredQuestionPojo> answeredQuestionsList = new ArrayList<>();
        for(UserAnswerPojo userAnswerPojo : userAnswers) {
        	try {
	        	Question question = questionService.getQuestionById(userAnswerPojo.id).get();
	        	AnsweredQuestionPojo answeredQuestionPojo = new AnsweredQuestionPojo(question, userAnswerPojo.userAnswerId);
	        	answeredQuestionsList.add(answeredQuestionPojo);
        	}
        	catch(Exception e) {
        		continue;
        	}
        	
        }
        AnsweredQuestionsListPojo list = new AnsweredQuestionsListPojo();
        list.questions = answeredQuestionsList;
        return list;
    }
    
}
