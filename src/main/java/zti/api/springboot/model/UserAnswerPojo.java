package zti.api.springboot.model;

import java.util.List;

public class UserAnswerPojo {
	public Long id;
	public String text;
	public List<AnswerPojo> answers;
	public Long userAnswerId;
}
