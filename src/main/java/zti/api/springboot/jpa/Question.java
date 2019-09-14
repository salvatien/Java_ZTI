/**
 * 
 */
package zti.api.springboot.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
/**
 * @author salva
 *
 */
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Question")
@Table(name = "question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
	private Category category;
	
	private String text;
	@OneToMany(
	        mappedBy = "question",
	        cascade = CascadeType.DETACH,
	        orphanRemoval = false
	    )
	private List<Answer> answers = new ArrayList<>();
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "correctAnswer_id", referencedColumnName = "id")
	private Answer correctAnswer;

	public Question(Category category, String text,
			List<Answer> answers, Answer correctAnswer) {
		super();
		this.category = category;
		this.text = text;
		this.answers = answers;
		this.correctAnswer = correctAnswer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Answer getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(Answer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}
