package zti.api.springboot.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import zti.api.springboot.jpa.Answer;
import zti.api.springboot.jpa.Genre;
import zti.api.springboot.jpa.GenreRepository;
import zti.api.springboot.jpa.Question;
import zti.api.springboot.jpa.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private GenreRepository genreRepository;
	
	public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }
    
    public Optional<Question> getQuestionById(Long id) throws Exception {
    	 if (!questionRepository.existsById(id)) {
    	        throw new Exception("Not found");
    	    }
		return questionRepository.findById(id);
	}
    
    public Question createQuestion(Long genreId, Question question) throws Exception {
    	
    	
        Set<Question> questions = new HashSet<>();
        Genre genre1 = new Genre();

        Optional<Genre> byId = genreRepository.findById(genreId);
        if (!byId.isPresent()) {
            throw new Exception("Not found");
        }
        
        Genre genre = byId.get();

        question.setGenre(genre);

        Question question1 = questionRepository.save(question);
        questions.add(question1);
        
        genre1.setQuestions(questions);
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
