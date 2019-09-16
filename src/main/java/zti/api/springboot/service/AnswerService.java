package zti.api.springboot.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import zti.api.springboot.jpa.Answer;
import zti.api.springboot.jpa.AnswerRepository;
import zti.api.springboot.jpa.Question;
import zti.api.springboot.jpa.QuestionRepository;

@Service
public class AnswerService {
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private QuestionRepository questionRepository;
	
	public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }
    
    public Optional<Answer> getAnswerById(Long id) {
    	 if (!answerRepository.existsById(id)) {
    	        throw new ResourceNotFoundException("Answer with id " + id + " not found");
    	    }
		return answerRepository.findById(id);
	}
    
    public Answer createAnswer(Long questionId, Answer answer) {
        Set<Answer> answers = new HashSet<>();
        Question question1 = new Question();

        Optional<Question> byId = questionRepository.findById(questionId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("Question with id " + questionId + " not found");
        }
        Question question = byId.get();

        answer.setQuestion(question);

        Answer answer1 = answerRepository.save(answer);
        answers.add(answer1);
        question1.setAnswers(answers);
        return answer1;

    }
    
    
}
