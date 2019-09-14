/**
 * 
 */
package zti.api.springboot.jpa;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Answer")
@Table(name = "answer")
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String text;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
	@OneToOne(mappedBy = "correctAnswer")
	private Question question;
	
	public Answer() {

	}

	public Answer(String text, Question question) {
		super();
		this.text = text;
		this.question = question;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
}
