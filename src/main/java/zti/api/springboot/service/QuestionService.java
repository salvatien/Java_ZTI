package zti.api.springboot.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import zti.api.springboot.jpa.Answer;
import zti.api.springboot.jpa.Category;
import zti.api.springboot.jpa.CategoryRepository;
import zti.api.springboot.jpa.Question;
import zti.api.springboot.jpa.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }
    
    public Optional<Question> getQuestionById(Long id) {
    	 if (!questionRepository.existsById(id)) {
    	        throw new ResourceNotFoundException("Question with id " + id + " not found");
    	    }
		return questionRepository.findById(id);
	}
    
    public Question createQuestion(Long categoryId, Question question) {
    	
    	
        Set<Question> questions = new HashSet<>();
        Category category1 = new Category();

        Optional<Category> byId = categoryRepository.findById(categoryId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("Category with id " + categoryId + " not found");
        }
        
        Category category = byId.get();

        question.setCategory(category);

        Question question1 = questionRepository.save(question);
        questions.add(question1);
        
        category1.setQuestions(questions);
        return question1;

    }
    
    public Answer getCorrectAnswer(Long questionId) {
    	Optional<Question> byId = questionRepository.findById(questionId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("Question with id " + questionId + " not found");
        }
        
        Question question = byId.get();
        return question.getCorrectAnswer();
    }
    
}
