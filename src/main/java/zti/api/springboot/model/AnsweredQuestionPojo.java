package zti.api.springboot.model;

import java.util.ArrayList;
import java.util.List;

import zti.api.springboot.jpa.Answer;
import zti.api.springboot.jpa.Question;

public class AnsweredQuestionPojo {
	public Long id;
	public String text;
	public List<AnswerPojo> answers;
	public Long userAnswerId;
	public Long correctAnswerId;
	
	public AnsweredQuestionPojo() {}
	
	public AnsweredQuestionPojo(Question question, Long userAnswerId) {
		this.id = question.getId();
		this.text = question.getText();
		this.answers = new ArrayList<>();
		List<Answer> answers = question.getAnswers();
		for(Answer answer : answers) {
			AnswerPojo answerPojo = new AnswerPojo(answer);
			this.answers.add(answerPojo);
		}
		this.correctAnswerId = question.getCorrectAnswerId();
		this.userAnswerId = userAnswerId;
	}
}
