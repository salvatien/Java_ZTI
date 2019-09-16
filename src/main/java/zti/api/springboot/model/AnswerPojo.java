package zti.api.springboot.model;

import zti.api.springboot.jpa.Answer;

public class AnswerPojo {
	public Long id;
	public String text;
	
	public AnswerPojo(Answer answer) {
		this.id = answer.getId();
		this.text = answer.getText();
	}
}
